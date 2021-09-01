package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.zee.app.generate.swagger.gp.GpDictionaryGenSwgApp;
import com.zee.bll.extend.unity.gp.GpRegionUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.base.BaseParameter;
import com.zee.ent.parameter.base.BaseParameter.BaseParamGetList.Order;
import com.zee.ent.parameter.gp.GpDictionaryParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 字典信息。 对外接口，扩展自GpDictionaryGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpDictionary")
public class GpDictionarySwgApp extends GpDictionaryGenSwgApp {

	@Autowired
	@Qualifier("gpRegionUntBll")
	protected GpRegionUntBll gpRegionUntBll;

	@ApiOperation(value = "获取当前时间及过去时间", notes = "获取当前时间及过去时间")
	@RequestMapping(value = "/getTimesView", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimesView() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String viewName = "";// 视图名称
		String pastNum = "";// 过去数字
		String hasCurrent = "";// 是否包含现在
		String afterNum = "";// 将来数字
		String isASC = "";// 排序

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("viewName") && StringUtils.isNotBlank(entityRelatedObject.getString("viewName")))
					viewName = entityRelatedObject.getString("viewName");
				if (entityRelatedObject.containsKey("pastNum") && StringUtils.isNotBlank(entityRelatedObject.getString("pastNum")))
					pastNum = entityRelatedObject.getString("pastNum");
				if (entityRelatedObject.containsKey("hasCurrent") && StringUtils.isNotBlank(entityRelatedObject.getString("hasCurrent")))
					hasCurrent = entityRelatedObject.getString("hasCurrent");
				if (entityRelatedObject.containsKey("afterNum") && StringUtils.isNotBlank(entityRelatedObject.getString("afterNum")))
					afterNum = entityRelatedObject.getString("afterNum");
				if (entityRelatedObject.containsKey("isASC") && StringUtils.isNotBlank(entityRelatedObject.getString("isASC")))
					isASC = entityRelatedObject.getString("isASC");
			}
		}
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", viewName);
		pmap.put("pastNum", pastNum);
		pmap.put("hasCurrent", hasCurrent);
		pmap.put("afterNum", afterNum);
		pmap.put("isASC", isASC);
		List<String> timesList = TimesView.getTimesView(pmap);
		resultModel.setData(timesList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "根据字典类型ID获取字典项列表", notes = "根据字典类型ID获取字典项列表，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "typeId", value = "字典类型ID", required = true, dataType = "String")
	@RequestMapping(value = "/getListByTypeId/{typeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByTypeId(@PathVariable("typeId") String typeId) {

		GpDictionaryParameter.GetList jsonData = new GpDictionaryParameter.GetList();

		GpDictionaryParameter.GetList.EntityRelated entityRelated = new GpDictionaryParameter.GetList.EntityRelated();
		BaseParameter.BaseParamGetList.Order order = new BaseParameter.BaseParamGetList.Order();
		BaseParameter.BaseParamGetList.Order order1 = new BaseParameter.BaseParamGetList.Order();

		entityRelated.setTypeId(typeId);
		order.setColumnName("priority");
		order.setIsASC(true);

		order1.setColumnName("code");
		order1.setIsASC(true);

		jsonData.setEntityRelated(entityRelated);
		jsonData.setOrderList(new ArrayList<Order>() {
			{
				add(order);
				add(order1);
			}
		});

		ResultModel result = gpDictionaryUntBll.getList(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpDictionaryUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpDictionaryParameter.UpdateList jsonData) {

		ResultModel result = new ResultModel();
		if ((jsonData.getEntity().getCode() == null) && (jsonData.getEntity().getTypeId().equals("") || jsonData.getEntity().getTypeId() == null) && (jsonData.getEntity().getText().equals("") || jsonData.getEntity().getText() == null) && (jsonData.getEntity().getPriority() == null) && (jsonData.getEntity().getRemark().equals("") || jsonData.getEntity().getRemark() == null)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			return result;
		} else {
			result = gpDictionaryUntBll.updateList(jsonData);
			return result;
		}
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
		selectBuffer.append("	SELECT                                                            ");
		selectBuffer.append("		A.id id,                                                      ");
		selectBuffer.append("		A.type_id typeId,                                             ");
		selectBuffer.append("		A.code code,                                                ");
		selectBuffer.append("		A.text text,                                                  ");
		selectBuffer.append("		A.priority priority,                                          ");
		selectBuffer.append("		A.remark remark,                                              ");
		selectBuffer.append("		B.name typeName                                             ");
		selectBuffer.append("	FROM                                                              ");
		selectBuffer.append("		gp_dictionary A                                               ");
		selectBuffer.append("	INNER JOIN gp_dictionary_type B ON A.type_id = B.id               ");
		selectBuffer.append("	WHERE                                                             ");
		selectBuffer.append("		1 = 1                                                         ");

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
				if (entityRelatedObject.containsKey("text") && StringUtils.isNotBlank(entityRelatedObject.getString("text")))
					selectBuffer.append(" and A.text like '%").append(entityRelatedObject.getString("text")).append("%'");
				if (entityRelatedObject.containsKey("priority") && StringUtils.isNotBlank(entityRelatedObject.getString("priority")))
					selectBuffer.append(" and A.priority like '%").append(entityRelatedObject.getString("priority")).append("%'");
				if (entityRelatedObject.containsKey("typeId") && StringUtils.isNotBlank(entityRelatedObject.getString("typeId")))
					selectBuffer.append(" and A.type_id = '").append(entityRelatedObject.getString("typeId")).append("'");
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

		resultModel = gpDictionaryUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "字典信息列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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

	@ApiOperation(value = "根据区域等级获取地理区域列表", notes = "根据区域等级获取地理区域列表，路径拼接模式")
	@RequestMapping(value = "/getRegionListByLevel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRegionListByLevel() {

		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String level = "5";

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("level") && StringUtils.isNotBlank(entityRelatedObject.getString("level")))
					level = entityRelatedObject.getString("level");
			}
		}

		List<Map<String, Object>> originList = getOriginRegionList();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		if (originList != null && originList.size() >= 1) {
			for (Map<String, Object> perMap : originList) {
				// 对perMap进行递归
				Map<String, Object> tempMap = recursionGetChildrenRegion(perMap, level);
				resultList.add(tempMap);
			}
		}
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private Map<String, Object> recursionGetChildrenRegion(Map<String, Object> perMap, String level) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String code = "";
		String name = "";
		// 根据perMap中的code值获取对应的level
		if (perMap.containsKey("value")) {
			code = (String) perMap.get("value");
			resultMap.put("value", code);
		}
		if (perMap.containsKey("label")) {
			name = (String) perMap.get("label");
			resultMap.put("label", name);
		}
		int perLevel = getLevelByCode(code);
		if (perLevel < Integer.parseInt(level)) {
			// 获取farther_code为code的地区列表
			List<Map<String, Object>> perChildrenRegionList = getChildrenRegionListByFartherCode(code);
			List<Map<String, Object>> perResultRegionList = new ArrayList<Map<String, Object>>();
			if (perChildrenRegionList != null && perChildrenRegionList.size() >= 1) {
				for (Map<String, Object> perChildrenMap : perChildrenRegionList) {
					Map<String, Object> tempMap = recursionGetChildrenRegion(perChildrenMap, level);
					perResultRegionList.add(tempMap);
				}
			}
			resultMap.put("children", perResultRegionList);
		}
		return resultMap;
	}

	private List<Map<String, Object>> getChildrenRegionListByFartherCode(String code) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT A.`code` AS value, A.`name` AS label FROM gp_region A WHERE
		 * A.farther_code = '031000000'
		 */

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" A.`code` AS value,											");
		selectBuffer.append(" A.`name` AS label 											");
		selectBuffer.append(" FROM															");
		selectBuffer.append(" gp_region A 													");
		selectBuffer.append(" WHERE A.farther_code =										");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(code)) {
			selectBuffer.append(code);
		}
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpRegionUntBll.getListBySQL(map);
		return CastObjectUtil.cast(resultModel.getData());
	}

	private int getLevelByCode(String code) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT A.`region_level` AS regionLevel FROM gp_region A WHERE A.code
		 * = ''
		 */

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" A.`region_level` AS regionLevel								");
		selectBuffer.append(" FROM															");
		selectBuffer.append(" gp_region A 													");
		selectBuffer.append(" WHERE A.code =												");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(code)) {
			selectBuffer.append(code);
		}
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpRegionUntBll.getListBySQL(map);

		Object obj = resultModel.getData();
		List<Map<String, Object>> list = null;
		if (obj != null) {
			list =CastObjectUtil.cast(obj);
			if (list.size() >= 1) {
				Map<String, Object> tempMap = list.get(0);
				if (tempMap.containsKey("regionLevel")) {
					int level = (int) tempMap.get("regionLevel");
					return level;
				}
			}
		}
		return 0;
	}

	private List<Map<String, Object>> getOriginRegionList() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT A.`code` AS value, A.`name` AS label FROM gp_region A WHERE
		 * A.region_level = '1'
		 */

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" A.`code` AS value,											");
		selectBuffer.append(" A.`name` AS label 											");
		selectBuffer.append(" FROM															");
		selectBuffer.append(" gp_region A 													");
		selectBuffer.append(" WHERE A.region_level = '1'									");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpRegionUntBll.getListBySQL(map);

		Object obj = resultModel.getData();
		List<Map<String, Object>> list = null;
		if (obj != null) {
			list =CastObjectUtil.cast(obj);
			return list;
		}
		return null;
	}

}
