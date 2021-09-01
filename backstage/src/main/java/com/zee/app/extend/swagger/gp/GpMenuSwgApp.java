package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpMenuGenSwgApp;
import com.zee.bll.extend.unity.gp.GpModuleUntBll;
import com.zee.bll.extend.unity.gp.GpPageUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpMenu;
import com.zee.ent.parameter.gp.GpMenuParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 链接菜单。 对外接口，扩展自GpMenuGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpMenu")
public class GpMenuSwgApp extends GpMenuGenSwgApp {

	@Autowired
	@Qualifier("gpPageUntBll")
	protected GpPageUntBll gpPageUntBll;

	@Autowired
	@Qualifier("gpModuleUntBll")
	protected GpModuleUntBll gpModuleUntBll;

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

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取用户菜单权限，格式化", notes = "获取用户菜单权限，格式化")
	@RequestMapping(value = "/getLinkMenuFormat", method = { RequestMethod.POST, RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getLinkMenuFormat() {
		ResultModel resultModel = this.getCurrentUserMenu();

		// 转自定义格式
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> jsonFormat = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : modelList) {
			if (map.get("fartherModuleId") == null) {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("moduleId", map.get("moduleId"));
				jsonMap.put("fartherModuleId", map.get("fartherModuleId"));
				jsonMap.put("name", map.get("name"));
				jsonMap.put("path", map.get("pageUrl"));
				jsonMap.put("level", map.get("moduleLevel"));
				jsonMap.put("child", getJsonFormat(modelList, jsonMap));
				jsonFormat.add(jsonMap);
			}
		}

		resultModel.setData(jsonFormat);
		return resultModel;
	}

	private List<Map<String, Object>> getJsonFormat(List<Map<String, Object>> modelList, Map<String, Object> fmap) {
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : modelList) {
			if (map.get("fartherModuleId") != null && map.get("fartherModuleId").equals(fmap.get("moduleId"))) {
				Map<String, Object> jsonMap = new HashMap<String, Object>();
				jsonMap.put("moduleId", map.get("moduleId"));
				jsonMap.put("fartherModuleId", map.get("fartherModuleId"));
				jsonMap.put("name", map.get("name"));
				jsonMap.put("path", map.get("pageUrl"));
				jsonMap.put("level", map.get("moduleLevel"));
				List<Map<String, Object>> childList = getJsonFormat(modelList, jsonMap);
				jsonMap.put("child", childList);
				jsonList.add(jsonMap);
			}
		}
		return jsonList;
	}

	private ResultModel getCurrentUserMenu(String... domainId) {

		ResultModel resultModel = new ResultModel();

		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT DISTINCT                                                       ");
		selectBuffer.append("		e.id AS id,                                                       ");
		selectBuffer.append("		e.name AS name,                                                   ");
		selectBuffer.append("		e.icon_class AS iconClass,                                        ");
		selectBuffer.append("		e.module_id AS moduleId,                                          ");
		selectBuffer.append("		d.farther_id AS fartherModuleId,                                  ");
		selectBuffer.append("		d.level_code AS moduleLevel,                                      ");
		selectBuffer.append("		e.page_url AS pageUrl,                                             ");
		selectBuffer.append("		e.priority AS priority                                             ");
		selectBuffer.append("	FROM                                                                  ");
		selectBuffer.append("		gpr_role_module c                                                 ");
		selectBuffer.append("	INNER JOIN gp_module d ON c.module_id = d.id                          ");
		selectBuffer.append("	INNER JOIN gp_menu e ON e.module_id = d.id                            ");
		selectBuffer.append("	WHERE                                                                 ");
		selectBuffer.append("		c.role_id IN (                                                    ");
		selectBuffer.append("			SELECT                                                        ");
		selectBuffer.append("				b.role_id                                                 ");
		selectBuffer.append("			FROM                                                          ");
		selectBuffer.append("				gp_user a                                                 ");
		selectBuffer.append("			INNER JOIN gpr_user_role b ON a.id = b.user_id                ");
		selectBuffer.append("			WHERE                                                         ");
		selectBuffer.append("				a.id = '" + this.getCurrentUser().getId() + "'                ");
		selectBuffer.append("			AND a.is_admin_code = 0)                                      ");
		if (domainId.length > 0)
			selectBuffer.append("			AND e.domain_id='").append(domainId[0]).append("'");
		selectBuffer.append("	ORDER BY e.priority                                                   ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = gpMenuUntBll.getListBySQL(map);
		return resultModel;
	}

	/**
	 * 根据页面路径获取页面id
	 * @param ResultModel
	 * @return
	 */
	private String getPageId(String pageUrl) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT A.id FROM gp_page A WHERE A.url = '" + pageUrl + "'");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpPageUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		return modelList.get(0).get("id").toString();
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpMenu") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpMenu jsonData) {
		if (StringUtils.isNotBlank(jsonData.getPageUrl())) {
			jsonData.setPageId(getPageId(jsonData.getPageUrl()));
		}
		ResultModel result = gpMenuUntBll.update(jsonData);
		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpMenuUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpMenuParameter.UpdateList jsonData) {
		jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
		if (StringUtils.isNotBlank(jsonData.getEntity().getPageUrl())) {
			jsonData.getEntity().setPageId(getPageId(jsonData.getEntity().getPageUrl()));
		}
		ResultModel result = gpMenuUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpMenuUntBll.getModel(id);
		if (result.getData() != null) {
			GpMenu gpMenu = (GpMenu) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name moduleName,                            ");
			selectBuffer.append("			C.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_menu A                                     ");
			selectBuffer.append("		LEFT JOIN gp_module B ON A.module_id = B.id       ");
			selectBuffer.append("		LEFT JOIN gp_domain C ON A.domain_id = C.id       ");
			selectBuffer.append("		WHERE                                             ");
			selectBuffer.append("		A.id = '" + gpMenu.getId() + "'                       ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpMenuUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> modelMap = modelList.get(0);
			gpMenu.setModuleName(modelMap.get("moduleName") != null ? modelMap.get("moduleName").toString() : "");
			gpMenu.setDomainName(modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "");
			result.setData(gpMenu);
		}
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
		selectBuffer.append("	SELECT                                              ");
		selectBuffer.append("		A.id id,                                        ");
		selectBuffer.append("		A.name name,                                    ");
		selectBuffer.append("		AF.name fartherName,                            ");
		selectBuffer.append("		B.level_text,                                   ");
		selectBuffer.append("		A.page_id pageId,                               ");
		selectBuffer.append("		A.page_url pageUrl,                             ");
		selectBuffer.append("		A.icon_class iconClass,                         ");
		selectBuffer.append("		A.priority	priority                            ");
		selectBuffer.append("	FROM                                                ");
		selectBuffer.append("		gp_menu A                                       ");
		selectBuffer.append("	INNER JOIN gp_module B ON A.module_id = B.id        ");
		selectBuffer.append("	LEFT JOIN gp_module BF ON B.farther_id = BF.id      ");
		selectBuffer.append("	LEFT JOIN gp_menu AF ON AF.module_id = BF.id        ");
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
				if (entityRelatedObject.containsKey("pageUrl") && StringUtils.isNotBlank(entityRelatedObject.getString("pageUrl")))
					selectBuffer.append(" and A.page_url like '%").append(entityRelatedObject.getString("pageUrl")).append("%'");
				if (entityRelatedObject.containsKey("priority") && StringUtils.isNotBlank(entityRelatedObject.getString("priority")))
					selectBuffer.append(" and A.priority like '%").append(entityRelatedObject.getString("priority")).append("%'");
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

		resultModel = gpMenuUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "链接菜单列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
