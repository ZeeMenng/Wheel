package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpDomainGenSwgApp;
import com.zee.bll.extend.split.gp.GpModuleSplBll;
import com.zee.bll.extend.split.gp.GpResourceSplBll;
import com.zee.bll.extend.split.gp.GprResourceSplBll;
import com.zee.bll.extend.unity.gp.GpModuleUntBll;
import com.zee.bll.extend.unity.gp.GprResourceUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpResource;
import com.zee.ent.extend.gp.GprResource;
import com.zee.ent.parameter.gp.GpDomainParameter;
import com.zee.set.symbolic.CustomSymbolic;
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
 * @description 应用领域。 对外接口，扩展自GpDomainGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpDomain")
public class GpDomainSwgApp extends GpDomainGenSwgApp {

	@Autowired
	@Qualifier("gpModuleUntBll")
	protected GpModuleUntBll gpModuleUntBll;

	@Autowired
	@Qualifier("gpModuleSplBll")
	protected GpModuleSplBll gpModuleSplBll;

	@Autowired
	@Qualifier("gpResourceSplBll")
	protected GpResourceSplBll gpResourceSplBll;

	@Autowired
	@Qualifier("gprResourceSplBll")
	protected GprResourceSplBll gprResourceSplBll;

	@Autowired
	@Qualifier("gprResourceUntBll")
	protected GprResourceUntBll gprResourceUntBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpDomain") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpDomain jsonData) {
		jsonData.setId(Tools.getUUID());

		if (StringUtils.isNotBlank(jsonData.getIconPaths())) {
			jsonData.setIconPaths(jsonData.getIconPaths().replaceAll(linkPath, ""));
			String[] resourcePathArray = jsonData.getIconPaths().split(",");
			if (resourcePathArray.length != 0)
				jsonData.setIconResource(resourcePathArray[0]);
		}

		ResultModel result = new ResultModel();
		result = gpDomainUntBll.add(jsonData);

		String modules = jsonData.getModules();
		if (StringUtils.isNotBlank(modules)) {
			ArrayList<GpModule> moduleList = Tools.getModuleListFromJsonString(result.getObjectId(), modules);
			result = gpModuleUntBll.add(moduleList);
		}

		// 图标列表
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

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = gpDomainUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = gpDomainUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpDomainDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpDomainParameter.DeleteByIdList jsonData) {
		ResultModel result = gpDomainUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpDomain") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpDomain jsonData) {
		if (StringUtils.isNotBlank(jsonData.getIconPaths())) {
			jsonData.setIconPaths(jsonData.getIconPaths().replaceAll(linkPath, ""));
			String[] resourcePathArray = jsonData.getIconPaths().split(",");
			if (resourcePathArray.length != 0)
				jsonData.setIconResource(resourcePathArray[0]);
		}
		Date updateTime = DateUtils.getCurrentDate();
		jsonData.setUpdateTime(updateTime);
		ResultModel result = gpDomainUntBll.update(jsonData);

		String modules = jsonData.getModules();
		if (StringUtils.isNotBlank(modules)) {
			ArrayList<GpModule> moduleList = Tools.getModuleListFromJsonString(jsonData.getId(), modules);
			// 如果为0直接删除所有
			if (moduleList.size() == 0)
				result = gpModuleUntBll.deleteByDomainId(jsonData.getId());
			else
				result = gpModuleSplBll.updateDomainModules(moduleList);
		}

		// 图标列表
		gprResourceSplBll.deleteByBusinessId(jsonData.getId());
		if (StringUtils.isNotBlank(jsonData.getIconIds())) {
			ArrayList<GprResource> gprResourceList = new ArrayList<GprResource>();
			String[] resourceArray = jsonData.getIconIds().split(",");
			for (int i = 0; i < resourceArray.length; i++) {
				GprResource gprResource = new GprResource();
				gprResource.setResourceId(resourceArray[i]);
				gprResource.setBusinessId(jsonData.getId());
				gprResource.setIsDefault(i == 0 ? CustomSymbolic.DCODE_BOOLEAN_T : CustomSymbolic.DCODE_BOOLEAN_F);
				gprResourceList.add(gprResource);
			}
			gprResourceUntBll.add(gprResourceList);
		}

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpDomainUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpDomainParameter.UpdateList jsonData) {

		jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
		ResultModel result = gpDomainUntBll.updateList(jsonData);

		return result;
	}

	@RequestMapping(value = "/getDomainListByRoleId/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDomainListByRoleId(@PathVariable("roleId") String roleId) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                         ");
		selectBuffer.append("		A.id id,                                   ");
		selectBuffer.append("		A.name name,                               ");
		selectBuffer.append("		A.serial_no serialNo,                      ");
		selectBuffer.append("		A.com com,                                 ");
		selectBuffer.append("		A.remark remark,                           ");
		selectBuffer.append("		A.add_time addTime,                        ");
		selectBuffer.append("		A.update_time updateTime                   ");
		selectBuffer.append("	FROM                                           ");
		selectBuffer.append("		gp_domain A                                ");
		selectBuffer.append("	INNER JOIN gpr_role_domain B ON A.id = B.domain_id");
		selectBuffer.append("	where B.role_id='").append(roleId).append("'");

		map.put("Sql", selectBuffer.toString());

		ResultModel result = gpDomainUntBll.getListBySQL(map);

		return result;
	}

	@RequestMapping(value = "/getDomainListByUserId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDomainListByUserId(@PathVariable("userId") String userId) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                         ");
		selectBuffer.append("		A.id id,                                   ");
		selectBuffer.append("		A.name name,                               ");
		selectBuffer.append("		A.serial_no serialNo,                      ");
		selectBuffer.append("		A.com com,                                 ");
		selectBuffer.append("		A.remark remark,                           ");
		selectBuffer.append("		A.add_time addTime,                        ");
		selectBuffer.append("		A.update_time updateTime                   ");
		selectBuffer.append("	FROM                                           ");
		selectBuffer.append("		gp_domain A                                ");
		selectBuffer.append("	INNER JOIN gpr_domain_user B ON A.id = B.domain_id");
		selectBuffer.append("	where B.user_id='").append(userId).append("'");

		map.put("Sql", selectBuffer.toString());

		ResultModel result = gpDomainUntBll.getListBySQL(map);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = new ResultModel();

		result = gpResourceSplBll.getListByBusinessId(id);
		ArrayList<GpResource> iconResuourceList = (ArrayList<GpResource>) result.getData();

		result = gpDomainUntBll.getModel(id);
		GpDomain gpDomain = (GpDomain) result.getData();

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
		gpDomain.setIconIds(userIconIds);
		gpDomain.setIconPaths(userIconPaths);
		result.setData(gpDomain);

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
		selectBuffer.append("	SELECT                                         ");
		selectBuffer.append("		A.id id,                                   ");
		selectBuffer.append("		A.NAME name,                               ");
		selectBuffer.append("		A.serial_no serialNo,                      ");
		selectBuffer.append("		A.com com,                                 ");
		selectBuffer.append("		A.remark remark,                           ");
		selectBuffer.append("		A.add_time addTime,                        ");
		selectBuffer.append("		A.update_time updateTime                   ");
		selectBuffer.append("	FROM                                           ");
		selectBuffer.append("		gp_domain A                                ");
		selectBuffer.append("	INNER JOIN gp_domain B ON A.id = B.id          ");
		selectBuffer.append("	WHERE                                          ");
		selectBuffer.append("		1 = 1                                      ");

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
					selectBuffer.append(String.format(" and(  A.name like %1$s or A.com like %1$s )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("serialNo") && StringUtils.isNotBlank(entityRelatedObject.getString("serialNo")))
					selectBuffer.append(" and A.serial_no like '%").append(entityRelatedObject.getString("serialNo")).append("%'");
				if (entityRelatedObject.containsKey("com") && StringUtils.isNotBlank(entityRelatedObject.getString("com")))
					selectBuffer.append(" and A.com like '%").append(entityRelatedObject.getString("com")).append("%'");
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

		resultModel = gpDomainUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "应用领域列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
