package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpOrganizationGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.parameter.gp.GpOrganizationParameter;
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
 * @description 组织机构。 对外接口，扩展自GpOrganizationGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpOrganization")
public class GpOrganizationSwgApp extends GpOrganizationGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpOrganization") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpOrganization jsonData) {

		Date addTime = DateUtils.getCurrentDate();
		jsonData.setAddTime(addTime);
		ResultModel result = gpOrganizationUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpOrganization") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpOrganization jsonData) {

		Date updateTime = DateUtils.getCurrentDate();
		jsonData.setUpdateTime(updateTime);
		ResultModel result = gpOrganizationUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpOrganizationUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpOrganizationParameter.UpdateList jsonData) {
		jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
		ResultModel result = gpOrganizationUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpOrganizationUntBll.getModel(id);

		GpOrganization gpOrganization = (GpOrganization) result.getData();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                  ");
		selectBuffer.append("		t.id,                                                               ");
		selectBuffer.append("		ft.id organizationFar,ft.name organizationFarName,                  ");
		selectBuffer.append("		fft.id organizationTop                                              ");
		selectBuffer.append("	FROM                                                                    ");
		selectBuffer.append("		gp_organization t                                                   ");
		selectBuffer.append("	LEFT JOIN gp_organization ft ON ft.id = t.farther_id                    ");
		selectBuffer.append("	LEFT JOIN gp_organization fft ON fft.id = ft.farther_id                 ");
		selectBuffer.append("	WHERE                                                                   ");
		selectBuffer.append("		t.id = '" + gpOrganization.getId() + "'                                 ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpOrganizationUntBll.getListBySQL(map);
		List<Map<String, Object>> organizationList = CastObjectUtil.cast(resultModel.getData());
		Map<String, Object> organizationMap = organizationList.get(0);
		gpOrganization.setOrganizationTop(organizationMap.get("organizationTop") != null ? organizationMap.get("organizationTop").toString() : "");
		gpOrganization.setOrganizationFar(organizationMap.get("organizationFar") != null ? organizationMap.get("organizationFar").toString() : "");
		gpOrganization.setFartherId(organizationMap.get("organizationFar") != null ? organizationMap.get("organizationFar").toString() : "");
		gpOrganization.setFartherName(organizationMap.get("organizationFarName") != null ? organizationMap.get("organizationFarName").toString() : "");
		result.setData(gpOrganization);

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
		selectBuffer.append("	SELECT                                                   ");
		selectBuffer.append("		A.id,                                                ");
		selectBuffer.append("		A.name,                                              ");
		selectBuffer.append("		B.id fartherId,                                      ");
		selectBuffer.append("		B.name fartherName,                                  ");
		selectBuffer.append("		A.type_code typeCode,                                ");
		selectBuffer.append("		A.type_text typeText,                                ");
		selectBuffer.append("		A.level_code levelCode,                              ");
		selectBuffer.append("		A.level_text levelText,                              ");
		selectBuffer.append("		A.priority priority                                  ");
		selectBuffer.append("	FROM                                                     ");
		selectBuffer.append("		gp_organization A                                    ");
		selectBuffer.append("	LEFT JOIN gp_organization B ON B.id = A.farther_id       ");
		selectBuffer.append("	WHERE                                                    ");
		selectBuffer.append("		1 = 1                                                ");

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
				if (entityRelatedObject.containsKey("fartherName") && StringUtils.isNotBlank(entityRelatedObject.getString("fartherName")))
					selectBuffer.append(" and B.name like '%").append(entityRelatedObject.getString("fartherName")).append("%'");
				if (entityRelatedObject.containsKey("levelCode") && StringUtils.isNotBlank(entityRelatedObject.getString("levelCode")))
					selectBuffer.append(" and A.level_code = '").append(entityRelatedObject.getString("levelCode")).append("'");
				if (entityRelatedObject.containsKey("fartherId") && StringUtils.isNotBlank(entityRelatedObject.getString("fartherId")))
					selectBuffer.append(" and A.farther_id = '").append(entityRelatedObject.getString("fartherId")).append("'");
				if (entityRelatedObject.containsKey("typeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("typeCode")))
					selectBuffer.append(" and A.type_Code = '").append(entityRelatedObject.getString("typeCode")).append("'");

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

		resultModel = gpOrganizationUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/getListByUserId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByUserId(@PathVariable("userId") String userId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                   ");
		selectBuffer.append("		A.id,                                                ");
		selectBuffer.append("		A.name,                                              ");
		selectBuffer.append("		A.farther_id fartherId,                                      ");
		selectBuffer.append("		A.type_code typeCode,                                ");
		selectBuffer.append("		A.type_text typeText,                                ");
		selectBuffer.append("		A.level_code levelCode,                              ");
		selectBuffer.append("		A.level_text levelText,                              ");
		selectBuffer.append("		A.priority priority                                  ");
		selectBuffer.append("	FROM                                                     ");
		selectBuffer.append("		gp_organization A                                    ");
		selectBuffer.append("	INNER JOIN gpr_user_organization C ON C.organization_id = A.id       ");
		selectBuffer.append("	WHERE                                                    ");
		selectBuffer.append("		1 = 1                                                ");
		selectBuffer.append("		AND C.user_id = '").append(userId).append("'");

		map.put("Sql", selectBuffer.toString());

		resultModel = gpOrganizationUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "组织机构名称查询", notes = "查询所有现有组织机构名称")
	@RequestMapping(value = "/getOrganizationNameList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getOrganizationNameList() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                   ");
		selectBuffer.append("		distinct id code,       				             ");
		selectBuffer.append("		name text           		            			 ");
		selectBuffer.append("	FROM                                                     ");
		selectBuffer.append("		gp_organization A                                    ");
		selectBuffer.append("	WHERE                                                    ");
		selectBuffer.append("		1 = 1                                                ");

		map.put("Sql", selectBuffer.toString());

		resultModel = gpOrganizationUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "组织机构列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
