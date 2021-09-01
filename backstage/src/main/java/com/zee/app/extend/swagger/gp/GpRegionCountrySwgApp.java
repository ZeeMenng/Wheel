package com.zee.app.extend.swagger.gp;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpRegionCountryGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpRegionCountry;
import com.zee.ent.parameter.gp.GpRegionCountryParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.DictionaryUtil;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 国家信息。 对外接口，扩展自GpRegionCountryGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpRegionCountry")
public class GpRegionCountrySwgApp extends GpRegionCountryGenSwgApp {
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	
	
	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpRegionCountryUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpRegionCountryParameter.UpdateList jsonData) {
		ResultModel result = new ResultModel();
		if((jsonData.getEntity().getIsoCode().equals("")||jsonData.getEntity().getIsoCode()==null)
				&&(jsonData.getEntity().getChineseName().equals("")||jsonData.getEntity().getChineseName()==null)
				&&(jsonData.getEntity().getEnglishName().equals("")||jsonData.getEntity().getEnglishName()==null)
				&&(jsonData.getEntity().getIsDisplayCode()==null)
				&&(jsonData.getEntity().getLatitude().equals("")||jsonData.getEntity().getLatitude()==null)
				&&(jsonData.getEntity().getLongitude().equals("")||jsonData.getEntity().getLongitude()==null)
				&&(jsonData.getEntity().getArea()==null)
				&&(jsonData.getEntity().getRemark().equals("")||jsonData.getEntity().getRemark()==null)
				){
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			return result;
		}else{
					result = gpRegionCountryUntBll.updateList(jsonData);
					return result;
				}
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpRegionCountryUntBll.getModel(id);
		GpRegionCountry gpRegionCountry = (GpRegionCountry) result.getData();
		result.setData(dictionaryUtil.dictTransform(gpRegionCountry));
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
		selectBuffer.append("	SELECT                                                        ");
		selectBuffer.append("		A.id id,                                                  ");
		selectBuffer.append("		A.iso_code isoCode,                                       ");
		selectBuffer.append("		A.chinese_name chineseName,                               ");
		selectBuffer.append("		A.english_name englishName,                               ");
		selectBuffer.append("		A.longitude longitude,                                    ");
		selectBuffer.append("		A.latitude latitude,                                      ");
		selectBuffer.append("		A.area area,                                              ");
		selectBuffer.append("		IF(A.is_display_code = 0,'是','否') isDisplayCode,         ");
		selectBuffer.append("		A.remark remark                                           ");
		selectBuffer.append("	FROM                                                          ");
		selectBuffer.append("		gp_region_country A                                       ");
		selectBuffer.append("	INNER JOIN gp_region_country B ON A.id = B.id                 ");
		selectBuffer.append("	WHERE                                                         ");
		selectBuffer.append("		1 = 1                                                     ");
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
                
				if (entityRelatedObject.containsKey("isoCode") && StringUtils.isNotBlank(entityRelatedObject.getString("isoCode")))
					selectBuffer.append(" and A.iso_code like '%").append(entityRelatedObject.getString("isoCode")).append("%'");
				if (entityRelatedObject.containsKey("chineseName") && StringUtils.isNotBlank(entityRelatedObject.getString("chineseName")))
					selectBuffer.append(" and A.chinese_name like '%").append(entityRelatedObject.getString("chineseName")).append("%'");
				if (entityRelatedObject.containsKey("englishName") && StringUtils.isNotBlank(entityRelatedObject.getString("englishName")))
					selectBuffer.append(" and A.english_name like '%").append(entityRelatedObject.getString("englishName")).append("%'");
				if (entityRelatedObject.containsKey("selectIsDisplayCode") && StringUtils.isNotBlank(entityRelatedObject.getString("selectIsDisplayCode")))
					selectBuffer.append(" and A.is_display_code = '").append(entityRelatedObject.getString("selectIsDisplayCode")).append("'");
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

		resultModel = gpRegionCountryUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "国家信息列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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



