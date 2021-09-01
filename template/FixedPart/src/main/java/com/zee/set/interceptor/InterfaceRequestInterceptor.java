package com.zee.set.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zee.bll.extend.split.gp.GpInterfaceSplBll;
import com.zee.bll.extend.split.gp.GpUserSplBll;
import com.zee.bll.extend.split.gp.GprDomainUserSplBll;
import com.zee.bll.extend.unity.gp.GpDomainUntBll;
import com.zee.bll.extend.unity.gp.GpTokenUntBll;
import com.zee.bll.extend.unity.gp.GpUserUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.enumer.OperResult;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.TokenUtil;

import io.jsonwebtoken.Claims;

/**
 * @author Zee
 * @createDate 2017年4月14日 下午2:58:58
 * @updateDate 2017年4月14日 下午2:58:58
 * @description 访问权限验证。RESTful是无状态的，所以每次请求就需要对起进行认证和授权。
 * Token比较像是一个更加精简的自定义的Session。Session的主要功能是保持会话信息，
 * 而Token则只用于登录用户的身份鉴权。所以在移动端使用Token会比使用Session更加简易并且有更高的安全性，
 * 同时也更加符合RESTful中无状态的定义。
 * <p>
 * 1、客户端通过登录请求提交用户名和密码， 服务端验证通过后生成一个Token与该用户进行关联，并将Token返回给客户端。
 * 2、客户端在接下来的请求中都会携带Token，服务端通过解析Token检查登录状态。
 * 3、当用户退出登录、其他终端登录同一账号（被顶号）、长时间未进行操作时Token会失效，这时用户需要重新登录。
 * <p>
 * 1、登录请求一定要使用HTTPS，否则无论Token做的安全性多好密码泄露了也是白搭
 * 2、Token的生成方式有很多种，例如比较热门的有JWT（JSON Web Tokens）、OAuth等。
 */
@Component
public class InterfaceRequestInterceptor implements HandlerInterceptor {

    @Resource(name = "gpTokenUntBll")
    protected GpTokenUntBll gpTokenUntBll;

    @Resource(name = "gpUserUntBll")
    protected GpUserUntBll gpUserUntBll;

    @Resource(name = "gpUserSplBll")
    protected GpUserSplBll gpUserSplBll;

    @Resource(name = "gpInterfaceSplBll")
    protected GpInterfaceSplBll gpInterfaceSplBll;

    @Resource(name = "gpDomainUntBll")
    protected GpDomainUntBll gpDomainUntBll;

    @Resource(name = "gprDomainUserSplBll")
    protected GprDomainUserSplBll gprDomainUserSplBll;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader(CustomSymbolic.JWT_HEADER_PARAM);
        // 如果是预检请求返回OK
        if (CorsUtils.isPreFlightRequest(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        uri = uri.substring(contextPath.length(), uri.length());
        //如果路径后面有uuid截取
        if (uri.matches("/(.*)/[0-9a-f]{32}"))
            uri = uri.substring(0, uri.length() - 32);
        ResultModel interfaceResult = gpInterfaceSplBll.getModelByUrl(uri);

        // 如果在接口白名单中直接放行
        List<String> permitUrlList = Arrays.asList("/extend/swagger/da/daMarketPrice/getHomePageDataForApp", "/extend/swagger/da/daMarketPrice/getNewsDataForApp", "/extend/swagger/pi/piContent/getContentListForApp", "/extend/swagger/pi/piContent/getContentForApp", "/extend/swagger/pi/piInteraction/addInteraction");

        if (permitUrlList.contains(uri)) {
            return true;
        }

        // 如果没有带Token且接口没有在数据库表中记录，则不受权限控制，放行
        if (StringUtils.isBlank(header) && !interfaceResult.getIsSuccess()) {
            return true;
        }
        GpInterface gpInterface = (GpInterface) interfaceResult.getData();
        // 如果没有带Token且接口为公共接口，放行。
        if (StringUtils.isBlank(header) && gpInterface.getIsPublicCode() == CustomSymbolic.DCODE_BOOLEAN_T) {
            return true;
        }

        // 如果携带Token无论接口是否为公共，都向下走取Token中的信息
        if (StringUtils.isBlank(header)) {
            // 没有取到Token信息，抛出异常。
            throw new GlobalException("未接收到AccessToken信息!");
        }

        if (header.length() < CustomSymbolic.JWT_HEADER_PREFIX.length()) {
            // Token信息不正确，抛出异常。
            throw new GlobalException("AccessToken信息不正确!");
        }

        header = header.substring(CustomSymbolic.JWT_HEADER_PREFIX.length(), header.length());

        Claims claims = TokenUtil.parserJavaWebToken(header, CustomSymbolic.JWT_SECRET).getBody();
        // 和自身记录的过期时间校验，Token过期，抛出异常
        if (DateUtils.comparator(claims.getExpiration(), DateUtils.getCurrentTime()) < 0) {
            throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());
        }

        String userName = claims.getSubject();
        String loginLogId = (String) claims.get(CustomSymbolic.JWT_LOGIN_LOG_ID);
        String domainId = (String) claims.get(CustomSymbolic.JWT_DOMAIN_ID);

        ResultModel tokenResult = gpTokenUntBll.getModel(loginLogId);
        // 根据登录日志没有取到相应的Token，说明后台数据发生人为更改，认为登录过期重新登录
        if (!tokenResult.getIsSuccess())
            throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());
        GpToken gpToken = (GpToken) tokenResult.getData();

        // 和数据库记录的过期时间校验，Token过期，抛出异常
        if (gpToken == null||DateUtils.comparator(gpToken.getRDeadTime(), DateUtils.getCurrentTime()) < 0)
            throw new GlobalException(OperResult.TOKEN_EXPIRED.getCode(), OperResult.TOKEN_EXPIRED.getText());


        GpDomain domain = (GpDomain) gpDomainUntBll.getModel(domainId).getData();
        GpUser user = (GpUser) gpUserSplBll.getModelByUserName(userName).getData();

        if (user.getIsDisabledCode() == CustomSymbolic.DCODE_BOOLEAN_T) {
            // 当前登录用户已被注销（禁止登录），抛出异常
            throw new GlobalException("您的账号已被注销，无权访问本接口！" + uri);
        }

        ResultModel gprDomainUserResult = gprDomainUserSplBll.isPermitted(user.getId(), domainId, false);
        if (!gprDomainUserResult.getIsSuccess()) {
            throw new GlobalException("该用户无权访问本系统！");
        }
        user.setCurrentDomain(domain);
        ResultModel isPermittedResult = gpInterfaceSplBll.isPermitted(user.getId(), uri);

        if (!isPermittedResult.getIsSuccess()) {
            // 当前登录用户没有访问请求接口的权限，抛出异常
            throw new GlobalException("您无权访问本接口！" + uri);
        }
        request.setAttribute(CustomSymbolic.REQUEST_CURRENT_USER, user);
        request.setAttribute(CustomSymbolic.REQUEST_CURRENT_DOMAIN, domain);
        return true;
    }

}