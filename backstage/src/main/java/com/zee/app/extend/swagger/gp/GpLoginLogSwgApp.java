package com.zee.app.extend.swagger.gp;

import java.io.IOException;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpLoginLogGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpLoginLog;
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
 * @description 登录日志。 对外接口，扩展自GpLoginLogGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpLoginLog")
public class GpLoginLogSwgApp extends GpLoginLogGenSwgApp {

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpLoginLogUntBll.getModel(id);
		if (result.getData() != null) {
			GpLoginLog gpLoginLog = (GpLoginLog) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_login_log A                                ");
			selectBuffer.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id       ");
			selectBuffer.append("		WHERE A.id = '" + gpLoginLog.getId() + "'             ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpLoginLogUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> modelMap = modelList.get(0);
			gpLoginLog.setDomainName(modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "");
			result.setData(dictionaryUtil.dictTransform(gpLoginLog));
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
		selectBuffer.append("	SELECT                                                                ");
		selectBuffer.append("		A.id id,                                                          ");
		selectBuffer.append("		A.domain_id domainId,                                             ");
		selectBuffer.append("		B.name domainName,                                             ");
		selectBuffer.append("		A.user_name userName,                                             ");
		selectBuffer.append("		DATE_FORMAT(A.login_time,'%Y-%m-%d %H:%i:%S') loginTime,          ");
		selectBuffer.append("		DATE_FORMAT(A.logout_time,'%Y-%m-%d %H:%i:%S') logoutTime,        ");
		selectBuffer.append("		A.duration duration,                                              ");
		selectBuffer.append("		A.ip ip,                                                          ");
		selectBuffer.append("		A.ip_address ipAddress,                                           ");
		selectBuffer.append("		A.browser browser,                                                ");
		selectBuffer.append("		A.resolution resolution,                                          ");
		selectBuffer.append("		A.os os,                                                          ");
		selectBuffer.append("		IF(A.is_success_code=0,'是','否') isSuccessCode,                  ");
		selectBuffer.append("		A.remark remark                                                   ");
		selectBuffer.append("	FROM                                                                  ");
		selectBuffer.append("		gp_login_log A                                                    ");
		selectBuffer.append("	LEFT JOIN gp_domain B ON A.domain_id = B.id                              ");
		selectBuffer.append("	WHERE                                                                 ");
		selectBuffer.append("		1 = 1                                                             ");

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
					selectBuffer.append(String.format(" and(  A.user_name like %1$s or A.ip like %1$s or A.browser like %1$s or A.os like %1$s )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}
				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName")))
					selectBuffer.append(" and A.user_name like '%").append(entityRelatedObject.getString("userName")).append("%'");
				if (entityRelatedObject.containsKey("ip") && StringUtils.isNotBlank(entityRelatedObject.getString("ip")))
					selectBuffer.append(" and A.ip like '%").append(entityRelatedObject.getString("ip")).append("%'");
				if (entityRelatedObject.containsKey("selectSuccessCode") && StringUtils.isNotBlank(entityRelatedObject.getString("selectSuccessCode")))
					selectBuffer.append(" and A.is_success_code = '").append(entityRelatedObject.getString("selectSuccessCode")).append("'");
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

		resultModel = gpLoginLogUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "登录日志列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
