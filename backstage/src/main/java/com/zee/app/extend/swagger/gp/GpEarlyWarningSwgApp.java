package com.zee.app.extend.swagger.gp;

import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpEarlyWarningGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpEarlyWarning;
import com.zee.ent.parameter.gp.GpEarlyWarningParameter;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-8-17 16:57:52
 * @description 预警阀值表 对外接口，扩展自GpEarlyWarningGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpEarlyWarning")
public class  GpEarlyWarningSwgApp extends  GpEarlyWarningGenSwgApp {
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpEarlyWarning") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpEarlyWarning jsonData) {
		ResultModel result = gpEarlyWarningUntBll.add(jsonData);

		return result;
	}


	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = gpEarlyWarningUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = gpEarlyWarningUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpEarlyWarningDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpEarlyWarningParameter.DeleteByIdList jsonData) {
		ResultModel result = gpEarlyWarningUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpEarlyWarning") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpEarlyWarning jsonData) {
		ResultModel result = gpEarlyWarningUntBll.update(jsonData);

		return result;
	}


	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = gpEarlyWarningUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpEarlyWarningUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "GpEarlyWarningGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody GpEarlyWarningParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = gpEarlyWarningUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.growth_id growthId,A.growth_name growthName,A.begin_date beginDate,A.end_date endDate,A.warning_type warningType,A.warning_name warningName,A.max_val maxVal,A.min_val minVal,A.tips_mess tipsMess,A.stutas_code stutasCode,A.stutas_text stutasText,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_early_warning A inner join gp_early_warning B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("growthId") && StringUtils.isNotBlank(entityRelatedObject.getString("growthId")))
					selectBuffer.append(" and A.growth_id = '").append(entityRelatedObject.getString("growthId")).append("'");
				if (entityRelatedObject.containsKey("growthName") && StringUtils.isNotBlank(entityRelatedObject.getString("growthName")))
					selectBuffer.append(" and A.growth_name like '%").append(entityRelatedObject.getString("growthName")).append("%'");
				if (entityRelatedObject.containsKey("warningType") && StringUtils.isNotBlank(entityRelatedObject.getString("warningType")))
					selectBuffer.append(" and A.warning_type = '").append(entityRelatedObject.getString("warningType")).append("'");
				if (entityRelatedObject.containsKey("warningName") && StringUtils.isNotBlank(entityRelatedObject.getString("warningName")))
					selectBuffer.append(" and A.warning_name like '%").append(entityRelatedObject.getString("warningName")).append("%'");
				if (entityRelatedObject.containsKey("stutasCode") && StringUtils.isNotBlank(entityRelatedObject.getString("stutasCode")))
					selectBuffer.append(" and A.stutas_code = '").append(entityRelatedObject.getString("stutasCode")).append("'");
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

		resultModel = gpEarlyWarningUntBll.getListBySQL(map);

		return resultModel;
	}
	
	
	@ApiOperation(value = "获取预警指标", notes = "获取预警指标")
	@RequestMapping(value = "/getWarningType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWarningType(){
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.type as warningType,t.display_name as warningName  ");
		selectBuffer.append(" from da_iot_dictionary t  ");
		selectBuffer.append(" where t.type in (769,770,778,780,781,782,787,788,797) ");
		selectBuffer.append(" order by t.type asc ");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpEarlyWarningUntBll.getListBySQL(map);
		return resultModel;
	}

}



