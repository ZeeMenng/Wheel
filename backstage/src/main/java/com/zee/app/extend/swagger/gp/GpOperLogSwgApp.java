package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.HashMap;
import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpOperLogGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.DictionaryUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 操作日志。 对外接口，扩展自GpOperLogGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpOperLog")
public class GpOperLogSwgApp extends GpOperLogGenSwgApp {

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpOperLogUntBll.getModel(id);
		if (result.getData() != null) {
			GpOperLog gpOperLog = (GpOperLog) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_oper_log A                                ");
			selectBuffer.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id       ");
			selectBuffer.append("		WHERE A.id = '" + gpOperLog.getId() + "'             ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpOperLogUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> modelMap = modelList.get(0);
			gpOperLog.setDomainName(modelMap != null ? (modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "") : "");
			result.setData(dictionaryUtil.dictTransform(gpOperLog));
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
		selectBuffer.append("	SELECT                                                       ");
		selectBuffer.append("		A.id id,                                                 ");
		selectBuffer.append("		A.domain_id domainId,                                    ");
		selectBuffer.append("		B.name domainName,                                             ");
		selectBuffer.append("		A.object_id objectId,                                    ");
		selectBuffer.append("		A.oper_type_text operTypeText,                           ");
		selectBuffer.append("		A.table_name tableName,                                  ");
		selectBuffer.append("		A.total_count totalCount,                                ");
		selectBuffer.append("		IF(A.is_success_code=0,'是','否') isSuccessCode,         ");
		selectBuffer.append("		A.add_time addTime                                       ");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		gp_oper_log A                                            ");
		selectBuffer.append("	LEFT JOIN gp_domain B ON A.domain_id = B.id                 ");
		selectBuffer.append("	WHERE                                                        ");
		selectBuffer.append("		1 = 1                                                    ");

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

				if (entityRelatedObject.containsKey("operTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("operTypeCode")))
					selectBuffer.append(" and A.oper_type_code = '").append(entityRelatedObject.getString("operTypeCode")).append("'");
				if (entityRelatedObject.containsKey("operTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("operTypeText")))
					selectBuffer.append(" and A.oper_type_text like '%").append(entityRelatedObject.getString("operTypeText")).append("%'");
				if (entityRelatedObject.containsKey("tableName") && StringUtils.isNotBlank(entityRelatedObject.getString("tableName")))
					selectBuffer.append(" and A.table_name like '%").append(entityRelatedObject.getString("tableName")).append("%'");
				if (entityRelatedObject.containsKey("isSuccessCode") && StringUtils.isNotBlank(entityRelatedObject.getString("isSuccessCode")))
					selectBuffer.append(" and A.is_success_code = '").append(entityRelatedObject.getString("isSuccessCode")).append("'");
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

		resultModel = gpOperLogUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "操作日志列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
