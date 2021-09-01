package com.zee.app.extend.swagger.gp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zee.app.generate.swagger.gp.GpUserGenSwgApp;
import com.zee.bll.extend.split.gp.GprDomainUserSplBll;
import com.zee.bll.extend.split.gp.GprMessageUserSplBll;
import com.zee.bll.extend.split.gp.GprResourceSplBll;
import com.zee.bll.extend.split.gp.GprUserBaseSplBll;
import com.zee.bll.extend.split.gp.GprUserOrganizationSplBll;
import com.zee.bll.extend.split.gp.GprUserRoleSplBll;
import com.zee.bll.extend.split.gp.GprUserStationSplBll;
import com.zee.bll.extend.unity.gp.GpLoginLogUntBll;
import com.zee.bll.extend.unity.gp.GpMenuUntBll;
import com.zee.bll.extend.unity.gp.GpUserUntBll;
import com.zee.bll.extend.unity.gp.GprDomainUserUntBll;
import com.zee.bll.extend.unity.gp.GprResourceUntBll;
import com.zee.bll.extend.unity.gp.GprRoleDomainUntBll;
import com.zee.bll.extend.unity.gp.GprUserOrganizationUntBll;
import com.zee.bll.extend.unity.gp.GprUserRoleUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.ent.extend.gp.GpResource;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.extend.gp.GprResource;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.extend.gp.GprUserOrganization;
import com.zee.ent.extend.gp.GprUserRole;
import com.zee.ent.parameter.gp.GpUserParameter;
import com.zee.set.enumer.OperResult;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.set.symbolic.SqlSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DictionaryConvertUtil;
import com.zee.utl.MailSenderUtil;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统用户。 对外接口，扩展自GpUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpUser")
public class GpUserSwgApp extends GpUserGenSwgApp {

	/*
	 * @Autowired private Executors executors;
	 */

	@Autowired
	@Qualifier("gprUserRoleUntBll")
	protected GprUserRoleUntBll gprUserRoleUntBll;

	@Autowired
	@Qualifier("gprUserRoleSplBll")
	protected GprUserRoleSplBll gprUserRoleSplBll;

	@Autowired
	@Qualifier("gprRoleDomainUntBll")
	protected GprRoleDomainUntBll gprRoleDomainUntBll;

	@Autowired
	@Qualifier("gpLoginLogUntBll")
	protected GpLoginLogUntBll gpLoginLogUntBll;

	@Autowired
	@Qualifier("gpMenuUntBll")
	protected GpMenuUntBll gpMenuUntBll;
	@Autowired
	@Qualifier("gpUserUntBll")

	protected GpUserUntBll gpUserUntBll;

	@Autowired
	@Qualifier("gprResourceUntBll")
	protected GprResourceUntBll gprResourceUntBll;

	@Autowired
	@Qualifier("gprResourceSplBll")
	protected GprResourceSplBll gprResourceSplBll;

	@Autowired
	@Qualifier("gprUserStationSplBll")
	protected GprUserStationSplBll gprUserStationSplBll;

	@Autowired
	@Qualifier("gprUserBaseSplBll")
	protected GprUserBaseSplBll gprUserBaseSplBll;

	@Autowired
	protected GpResourceSwgApp gpResourceSwgApp;

	@Autowired
	private MailSenderUtil mailSenderUtil;

	@Autowired
	@Qualifier("gprUserOrganizationUntBll")
	protected GprUserOrganizationUntBll gprUserOrganizationUntBll;

	@Autowired
	@Qualifier("gprUserOrganizationSplBll")
	protected GprUserOrganizationSplBll gprUserOrganizationSplBll;

	@Autowired
	@Qualifier("gprDomainUserUntBll")
	protected GprDomainUserUntBll gprDomainUserUntBll;

	@Autowired
	@Qualifier("gprDomainUserSplBll")
	protected GprDomainUserSplBll gprDomainUserSplBll;

	@Autowired
	@Qualifier("gprMessageUserSplBll")
	protected GprMessageUserSplBll gprMessageUserSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpUser") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpUser jsonData) {
		if (StringUtils.isNotBlank(jsonData.getIconPaths())) {
			jsonData.setIconPaths(jsonData.getIconPaths().replaceAll(linkPath, ""));
			String[] resourcePathArray = jsonData.getIconPaths().split(",");
			if (resourcePathArray.length != 0)
				jsonData.setIcon(resourcePathArray[0]);
		}
		jsonData.setAddTime(new Date());
		jsonData.setUpdateTime(new Date());
		jsonData.setIsAdminCode(jsonData.getIsAdminCode() == null ? 1 : jsonData.getIsAdminCode());
		if (jsonData.getBirthTime() != null)
			jsonData.setAge((byte) Tools.getAgeByBirth(jsonData.getBirthTime()));
		jsonData.setRegisterIp(getCurrentUser().getLastLoginIp());
		ResultModel result = gpUserUntBll.add(jsonData);

		// 头像列表
		if (StringUtils.isNotBlank(jsonData.getIconIds())) {
			ArrayList<GprResource> gprResourceList = new ArrayList<GprResource>();
			String[] resourceArray = jsonData.getIconIds().split(",");
			for (int i = 0; i < resourceArray.length; i++) {
				GprResource gprResource = new GprResource();
				gprResource.setResourceId(resourceArray[i]);
				gprResource.setBusinessId(result.getObjectId());
				gprResource.setIsDefault(i == 0 ? CustomSymbolic.DCODE_BOOLEAN_T : CustomSymbolic.DCODE_BOOLEAN_F);
				gprResourceList.add(gprResource);
			}
			gprResourceUntBll.add(gprResourceList);
		}
		// 组织机构
		if (StringUtils.isNotBlank(jsonData.getOrgIds())) {
			ArrayList<GprUserOrganization> addOrgs = new ArrayList<GprUserOrganization>();
			for (String organizationId : jsonData.getOrgIds().split(",")) {
				GprUserOrganization gprUserOrganization = new GprUserOrganization();
				gprUserOrganization.setOrganizationId(organizationId);
				gprUserOrganization.setUserId(result.getObjectId());
				addOrgs.add(gprUserOrganization);
			}
			gprUserOrganizationUntBll.add(addOrgs);
		}

		String userId = result.getObjectId();

		// 应用领域
		ArrayList<GprDomainUser> gprDominUserList = new ArrayList<GprDomainUser>();
		if (StringUtils.isNotBlank(jsonData.getDomainIds())) {
			for (String domainId : jsonData.getDomainIds().split(",")) {
				GprDomainUser gprDomainUser = new GprDomainUser();
				gprDomainUser.setDomainId(domainId);
				gprDomainUser.setUserId(userId);
				gprDominUserList.add(gprDomainUser);
			}
		}
		gprDomainUserUntBll.add(gprDominUserList);

		// 用户角色
		if (StringUtils.isNotBlank(jsonData.getRoleIds())) {
			ArrayList<GprUserRole> arrayList = new ArrayList<GprUserRole>();
			if (StringUtils.isNotBlank(jsonData.getRoleIds())) {
				for (String roleId : jsonData.getRoleIds().split(",")) {
					GprUserRole gprUserRole = new GprUserRole();
					gprUserRole.setUserId(jsonData.getId());
					gprUserRole.setRoleId(roleId);
					arrayList.add(gprUserRole);
				}
			}
			gprUserRoleUntBll.add(arrayList);

			// 用户角色相关应用领域
			String sql = String.format(SqlSymbolic.SQL_SELECT_ROLE_DOMAIN_ID, "'" + jsonData.getRoleIds().replace(",", "','") + "'", result.getObjectId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Sql", sql);
			ResultModel resultModel = gprRoleDomainUntBll.getListBySQL(map);
			List<Map<String, Object>> domainIdList = CastObjectUtil.cast(resultModel.getData());
			gprDominUserList = new ArrayList<GprDomainUser>();
			for (Map<String, Object> domainId : domainIdList) {
				GprDomainUser gprDomainUser = new GprDomainUser();
				gprDomainUser.setDomainId(domainId.get("domain_id").toString());
				gprDomainUser.setUserId(result.getObjectId());
				gprDominUserList.add(gprDomainUser);
			}
			gprDomainUserUntBll.add(gprDominUserList);
		}

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {

		ResultModel result = gpUserUntBll.delete(id);
		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpUserDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpUserParameter.DeleteByIdList jsonData) {
		ArrayList<String> idList = jsonData.getIdList();
		gprResourceSplBll.deleteByBusinessIdList(idList);
		ResultModel result = gpUserUntBll.deleteByIdList(idList);
		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpUser") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpUser jsonData) {
		ResultModel result = new ResultModel();
		if (StringUtils.isNotBlank(jsonData.getIconPaths())) {
			jsonData.setIconPaths(jsonData.getIconPaths().replaceAll(linkPath, ""));
			String[] resourcePathArray = jsonData.getIconPaths().split(",");
			if (resourcePathArray.length != 0)
				jsonData.setIcon(resourcePathArray[0]);
		}

		if (jsonData.getBirthTime() != null)
			jsonData.setAge((byte) Tools.getAgeByBirth(jsonData.getBirthTime()));
		result = gpUserUntBll.update(jsonData);
		String userId = jsonData.getId();
		// 头像列表
		gprResourceSplBll.deleteByBusinessId(userId);
		if (StringUtils.isNotBlank(jsonData.getIconIds())) {
			ArrayList<GprResource> gprResourceList = new ArrayList<GprResource>();
			String[] resourceArray = jsonData.getIconIds().split(",");
			for (int i = 0; i < resourceArray.length; i++) {
				GprResource gprResource = new GprResource();
				gprResource.setResourceId(resourceArray[i]);
				gprResource.setBusinessId(result.getObjectId());
				gprResource.setIsDefault(i == 0 ? CustomSymbolic.DCODE_BOOLEAN_T : CustomSymbolic.DCODE_BOOLEAN_F);
				gprResourceList.add(gprResource);
			}
			gprResourceUntBll.add(gprResourceList);
		}
		// 组织机构
		gprUserOrganizationUntBll.deleteByUserId(userId);
		if (StringUtils.isNotBlank(jsonData.getOrgIds())) {
			ArrayList<GprUserOrganization> addOrgs = new ArrayList<GprUserOrganization>();
			for (String organizationId : jsonData.getOrgIds().split(",")) {
				GprUserOrganization gprUserOrganization = new GprUserOrganization();
				gprUserOrganization.setOrganizationId(organizationId);
				gprUserOrganization.setUserId(result.getObjectId());
				addOrgs.add(gprUserOrganization);
			}
			gprUserOrganizationUntBll.add(addOrgs);
		}

		// 应用领域
		gprUserRoleUntBll.deleteByUserId(userId);
		gprDomainUserUntBll.deleteByUserId(userId);

		ArrayList<GprDomainUser> gprDominUserList = new ArrayList<GprDomainUser>();
		if (StringUtils.isNotBlank(jsonData.getDomainIds())) {
			for (String domainId : jsonData.getDomainIds().split(",")) {
				GprDomainUser gprDomainUser = new GprDomainUser();
				gprDomainUser.setDomainId(domainId);
				gprDomainUser.setUserId(userId);
				gprDominUserList.add(gprDomainUser);
			}
		}
		gprDomainUserUntBll.add(gprDominUserList);

		// 用户角色
		if (StringUtils.isNotBlank(jsonData.getRoleIds())) {
			ArrayList<GprUserRole> arrayList = new ArrayList<GprUserRole>();
			if (StringUtils.isNotBlank(jsonData.getRoleIds())) {
				for (String roleId : jsonData.getRoleIds().split(",")) {
					GprUserRole gprUserRole = new GprUserRole();
					gprUserRole.setUserId(jsonData.getId());
					gprUserRole.setRoleId(roleId);
					arrayList.add(gprUserRole);
				}
			}
			gprUserRoleUntBll.add(arrayList);

			// 用户角色相关应用领域
			String sql = String.format(SqlSymbolic.SQL_SELECT_ROLE_DOMAIN_ID, "'" + jsonData.getRoleIds().replace(",", "','") + "'", result.getObjectId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Sql", sql);
			ResultModel resultModel = gprRoleDomainUntBll.getListBySQL(map);
			List<Map<String, Object>> domainIdList = CastObjectUtil.cast(resultModel.getData());
			gprDominUserList = new ArrayList<GprDomainUser>();
			for (Map<String, Object> domainId : domainIdList) {
				GprDomainUser gprDomainUser = new GprDomainUser();
				gprDomainUser.setDomainId(domainId.get("domain_id").toString());
				gprDomainUser.setUserId(result.getObjectId());
				gprDominUserList.add(gprDomainUser);
			}
			gprDomainUserUntBll.add(gprDominUserList);
		}

		return result;
	}

	@RequestMapping(value = "/resetPassword/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel resetPassword(@PathVariable("id") String id) {
		ResultModel result = gpUserSplBll.resetPassword(id);
		return result;
	}

	@RequestMapping(value = "/disableUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel disableUser(@PathVariable("id") String id) {
		ResultModel result = gpUserSplBll.disableUser(id);
		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpUserUntBll.getModel(id);
		if (!result.getIsSuccess())
			return result;

		GpUser gpUser = (GpUser) result.getData();

		List<Map<String, Object>> modelList = getUserRoleList(id);
		String roleIds = "";
		String roleNames = "";
		for (int i = 0; i < modelList.size(); i++) {
			roleIds += modelList.get(i).get("roleId");
			roleNames += modelList.get(i).get("roleName");
			if (i != modelList.size() - 1) {
				roleIds += ",";
				roleNames += ",";
			}
		}
		gpUser.setRoleIds(roleIds);
		gpUser.setRoleNames(roleNames);

		List<Map<String, Object>> userOrgList = getUserOrganizationList(id);
		String orgIds = "";
		for (int i = 0; i < userOrgList.size(); i++) {
			orgIds += userOrgList.get(i).get("organizationId");
			if (i != userOrgList.size() - 1) {
				orgIds += ",";
			}
		}
		gpUser.setOrgIds(orgIds);

		List<Map<String, Object>> userIconList = getUserIconList(id);
		String userIconIds = "";
		String userIconPaths = "";
		for (int i = 0; i < userIconList.size(); i++) {
			userIconIds += userIconList.get(i).get("iconId");
			userIconPaths += userIconList.get(i).get("iconPath");
			if (i == userIconList.size() - 1)
				break;
			userIconIds += ",";
			userIconPaths += ",";
		}
		gpUser.setIconIds(userIconIds);
		gpUser.setIconPaths(userIconPaths);

		List<Map<String, Object>> domainUserList = getUserDomainList(id);
		String userDomainIds = "";
		String userDomainNames = "";
		for (int i = 0; i < domainUserList.size(); i++) {
			userDomainIds += domainUserList.get(i).get("domainId");
			userDomainNames += domainUserList.get(i).get("domainName");
			if (i == domainUserList.size() - 1)
				break;
			userDomainIds += ",";
			userDomainNames += ",";
		}
		gpUser.setDomainIds(userDomainIds);
		gpUser.setDomainNames(userDomainNames);

		result.setData(gpUser);

		return result;
	}

	@RequestMapping(value = "/getCurrentUser", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByToken() {
		ResultModel result = new ResultModel();
		GpUser user = getCurrentUser();
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		result.setObjectId(user == null ? null : user.getId());
		result.setData(user);
		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select distinct A.id id,A.user_name userName,A.password password,A.real_name realName,A.gender_code genderCode,A.birth_time birthTime,A.age age,A.is_marriage_code isMarriageCode,A.phone phone,A.email email,A.qq qq,A.icon icon,A.register_ip registerIp,A.last_login_time lastLoginTime,A.last_login_ip lastLoginIp,A.login_count loginCount,A.remark remark,A.is_admin_code isAdminCode,A.is_disabled_code isDisabledCode,A.add_time addTime,A.update_time updateTime  from gp_user A left join gpr_user_organization B on A.id=B.user_id left join gp_organization C on B.organization_id=C.id left join gpr_user_role D on A.id=D.user_id left join gp_role E on D.role_id=E.id where 1=1 ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("kewwords") && StringUtils.isNotBlank(entityRelatedObject.getString("kewwords"))) {
					selectBuffer.append(String.format(" and(  A.user_name like %1$s or A.real_name like %1$s or A.phone like %1$s or A.email like %1$s  )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}
				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName")))
					selectBuffer.append(" and A.user_name like '%").append(entityRelatedObject.getString("userName")).append("%'");
				if (entityRelatedObject.containsKey("phone") && StringUtils.isNotBlank(entityRelatedObject.getString("phone")))
					selectBuffer.append(" and A.phone like '%").append(entityRelatedObject.getString("phone")).append("%'");
				if (entityRelatedObject.containsKey("realName") && StringUtils.isNotBlank(entityRelatedObject.getString("realName")))
					selectBuffer.append(" and A.real_name like '%").append(entityRelatedObject.getString("realName")).append("%'");
				if (entityRelatedObject.containsKey("organizationName") && StringUtils.isNotBlank(entityRelatedObject.getString("organizationName")))
					selectBuffer.append(" and C.name like '%").append(entityRelatedObject.getString("organizationName")).append("%'");
				if (entityRelatedObject.containsKey("roleIds") && StringUtils.isNotBlank(entityRelatedObject.getString("roleIds")))
					selectBuffer.append(" and find_in_set(E.id ,'").append(entityRelatedObject.getString("roleIds")).append("')>0");
			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if (jsonObject.containsKey("orderList")) {
				JSONArray orderListArray = jsonObject.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append("A." + Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpUserUntBll.getListBySQL(map);

		// 将Map转换成实体以解析字典编码
		// resultModel.setData(DictionaryConvertUtil.convertMapListToTextList(GpUser.class,
		// resultModel.getData()));

		return resultModel;
	}

	@ApiOperation(value = "查询邮箱是否重复")
	@RequestMapping(value = "/isUniqueEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel isUniqueEmail() {
		ResultModel result = new ResultModel();
		String email = request.getParameter("email");
		String oldEmail = request.getParameter("oldEmail");
		if (StringUtils.isNotEmpty(oldEmail) && email.equals(oldEmail)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setData(true);
			return result;
		}

		result = gpUserUntBll.isUniqueEmail(email);
		return result;
	}

	@ApiOperation(value = "查询用户名是否重复")
	@RequestMapping(value = "/isUniqueUserName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel isUniqueUserName() {
		ResultModel result = new ResultModel();
		String username = request.getParameter("userName");
		String oldUserName = request.getParameter("oldUserName");
		if (StringUtils.isNotEmpty(oldUserName) && username.equals(oldUserName)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setData(true);
			return result;
		}
		result = gpUserUntBll.isUniqueUserName(username);
		return result;
	}

	@ApiOperation(value = "查询手机号是否重复")
	@RequestMapping(value = "/isUniquePhone", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel isUniquePhone() {
		ResultModel result = new ResultModel();
		String phone = request.getParameter("phone");
		String oldPhone = request.getParameter("oldPhone");
		if (StringUtils.isNotEmpty(oldPhone) && phone.equals(oldPhone)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setData(true);
			return result;
		}

		result = gpUserUntBll.isUniquePhone(phone);
		return result;
	}

	@ApiOperation(value = "查询QQ号是否重复")
	@RequestMapping(value = "/isUniqueQQ", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel isUniqueQQ() {
		ResultModel result = new ResultModel();
		String qq = request.getParameter("qq");
		String oldQq = request.getParameter("oldQq");
		if (StringUtils.isNotEmpty(oldQq) && qq.equals(oldQq)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setData(true);
			return result;
		}
		result = gpUserUntBll.isUniqueQq(qq);
		return result;
	}

	private List<Map<String, Object>> getUserRoleList(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			A.id,                                                 ");
		selectBuffer.append("			A.user_id userId,                                     ");
		selectBuffer.append("			A.role_id roleId,                                    ");
		selectBuffer.append("			B.name roleName                                       ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("			gpr_user_role A                                       ");
		selectBuffer.append("		INNER JOIN gp_role B ON A.role_id = B.id                  ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.user_id = '" + userId + "'        					  ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprUserRoleUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		return modelList;
	}

	private List<Map<String, Object>> getUserOrganizationList(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		A.id id,                                                   ");
		selectBuffer.append("		A.organization_id organizationId,                          ");
		selectBuffer.append("		A.user_id userId                                           ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		gpr_user_organization A                                    ");
		selectBuffer.append("	WHERE                                                          ");
		selectBuffer.append("		A.user_id = '" + userId + "'                                   ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprUserRoleUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		return modelList;
	}

	private List<Map<String, Object>> getUserIconList(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			B.id iconId,                                     ");
		selectBuffer.append("			CONCAT('" + this.linkPath + "',B.path) iconPath ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("		gpr_resource A");
		selectBuffer.append("		INNER JOIN gp_resource B ON A.resource_id = B.id                  ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.business_id = '" + userId + "'        					  ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprResourceUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		return modelList;
	}

	private List<Map<String, Object>> getUserDomainList(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			B.id domainId,                                     ");
		selectBuffer.append("			B.name domainName                                    ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("		gpr_domain_user A");
		selectBuffer.append("		INNER JOIN gp_domain B ON A.domain_id=B.id                  ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.user_id = '" + userId + "'        					  ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprDomainUserUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		return modelList;
	}

	private List<Map<String, Object>> getIconList(String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			A.id                                                  ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("			gpr_resource A                                       ");
		selectBuffer.append("		INNER JOIN gp_resource B ON A.resource_id = B.id          ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.business_id = '" + userId + "' and is_default = '0'     ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprResourceUntBll.getListBySQL(map);
		List<Map<String, Object>> iconList = CastObjectUtil.cast(resultModel.getData());
		return iconList;
	}

	/**
	 * 修改用户头像关系表
	 * @param userId
	 * @param jsonData
	 */
	private void updateUserIcon(String userId, String resourceId) {
		// 修改目前默认头像为非默认，再新增此默认头像
		GprResource gprResource;
		List<Map<String, Object>> iconList = getIconList(userId);
		for (Map<String, Object> map2 : iconList) {
			String id = map2.get("id").toString();
			gprResource = new GprResource();
			gprResource.setId(id);
			gprResource.setBusinessId(userId);
			gprResource.setIsDefault(CustomSymbolic.DCODE_BOOLEAN_F);
			gprResourceUntBll.update(gprResource);

		}

		if (StringUtils.isNotBlank(resourceId)) {
			gprResource = new GprResource();
			gprResource.setBusinessId(userId);
			gprResource.setResourceId(resourceId);
			gprResource.setIsDefault(CustomSymbolic.DCODE_BOOLEAN_T);
			gprResourceUntBll.add(gprResource);
		}
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@RequestMapping(value = "/updateForApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateForApp(MultipartHttpServletRequest multipartRequest) {

		ResultModel resultModel = new ResultModel();
		GpUser currentUser = this.getCurrentUser();

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		ResultModel resultResource = new ResultModel();
		if (!fileMap.isEmpty()) {
			resultResource = gpResourceSwgApp.saveUploadFile(multipartRequest);
			ArrayList<GpResource> resList = (ArrayList<GpResource>) resultResource.getData();
			// 修改用户头像关系表
			for (GpResource gpResource : resList) {
				updateUserIcon(currentUser.getId(), gpResource.getId());
			}
		}

		String realName = multipartRequest.getParameter("realName");
		currentUser.setRealName(realName);
		resultModel = gpUserUntBll.update(currentUser);

		resultModel.setData(resultResource.getData());

		return resultModel;
	}

	@ApiOperation(value = "查询当前登陆的用户信息", notes = "查询当前登陆的用户信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getCurrenUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getCurrenUserInfo() {
		ResultModel resultModel = new ResultModel();
		GpUser gpUser = this.getCurrentUser();
		resultModel.setData(gpUser);
		return resultModel;
	}

	@ApiOperation(value = "查询当前登陆的用户信息forapp", notes = "查询当前登陆的用户信息forapp")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getCurrenUserForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getCurrenUserForApp() {
		ResultModel resultModel = new ResultModel();

		GpUser currentUser = this.getCurrentUser();
		String id = currentUser.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append("  select A.id userId,A.user_name userName,A.real_name realName,B.address,C.path as icon,                         ");
		selectBuffer.append("  if(E.enterprise_name,null,'') enterpriseName,C.resource_id resourceId FROM gp_user A LEFT JOIN da_peasant_info B           ");
		selectBuffer.append("  ON A.id = B.user_id LEFT JOIN (select a.id,b.path,c.resource_id from gp_user a,gp_resource b,gpr_user_icon c   ");
		selectBuffer.append("  where a.id = c.user_id and b.id = c.resource_id and c.is_default = '0' ) C ON A.id = C.id LEFT JOIN            ");
		selectBuffer.append("  (select a.id,b.id organization_id from gp_user a,gp_organization b,gpr_user_organization c                     ");
		selectBuffer.append("  where a.id = c.user_id and b.id = c.organization_id) D ON A.id = D.id LEFT JOIN da_enterprise_info E           ");
		selectBuffer.append("  ON D.organization_id = E.organization_id where A.id = '" + id + "'                                             ");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpUserUntBll.getListBySQL(map);
		List<Map<String, Object>> data = CastObjectUtil.cast(resultModel.getData());
		maps.put("data", data);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> basicdataMap = new HashMap<String, Object>();

		for (Map<String, Object> map1 : maps.get("data")) {
			basicdataMap.put("userId", map1.get("userId").toString());
			basicdataMap.put("userName", map1.get("userName").toString());
			basicdataMap.put("realName", map1.get("realName").toString());
			String icon = "";
			String address = "";
			String enterpriseName = "";
			String resourceId = "";
			if (map1.get("icon") != null) {
				icon = linkPath + map1.get("icon").toString();
			}
			if (map1.get("icon") != null) {
				address = map1.get("address").toString();
			}
			if (map1.get("icon") != null) {
				enterpriseName = map1.get("enterpriseName").toString();
			}
			if (map1.get("icon") != null) {
				resourceId = map1.get("resourceId").toString();
			}
			basicdataMap.put("icon", icon);
			basicdataMap.put("address", address);
			basicdataMap.put("enterpriseName", enterpriseName);
			basicdataMap.put("resourceId", resourceId);
		}

		resultMap.put("basicinfo", basicdataMap);
		resultModel.setData(resultMap);

		return resultModel;
	}

	@ApiOperation(value = "用户登录校验ForApp", notes = "根据用户名和密码对用户登录进行校验ForApp")
	@RequestMapping(value = "/getLoginInfoByJsonDataForApp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getLoginInfoByJsonDataForApp() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String userName = "";// 用户名
		String password = "";// 密码
		String domainId = "";// 领域名

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                               ");
		selectBuffer.append("		A.user_name userName,                                            ");
		selectBuffer.append("		A.password password 	                                         ");
		selectBuffer.append("	FROM                                                                 ");
		selectBuffer.append("		gp_user A                                                        ");
		selectBuffer.append("	LEFT JOIN gpr_domain_user B ON A.id = B.user_id                      ");
		selectBuffer.append("	WHERE                                                                ");
		selectBuffer.append("		1 = 1                                                            ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName"))) {
					userName = entityRelatedObject.getString("userName");
					selectBuffer.append(" and A.user_name = '").append(userName).append("'");
				}
				if (entityRelatedObject.containsKey("domainId") && StringUtils.isNotBlank(entityRelatedObject.getString("domainId"))) {
					domainId = entityRelatedObject.getString("domainId");
					selectBuffer.append(" and B.domain_id = '").append(domainId).append("'");
				}
				if (entityRelatedObject.containsKey("password") && StringUtils.isNotBlank(entityRelatedObject.getString("password"))) {
					password = entityRelatedObject.getString("password");
				}
			}
		}

		// 设置limit只查一条
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("pageIndex", "1");
		jsonObj.put("pageSize", "1");
		map.put("Page", jsonObj);

		map.put("Sql", selectBuffer.toString());
		resultModel = gpUserUntBll.getListBySQL(map);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		if (modelList.size() > 0) {
			if (!password.equals(modelList.get(0).get("password").toString())) {
				resultModel.setResultMessage("输入的密码有误！");
			} else {
				if (!userName.equals(modelList.get(0).get("userName").toString())) {
					resultModel.setResultMessage("输入的用户名有误，请注意区分大小写！");
				} else if (!domainId.equals("9e47d87dd1706cf7710e6fcfe0e6e0a7")) {
					resultModel.setResultMessage("该用户没有登录此app权限！");
				} else {
					resultModel.setResultMessage("登录成功！");
					resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
					// 获取第一个进入的页面
					// modelList.get(0).putAll(getFstPage(userName));
					// 处理用户登陆信息
					String token = dealLogin(modelList.get(0));
					modelList.get(0).put("Authorization", token);
				}
			}
		} else {
			resultModel.setResultMessage("输入的用户名不存在！");
		}
		return resultModel;
	}

	private String dealLogin(Map<String, Object> modelMap) {
		String token = "Bearer " + Tools.getUUID();// 生成token,暂时使用uuid，后期结合加密

		modelMap.put("token", token);
		modelMap.putAll(Tools.getLoginInfo(request));
		// 使用多线程处理用户登陆的其他信息并插入mysql
		// executors.dealLogin(modelMap);
		// redis存放用户登陆信息
		// redisUtil.setSessionData(modelMap);
		return token;
	}

	@ApiOperation(value = "用户注册", notes = "注册一个新用户")
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultModel register() throws Exception {

		ResultModel resultModel = new ResultModel();
		String jsonData = new String(request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON).getBytes("ISO-8859-1"), "UTF-8");

		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String realName = "";
		String email = "";
		String userName = "";
		String password = "";

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			// 设置pi_content相关数据
			GpUser gpUser = new GpUser();
			if (jsonObject.containsKey("fullname")) {
				realName = jsonObject.getString("fullname");
				gpUser.setRealName(realName);
			}
			if (jsonObject.containsKey("email")) {
				email = jsonObject.getString("email");
				gpUser.setEmail(email);
			}
			if (jsonObject.containsKey("username")) {
				userName = jsonObject.getString("username");
				gpUser.setUserName(userName);
			}
			if (jsonObject.containsKey("password")) {
				password = jsonObject.getString("password");
				gpUser.setPassword(password);
			}
			resultModel = gpUserUntBll.add(gpUser);
		}

		return resultModel;
	}

	/**
	 * 接收用户邮箱、发送验证码
	 * @param toMailAddress 用户邮箱
	 * @return
	 */
	@ApiOperation(value = "发送重置密码邮件", notes = "根据邮箱地址发送重置密码邮件")
	@RequestMapping(value = "/sendResetMailByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel sendResetMailByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                               ");
		selectBuffer.append("		A.id id,                                                         ");
		selectBuffer.append("		A.user_name userName,                                            ");
		selectBuffer.append("		A.email email			                                         ");
		selectBuffer.append("	FROM                                                                 ");
		selectBuffer.append("		gp_user A                                                        ");
		selectBuffer.append("	INNER JOIN gp_user B ON A.id = B.id                                  ");
		selectBuffer.append("	WHERE                                                                ");
		selectBuffer.append("		1 = 1                                                            ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("toMailAddress") && StringUtils.isNotBlank(entityRelatedObject.getString("toMailAddress"))) {
					selectBuffer.append(" and A.email = '").append(entityRelatedObject.getString("toMailAddress")).append("'");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpUserUntBll.getListBySQL(map);

		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());

		if (resultModel.getTotalCount() == 0) {
			resultModel.setResultMessage("您输入的邮箱未注册！");
			resultModel.setTotalCount(0);
			resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			resultModel.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
		} else if (resultModel.getTotalCount() >= 1) {
			if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				String mail = entityRelatedObject.getString("toMailAddress");
				String subject = "芒果产业大数据平台-找回密码";
				String code = getRandomNumber();
				String content = "您的账户正在进行找回密码，验证码为：" + code + "，请妥善保存，以免影响您的账户安全。";
				// String url = "<a href='"+"resetUrl"+"'>"+resetUrl+"</a>";
				mailSenderUtil.mailSimple(mail, subject, content);
				modelList.get(0).put("code", code);
				resultModel.setData(modelList);
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

				resultModel.setResultMessage("邮件发送成功，请登录邮箱查看验证码！");
			}
		}

		return resultModel;
	}

	/**
	 * 生成随机数验证码
	 * @return
	 */
	public static String getRandomNumber() {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%02d", r.nextInt(1000)));
		sb.append(String.format("%02d", r.nextInt(1000)));
		return sb.toString();
	}

	/**
	 * 设置新密码
	 * @param userName 用户名
	 * @param toMailAddress 用户预留邮箱
	 * @param passWord 用户要重置的新密码
	 */
	@ApiOperation(value = "设置新密码", notes = "设置新密码")
	@RequestMapping(value = "/updatePassWord", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updatePassWord() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (!StringUtils.isNotEmpty(entityRelatedObject.getString("newPassWord"))) {
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				resultModel.setResultMessage("新密码不能为空！");
				return resultModel;
			} else if (!StringUtils.isNotEmpty(entityRelatedObject.getString("rpnewpassword"))) {
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				resultModel.setResultMessage("请重复输入的新密码！");
				return resultModel;
			} else if (!entityRelatedObject.getString("newPassWord").equals(entityRelatedObject.getString("rpnewpassword"))) {
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				resultModel.setResultMessage("两次输入的密码不一致！");
				return resultModel;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                               ");
		selectBuffer.append("		A.id id,                                                         ");
		selectBuffer.append("		A.user_name userName,                                            ");
		selectBuffer.append("		A.password password,	                                         ");
		selectBuffer.append("		A.email email			                                         ");
		selectBuffer.append("	FROM                                                                 ");
		selectBuffer.append("		gp_user A                                                        ");
		selectBuffer.append("	INNER JOIN gp_user B ON A.id = B.id                                  ");
		selectBuffer.append("	WHERE                                                                ");
		selectBuffer.append("		1 = 1                                                            ");

		if (!StringUtils.isBlank(jsonData)) {

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName"))) {
					selectBuffer.append(" and A.user_name = '").append(entityRelatedObject.getString("userName")).append("'");
				}
				if (entityRelatedObject.containsKey("id") && StringUtils.isNotBlank(entityRelatedObject.getString("id"))) {
					selectBuffer.append(" and A.id = '").append(entityRelatedObject.getString("id")).append("'");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpUserUntBll.getListBySQL(map);

		if (resultModel.getTotalCount() == 0) {
			resultModel.setResultMessage("重置密码失败！");
			resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			resultModel.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
		} else if (resultModel.getTotalCount() >= 1) {

			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			String id1 = modelList.get(0).get("id").toString();

			ResultModel result = gpUserUntBll.getModel(id1);
			GpUser gpUser = (GpUser) result.getData();

			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			String qq = entityRelatedObject.getString("newPassWord");
			gpUser.setPassword(qq);
			resultModel = gpUserUntBll.update(gpUser);
			resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			resultModel.setResultMessage("密码重置成功，请返回登录页面进行登录！");
		}
		return resultModel;
	}

	@ApiOperation(value = "注册，用户名排重", notes = "根据用户名查询注册用户信息")
	@ApiImplicitParam(paramType = "path", name = "userName", value = "userName", required = true, dataType = "String")
	@RequestMapping(value = "/userNameVerify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel userNameVerify() {
		ResultModel resultModel = new ResultModel();
		String uName = request.getParameter("userName");

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			A.id,                                                 ");
		selectBuffer.append("			A.user_name userName                                  ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("			gp_user A                                       	  ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.user_name = '" + uName + "'        					  ");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpUserUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "注册，邮箱排重", notes = "根据邮箱查询注册用户信息")
	@ApiImplicitParam(paramType = "path", name = "email", value = "email", required = true, dataType = "String")
	@RequestMapping(value = "/emailVerify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel emailVerify() {
		ResultModel resultModel = new ResultModel();
		String email = request.getParameter("email");

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                    ");
		selectBuffer.append("			A.id,                                                 ");
		selectBuffer.append("			A.email email		                                  ");
		selectBuffer.append("		FROM                                                      ");
		selectBuffer.append("			gp_user A                                       	  ");
		selectBuffer.append("		WHERE                                                     ");
		selectBuffer.append("			A.email = '" + email + "'        					  	  ");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpUserUntBll.getListBySQL(map);

		return resultModel;
	}

}
