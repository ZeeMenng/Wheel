package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpRegionGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpRegion;
import com.zee.ent.parameter.gp.GpRegionParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.DictionaryUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 地区信息。 对外接口，扩展自GpRegionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpRegion")
public class GpRegionSwgApp extends GpRegionGenSwgApp {

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpRegionUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpRegionParameter.UpdateList jsonData) {
		ResultModel result = new ResultModel();
		if ((jsonData.getEntity().getCode().equals("") || jsonData.getEntity().getCode() == null) && (jsonData.getEntity().getName().equals("") || jsonData.getEntity().getName() == null) && (jsonData.getEntity().getCategory() == null) && (jsonData.getEntity().getFartherCode().equals("") || jsonData.getEntity().getFartherCode() == null) && (jsonData.getEntity().getIsDisplayCode() == null) && (jsonData.getEntity().getRegionLevel() == null) && (jsonData.getEntity().getLongitude().equals("") || jsonData.getEntity().getLongitude() == null) && (jsonData.getEntity().getLatitude().equals("") || jsonData.getEntity().getLatitude() == null) && (jsonData.getEntity().getArea() == null) && (jsonData.getEntity().getRemark().equals("") || jsonData.getEntity().getRemark() == null)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			return result;
		} else {
			result = gpRegionUntBll.updateList(jsonData);
			return result;
		}
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpRegionUntBll.getModel(id);
		GpRegion gpRegion = (GpRegion) result.getData();
		// result.setData(dictionaryUtil.dictTransform(gpRegion));

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         											     ");
		selectBuffer.append("		t.*,                                      															     ");
		selectBuffer.append("		ft.name fartherName,                                 	    										     ");
		selectBuffer.append("		qy.text regionLevelName,                               	   											     ");
		selectBuffer.append("		dq.text categoryName                 		                  										     ");
		selectBuffer.append("	FROM                                                             											 ");
		selectBuffer.append("		gp_region t                                                											     ");
		selectBuffer.append("	LEFT JOIN gp_region ft ON ft.code = t.farther_code               											 ");
		selectBuffer.append("	LEFT JOIN gp_dictionary qy ON qy.code = t.region_level and qy.type_id = '25fe79d29b55bdba65cf229346598886'   ");
		selectBuffer.append("	LEFT JOIN gp_dictionary dq ON dq.code = t.category and dq.type_id = 'dd6c407283cfc1d4e749a6e0d6c33a12'           ");
		selectBuffer.append("	WHERE                                                            											 ");
		selectBuffer.append("		t.id = '" + gpRegion.getId() + "'                                 											 ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpRegionUntBll.getListBySQL(map);
		List<Map<String, Object>> regionList = CastObjectUtil.cast(resultModel.getData());
		Map<String, Object> regionMap = regionList.get(0);
		gpRegion.setFartherName(regionMap.get("fartherName") != null ? regionMap.get("fartherName").toString() : "");
		gpRegion.setRegionLevelName(regionMap.get("regionLevelName") != null ? regionMap.get("regionLevelName").toString() : "");
		gpRegion.setCategoryName(regionMap.get("categoryName") != null ? regionMap.get("categoryName").toString() : "");
		result.setData(dictionaryUtil.dictTransform(gpRegion));

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
		selectBuffer.append("	SELECT                                                          ");
		selectBuffer.append("		A.id id,                                                    ");
		selectBuffer.append("		A.CODE code,                                                ");
		selectBuffer.append("		A.NAME name,                                                ");
		selectBuffer.append("		A.category category,                                        ");
		selectBuffer.append("		A.farther_code fartherCode,                                 ");
		selectBuffer.append("		A.region_level region_level,                                ");
		selectBuffer.append("		A.longitude longitude,                                      ");
		selectBuffer.append("		A.latitude latitude,                                        ");
		selectBuffer.append("		A.area area,                                                ");
		selectBuffer.append("		IF(A.is_display_code = 0,'是','否') isDisplayCode,          ");
		selectBuffer.append("		A.remark remark                                             ");
		selectBuffer.append("	FROM                                                            ");
		selectBuffer.append("		gp_region A                                                 ");
		selectBuffer.append("	WHERE                                                           ");
		selectBuffer.append("		1 = 1                                                       ");

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
				if (entityRelatedObject.containsKey("code") && StringUtils.isNotBlank(entityRelatedObject.getString("code")))
					selectBuffer.append(" and A.code like '%").append(entityRelatedObject.getString("code")).append("%'");
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("regionLevel") && StringUtils.isNotBlank(entityRelatedObject.getString("regionLevel")))
					selectBuffer.append(" and A.region_level = '").append(entityRelatedObject.getString("regionLevel")).append("'");
				if (entityRelatedObject.containsKey("selectIsDisplayCode") && StringUtils.isNotBlank(entityRelatedObject.getString("selectIsDisplayCode")))
					selectBuffer.append(" and A.is_display_code = '").append(entityRelatedObject.getString("selectIsDisplayCode")).append("'");
				if (entityRelatedObject.containsKey("category") && StringUtils.isNotBlank(entityRelatedObject.getString("category"))) {
					String categorys = entityRelatedObject.getString("category");
					String[] categoryArr = categorys.split(",");
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < categoryArr.length; i++) {
						sb.append("'" + categoryArr[i] + "'");
						if (i != categoryArr.length - 1) {
							sb.append(",");
						}
					}
					selectBuffer.append(" and A.category in (" + sb.toString() + ")");
				}
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

		resultModel = gpRegionUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "地区信息列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
