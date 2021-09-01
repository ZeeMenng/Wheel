package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpModuleGenSwgApp;
import com.zee.bll.extend.split.gp.GpResourceSplBll;
import com.zee.bll.extend.split.gp.GprResourceSplBll;
import com.zee.bll.extend.unity.gp.GpDomainUntBll;
import com.zee.bll.extend.unity.gp.GpMenuUntBll;
import com.zee.bll.extend.unity.gp.GprResourceUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpResource;
import com.zee.ent.extend.gp.GprResource;
import com.zee.ent.parameter.gp.GpDomainParameter;
import com.zee.ent.parameter.gp.GpModuleParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.ClassFieldNullable;
import com.zee.utl.DateUtils;
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
 * @description 功能模块。 对外接口，扩展自GpModuleGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpModule")
@Scope("prototype")
public class GpModuleSwgApp extends GpModuleGenSwgApp {

	@Autowired
	@Qualifier("gpMenuUntBll")
	protected GpMenuUntBll gpMenuUntBll;

	@Autowired
	@Qualifier("gpDomainUntBll")
	protected GpDomainUntBll gpDomainUntBll;

	@Autowired
	@Qualifier("gpResourceSplBll")
	protected GpResourceSplBll gpResourceSplBll;

	@Autowired
	@Qualifier("gprResourceUntBll")
	protected GprResourceUntBll gprResourceUntBll;

	@Autowired
	@Qualifier("gprResourceSplBll")
	protected GprResourceSplBll gprResourceSplBll;

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {

		ResultModel result = gpModuleUntBll.delete(id);// 删除模块
		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpModuleDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpModuleParameter.DeleteByIdList jsonData) {
		ResultModel result = gpModuleUntBll.deleteByIdList(jsonData.getIdList());// 删除模块

		return result;
	}

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpModule") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpModule jsonData) {
		jsonData.setAddTime(new Date());
		jsonData.setUpdateTime(new Date());
		ResultModel result = gpModuleUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpModule") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpModule jsonData) {

		if (StringUtils.isNotBlank(jsonData.getIconPaths())) {
			jsonData.setIconPaths(jsonData.getIconPaths().replaceAll(linkPath, ""));
			String[] resourcePathArray = jsonData.getIconPaths().split(",");
			if (resourcePathArray.length != 0)
				jsonData.setIconResource(resourcePathArray[0]);
			else
				jsonData.setIconResource(null);
		}

		jsonData.setUpdateTime(new Date());
		if (StringUtils.isBlank(jsonData.getFartherId())) {
			jsonData.setFartherId(null);
		}
		GpModule gpModule = ClassFieldNullable.convertNull(jsonData, new ArrayList<String>() {
			{
				add("style");
				add("iconResource");
			}
		});

		ResultModel result = gpModuleUntBll.update(gpModule);

		// 头像列表
		gprResourceSplBll.deleteByBusinessId(result.getObjectId());
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

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpModuleUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpModuleParameter.UpdateList jsonData) {
		jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
		if (StringUtils.isBlank(jsonData.getEntity().getFartherId())) {
			jsonData.getEntity().setFartherId(null);
		}
		ResultModel result = gpModuleUntBll.updateList(jsonData);
		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "GpModuleAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateListWithDff(@RequestBody GpModuleParameter.AddList jsonData) {
		ArrayList<GpModule> moduleList = jsonData.getEntityList();
		ArrayList<GpModule> list = ClassFieldNullable.convertNull(moduleList, new ArrayList<String>() {
			{
				add("fartherId");
			}
		});

		ResultModel result = gpModuleUntBll.updateListWithDff(list);
		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "GpModuleAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateListWithDffOrAdd(@RequestBody GpModuleParameter.AddList jsonData) {
		ArrayList<GpModule> moduleList = jsonData.getEntityList();

		ResultModel result = gpModuleUntBll.addListWithDffOrAdd(moduleList);
		return result;
	}

	@RequestMapping(value = "/updateListByJsonData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateListByJsonData() {

		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		// 这个接口暂时无人使用，先给一个空值。
		String doaminId = null;
		ArrayList<GpModule> moduleList = Tools.getModuleListFromJsonString(doaminId, jsonData);
		resultModel = gpModuleSplBll.updateDomainModules(moduleList);

		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = new ResultModel();

		result = gpResourceSplBll.getListByBusinessId(id);
		ArrayList<GpResource> iconResuourceList = CastObjectUtil.cast(result.getData());
		result = gpModuleUntBll.getModel(id);

		GpModule gpModule = CastObjectUtil.cast(result.getData());

		String userIconIds = "";
		String userIconPaths = "";
		for (int i = 0; i < iconResuourceList.size(); i++) {
			userIconIds += iconResuourceList.get(i).getId();
			userIconPaths += (this.linkPath + iconResuourceList.get(i).getPath());
			if (i == iconResuourceList.size() - 1)
				break;
			userIconIds += ",";
			userIconPaths += ",";
		}
		gpModule.setIconIds(userIconIds);
		gpModule.setIconPaths(userIconPaths);
		result.setData(gpModule);
		return result;
	}

	@ApiOperation(value = "获取树状结构数据", notes = "获取树状结构数据")
	@RequestMapping(value = "/getTreeNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTreeNodes() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                               ");
		selectBuffer.append("		A.id id,                                         ");
		selectBuffer.append("		C.name name,                                     ");
		selectBuffer.append("		B.id fartherId,                                  ");
		selectBuffer.append("		B.name fartherName,                              ");
		selectBuffer.append("		A.level level,                          ");
		selectBuffer.append("		A.priority priority                              ");
		selectBuffer.append("	FROM                                                 ");
		selectBuffer.append("		gp_module A                                      ");
		selectBuffer.append("	LEFT JOIN gp_module B ON A.farther_id = B.id         ");
		selectBuffer.append("	LEFT JOIN gp_menu C ON C.module_id = A.id            ");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpModuleUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : modelList) {
			Map<String, Object> treeMap = new HashMap<String, Object>();
			treeMap.put("id", map2.get("id"));
			treeMap.put("pId", map2.get("fartherId"));
			treeMap.put("name", map2.get("name"));
			if (!"3".equals(map2.get("level").toString())) {
				treeMap.put("open", true);
			}
			treeNodes.add(treeMap);
		}
		resultModel.setData(treeNodes);
		return resultModel;
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
		selectBuffer.append("	SELECT                                              ");
		selectBuffer.append("		A.id id,                                        ");
		selectBuffer.append("		A.name name,                                    ");
		selectBuffer.append("		B.id fartherId,                                 ");
		selectBuffer.append("		B.name fartherName,                             ");
		selectBuffer.append("		C.name menuName,                                ");
		selectBuffer.append("		A.level level,                         ");
		selectBuffer.append("		A.priority priority                             ");
		selectBuffer.append("	FROM                                                ");
		selectBuffer.append("		gp_module A                                     ");
		selectBuffer.append("	LEFT JOIN gp_module B ON A.farther_id = B.id        ");
		selectBuffer.append("	LEFT JOIN gp_menu C ON C.module_id = A.id           ");
		selectBuffer.append("	WHERE                                               ");
		selectBuffer.append("		1 = 1                                           ");

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

				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("level") && StringUtils.isNotBlank(entityRelatedObject.getString("level")))
					selectBuffer.append(" and A.level = '").append(entityRelatedObject.getString("level")).append("'");
				if (entityRelatedObject.containsKey("fartherId") && StringUtils.isNotBlank(entityRelatedObject.getString("fartherId")))
					selectBuffer.append(" and A.farther_id = '").append(entityRelatedObject.getString("fartherId")).append("'");
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
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpModuleUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/getListByUserId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByUserId(@PathVariable("userId") String userId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                               ");
		selectBuffer.append("	distinct	C.id id,                                         ");
		selectBuffer.append("		C.name name,                                     ");
		selectBuffer.append("		C.farther_id fartherId,                          ");
		selectBuffer.append("		C.domain_id domainId,                          ");
		selectBuffer.append("		C.level level,                          ");
		selectBuffer.append("		C.priority priority                              ");
		selectBuffer.append("	FROM                                                 ");
		selectBuffer.append("		gpr_user_role A                                  ");
		selectBuffer.append("   INNER JOIN gpr_role_module B on A.role_id=B.role_id   ");
		selectBuffer.append("	INNER JOIN gp_module C ON B.module_id = C.id          ");
		selectBuffer.append("	WHERE    1 = 1                                       ");
		selectBuffer.append("	AND  A.user_id='").append(userId).append("'");
		selectBuffer.append("	order by C.domain_id,C.level ASC,C.farther_id,C.priority");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpModuleUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/getListByDomainId/{domainId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByDomainId(@PathVariable("domainId") String domainId) {
		ResultModel resultModel = gpModuleUntBll.getListByDomainId(domainId);
		resultModel = Tools.sortModuleList(resultModel);
		return resultModel;
	}

	@ApiOperation(value = "模糊查询", notes = "初始化功能模块树形菜单查询")
	@RequestMapping(value = "/getZtreeList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getZtreeList() {
		ResultModel resultModel = new ResultModel();

		String domainId = request.getParameter("parentId");
		if (StringUtils.isBlank(domainId)) {
			GpDomainParameter.GetList getListParam = new GpDomainParameter.GetList();

			getListParam.setOrderList(null);
			resultModel = gpDomainUntBll.getList(getListParam);
			return resultModel;
		}
		resultModel = gpModuleUntBll.getListByDomainId(domainId);
		resultModel = Tools.sortModuleList(resultModel);
		return resultModel;
	}

	@RequestMapping(value = "/getListByRoleId/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByRoleId(@PathVariable("roleId") String roleId) {
		ResultModel resultModel = new ResultModel();

		resultModel = gpModuleSplBll.getListByRoleId(roleId);
		resultModel = Tools.sortModuleList(resultModel);
		return resultModel;
	}

	@ApiOperation(value = "获取用户菜单权限", notes = "获取用户菜单权限")
	@RequestMapping(value = "/getLinkMenu", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getLinkMenu() {

		// 授收domainId参数，如果有此参数，则返回当前登录用户在这个应用领域下拥有的菜单权限，如果没有此参数，则返回用户拥有的所有菜单权限。
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		String domainId = "";
		if (StringUtils.isNotBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("domainId"))
				domainId = jsonObject.getString("domainId");
		}
		return StringUtils.isBlank(domainId) ? this.getCurrentUserMenu() : this.getCurrentUserMenu(domainId);
	}

	private ResultModel getCurrentUserMenu(String... domainId) {

		ResultModel resultModel = new ResultModel();

		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT DISTINCT                                                       ");
		selectBuffer.append("		A.id AS id,                                                       ");
		selectBuffer.append("		A.name AS name,                                                   ");
		selectBuffer.append("		A.style AS iconClass,                                        ");
		selectBuffer.append("		A.farther_id AS fartherId,                                  ");
		selectBuffer.append("		A.level AS level,                                      ");
		selectBuffer.append("		CONCAT('" + this.linkPath + "',A.icon_resource) AS iconResource,  ");
		selectBuffer.append("		A.page_url AS pageUrl,                                             ");
		selectBuffer.append("		A.priority AS priority                                             ");
		selectBuffer.append("	FROM                                                                  ");
		selectBuffer.append("		gp_module A                                                 ");
		selectBuffer.append("	INNER JOIN  gpr_role_module B ON A.id= B.module_id                          ");
		selectBuffer.append("	INNER JOIN  gpr_user_role C ON B.role_id = C.role_id                ");
		selectBuffer.append("	INNER JOIN  gp_user D ON C.user_id = D.id                ");
		selectBuffer.append("			WHERE                                                         ");
		selectBuffer.append("				D.id = '" + this.getCurrentUser().getId() + "'                ");
		selectBuffer.append("			AND  D.is_disabled_code = 1                                    ");
		if (domainId.length > 0)
			selectBuffer.append("			AND A.domain_id='").append(domainId[0]).append("'");
		selectBuffer.append("	ORDER BY A.priority                                                   ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = gpModuleUntBll.getListBySQL(map);
		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "功能模块列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		JSONArray columnInfoList = new JSONArray();
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);

			if (json.containsKey("columnInfo")) {
				columnInfoList = json.getJSONArray("columnInfo");
			}
		}

		if (resultModel != null) {
			try {
				exportExcel(fileName, columnInfoList, resultModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
