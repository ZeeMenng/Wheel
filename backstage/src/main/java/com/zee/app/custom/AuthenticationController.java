package com.zee.app.custom;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsUtils;

import com.zee.app.generate.swagger.base.BaseSwgApp;
import com.zee.bll.extend.split.gp.GpLoginLogSplBll;
import com.zee.bll.extend.split.gp.GpUserSplBll;
import com.zee.bll.extend.split.gp.GprDomainUserSplBll;
import com.zee.bll.extend.unity.gp.GpLoginLogUntBll;
import com.zee.bll.extend.unity.gp.GpTokenUntBll;
import com.zee.bll.extend.unity.gp.GpUserUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.enumer.DomainEnum;
import com.zee.set.enumer.LogoutType;
import com.zee.set.enumer.OperResult;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.TokenUtil;
import com.zee.utl.Tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/oauth")
public class AuthenticationController extends BaseSwgApp {

	@Autowired
	@Qualifier("gpLoginLogUntBll")
	protected GpLoginLogUntBll gpLoginLogUntBll;

	@Autowired
	@Qualifier("gpLoginLogSplBll")
	protected GpLoginLogSplBll gpLoginLogSplBll;

	@Resource(name = "gpTokenUntBll")
	protected GpTokenUntBll gpTokenUntBll;

	@Resource(name = "gpUserSplBll")
	protected GpUserSplBll gpUserSplBll;

	@Resource(name = "gpUserUntBll")
	protected GpUserUntBll gpUserUntBll;

	@Resource(name = "gprDomainUserSplBll")
	protected GprDomainUserSplBll gprDomainUserSplBll;

	@RequestMapping(value = "/token", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel login() {
		ResultModel result = new ResultModel();

		// ???????????????????????????OK
		if (CorsUtils.isPreFlightRequest(request)) {
			response.setStatus(HttpServletResponse.SC_OK);
			return result;
		}

		String grantType = request.getParameter("grant_type");
		String clientId = request.getParameter("client_id");
		if (StringUtils.isBlank(grantType))
			throw new GlobalException("???????????????????????????");
		if (StringUtils.isBlank(clientId))
			throw new GlobalException("???????????????????????????");

		if (grantType.equals("password")) {
			/*
			 * // ??????????????????????????????ajax???POST???????????? if
			 * (!request.getMethod().equals(HttpMethod.POST.name())) {
			 * response.setStatus(HttpServletResponse.SC_NOT_FOUND); return
			 * result; }
			 */

			result = grantByPassword(request);

		}
		if (grantType.equals("refreshToken")) {

			result = grantByRefreshToken(request);
		}
		return result;
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel logout() {

		ResultModel result = new ResultModel();

		String header = request.getHeader(CustomSymbolic.JWT_HEADER_PARAM);

		header = header.substring(CustomSymbolic.JWT_HEADER_PREFIX.length(), header.length());

		Claims claims = TokenUtil.parserJavaWebToken(header, CustomSymbolic.JWT_SECRET).getBody();

		if (DateUtils.comparator(claims.getExpiration(), DateUtils.getCurrentTime()) < 0) {
			throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());
		}

		String loginLogId = (String) claims.get(CustomSymbolic.JWT_LOGIN_LOG_ID);

		ResultModel resultModel = gpTokenUntBll.getModel(loginLogId);
		GpToken gpToken = (GpToken) resultModel.getData();
		if (DateUtils.comparator(gpToken.getRDeadTime(), DateUtils.getCurrentTime()) < 0) {
			throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());
		}

		result = gpLoginLogUntBll.getModel(loginLogId);
		GpLoginLog gpLoginLog = (GpLoginLog) result.getData();
		gpLoginLog.setLogoutTime(DateUtils.getCurrentTime());
		gpLoginLog.setLogoutTypeCode(LogoutType.INITIATIVE.getCode());
		gpLoginLogUntBll.update(gpLoginLog, false);

		gpToken.setADeadTime(DateUtils.getCurrentTime());
		gpToken.setRDeadTime(DateUtils.getCurrentTime());
		gpTokenUntBll.update(gpToken, false);

		return result;

	}

	private ResultModel grantByRefreshToken(HttpServletRequest request) {
		String refreshToken = request.getParameter("refresh_token");
		if (StringUtils.isBlank(refreshToken)) {
			throw new GlobalException("refreshToken???????????????");
		}
		Claims claims = TokenUtil.parserJavaWebToken(refreshToken, CustomSymbolic.JWT_SECRET).getBody();
		if (DateUtils.comparator(claims.getExpiration(), DateUtils.getCurrentTime()) < 0) {
			throw new GlobalException("RefreshToken?????????????????????!");
		}
		String userName = claims.getSubject();
		String loginLogId = (String) claims.get(CustomSymbolic.JWT_LOGIN_LOG_ID);
		ResultModel resultModel = gpTokenUntBll.getModel(loginLogId);
		GpToken gpToken = (GpToken) resultModel.getData();
		if (DateUtils.comparator(gpToken.getRDeadTime(), DateUtils.getCurrentTime()) < 0) {
			throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());
		}

		resultModel = gpUserSplBll.getModelByUserName(userName);

		if (!resultModel.getIsSuccess()) {
			throw new GlobalException("?????????????????????");
		}
		GpUser user = (GpUser) resultModel.getData();
		if (StringUtils.isBlank(userName)) {
			throw new GlobalException("???????????????!");
		}

		String secret = TokenUtil.createSecret();
		secret = CustomSymbolic.JWT_SECRET;
		String accessToken = TokenUtil.createAccessToken(claims, secret);

		gpToken.setAccessToken(accessToken);
		gpToken.setADeadTime(DateUtils.addSecond(DateUtils.getCurrentTime(), CustomSymbolic.JWT_ACCESS_DEAD_TIME));

		// ?????????????????????GpToken??????
		ResultModel result = gpTokenUntBll.update(gpToken, false);

		result = gpLoginLogUntBll.getModel(loginLogId);
		GpLoginLog gpLoginLog = (GpLoginLog) result.getData();
		gpLoginLog.setLogoutTime(DateUtils.comparator(gpToken.getRDeadTime(), gpToken.getADeadTime()) < 0 ? gpToken.getADeadTime() : gpToken.getRDeadTime());
		// ???????????????????????????GpLoginLog??????
		result = gpLoginLogUntBll.update(gpLoginLog, false);
		if (!result.getIsSuccess())
			throw new GlobalException(result.getResultMessage());
		result = new ResultModel();
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		gpToken.setGpUser(user);
		result.setData(gpToken);
		return result;

	}

	private ResultModel grantByPassword(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String clientId = request.getParameter("client_id");
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			throw new GlobalException("?????????????????????????????????");
		}

		ResultModel resultModel = gpUserSplBll.getModelByUserName(userName);

		if (!resultModel.getIsSuccess()) {
			throw new GlobalException("?????????????????????");
		}
		GpUser user = (GpUser) resultModel.getData();

		if (!password.equals(user.getPassword())) {
			throw new GlobalException("????????????????????????");
		}
		if (user.getIsDisabledCode() == CustomSymbolic.DCODE_BOOLEAN_T) {
			throw new GlobalException("????????????????????????");
		}
		resultModel = gprDomainUserSplBll.isPermitted(user.getId(), clientId, false);
		if (!resultModel.getIsSuccess()) {
			throw new GlobalException("?????????????????????????????????");
		}
		// ??????GpLoginLog?????????????????????
		// ???????????????????????????logoutTime????????????????????????????????????domainId??????App??????????????????????????????

		/*************************????????????*******************************/
		resultModel = gpLoginLogSplBll.getListOnLine(userName, clientId);
		// ????????????????????????????????????????????????????????????GpToken??????????????????
		if (resultModel.getTotalCount() != 0) {
			@SuppressWarnings("unchecked")
			List<GpLoginLog> data = (List<GpLoginLog>) resultModel.getData();
			List<GpLoginLog> gpLoginLogList = data;
			for (GpLoginLog gpLoginLog : gpLoginLogList) {
				gpLoginLog.setLogoutTime(DateUtils.getCurrentTime());
				gpLoginLog.setLogoutTypeCode(LogoutType.PASSIVE.getCode());
				gpLoginLogUntBll.update(gpLoginLog, false);

				GpToken gpToken = (GpToken) gpTokenUntBll.getModel(gpLoginLog.getId()).getData();
				gpToken.setADeadTime(DateUtils.getCurrentTime());
				gpToken.setRDeadTime(DateUtils.getCurrentTime());
				gpTokenUntBll.update(gpToken, false);
			}

		}
		/*************************????????????*******************************/

		// ??????????????????
		GpToken gpToken = new GpToken();

		Claims claims = Jwts.claims();
		String loginLogId = Tools.getUUID();
		// ????????????????????????
		String secret = TokenUtil.createSecret();
		secret = CustomSymbolic.JWT_SECRET;
		claims.setSubject(userName);
		claims.put(CustomSymbolic.JWT_LOGIN_LOG_ID, loginLogId);
		claims.put(CustomSymbolic.JWT_DOMAIN_ID, clientId);

		String accessToken = TokenUtil.createAccessToken(claims, secret);
		String refreshToken = TokenUtil.createRefreshToken(claims, secret);

		gpToken.setId(loginLogId);
		gpToken.setAccessToken(accessToken);
		gpToken.setAddTime(DateUtils.getCurrentTime());
		gpToken.setADeadTime(DateUtils.addSecond(gpToken.getAddTime(), CustomSymbolic.JWT_ACCESS_DEAD_TIME));
		gpToken.setDomainId(clientId);
		gpToken.setRDeadTime(DateUtils.addSecond(gpToken.getADeadTime(), CustomSymbolic.JWT_ACCESS_DEAD_TIME));
		gpToken.setRefreshToken(refreshToken);
		gpToken.setRemark("");
		gpToken.setSecret(secret);
		gpToken.setUpdateTime(DateUtils.getCurrentTime());
		gpToken.setUserId(user.getId());
		gpToken.setUserName(user.getUserName());
		gpToken.getSecret();
		
		GpLoginLog gpLoginLog = new GpLoginLog();
		String ip = Tools.getIpAddr(request);
		String resolution = request.getParameter("resolution");
		gpLoginLog.setId(loginLogId);
		gpLoginLog.setBrowser(Tools.getBrowser(request));
		gpLoginLog.setDomainId(clientId);
		gpLoginLog.setDomainName(DomainEnum.getText(clientId));
		gpLoginLog.setDuration(0);
		gpLoginLog.setIp(ip);
		gpLoginLog.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		gpLoginLog.setLoginTime(DateUtils.getCurrentTime());
		gpLoginLog.setLogoutTime(gpToken.getRDeadTime());
		gpLoginLog.setLogoutTypeCode(LogoutType.TOKEN_EXPIRE.getCode());
		gpLoginLog.setOs(Tools.getOs(request));
		gpLoginLog.setRemark("");
		gpLoginLog.setResolution(resolution);
		gpLoginLog.setUserId(user.getId());
		gpLoginLog.setUserName(user.getUserName());
		// ?????????????????????GpLoginLog??????
		resultModel = gpLoginLogUntBll.add(gpLoginLog, false);
		if (!resultModel.getIsSuccess())
			throw new GlobalException(resultModel.getResultMessage());

		// ?????????????????????GpToken??????
		resultModel = gpTokenUntBll.add(gpToken, false);
		if (!resultModel.getIsSuccess())
			throw new GlobalException(resultModel.getResultMessage());
		
		// ????????????????????????????????????
		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginTime(gpLoginLog.getLoginTime());
		user.setLastLoginIp(gpLoginLog.getIp());
		gpUserUntBll.update(user);

		resultModel = new ResultModel();
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		if (StringUtils.isNotEmpty(user.getIcon()))
			user.setIcon(this.linkPath + user.getIcon());
		gpToken.setGpUser(user);
		resultModel.setData(gpToken);
		return resultModel;
	}

}
