package com.zee.app.extend.swagger.gp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.zee.app.generate.swagger.gp.GpInterfaceGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.parameter.gp.GpInterfaceParameter;
import com.zee.set.enumer.DomainEnum;
import com.zee.set.enumer.DiInterfaceType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CamelCaseUtl;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.DictionaryUtil;
import com.zee.utl.FileUtil;
import com.zee.utl.SnowFlakeSerialNoWorkerUtl;
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
 * @description 系统接口。 对外接口，扩展自GpInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpInterface")
public class GpInterfaceSwgApp extends GpInterfaceGenSwgApp {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpInterface") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpInterface jsonData) {
		ResultModel result = gpInterfaceUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpInterfaceUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpInterfaceParameter.UpdateList jsonData) {
		jsonData.getEntity().setAddTime(DateUtils.getCurrentTime());
		ResultModel result = gpInterfaceUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpInterfaceUntBll.getModel(id);
		if (result.getData() != null) {
			GpInterface gpInterface = (GpInterface) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_interface A                                ");
			selectBuffer.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id       ");
			selectBuffer.append("		WHERE A.id = '" + gpInterface.getId() + "'            ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpInterfaceUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> modelMap = modelList.get(0);
			gpInterface.setDomainName(modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "");
			result.setData(dictionaryUtil.dictTransform(gpInterface));
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
		selectBuffer.append("		A.name name,                                  ");
		selectBuffer.append("		A.domain_id domainId,                                    ");
		selectBuffer.append("		A.table_name tableName,                                  ");
		selectBuffer.append("		A.url url,                                               ");
		selectBuffer.append("		A.remark remark,                                         ");
		selectBuffer.append("		A.add_time addTime,                                      ");
		selectBuffer.append("		A.update_time updateTime,                                      ");
		selectBuffer.append("		IF(A.is_public_code=0,'是','否') isPublicCode,           ");
		selectBuffer.append("		CASE A.type_code WHEN 1 THEN 'GET' WHEN 2 THEN 'POST' ELSE '其它' END AS typeCode");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		gp_interface A                                           ");
		selectBuffer.append("	LEFT JOIN gpr_catalog_interface B ON A.id = B.interface_id   ");
		selectBuffer.append("	LEFT JOIN gp_domain C ON A.domain_id = C.id   ");
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

				if (entityRelatedObject.containsKey("kewwords") && StringUtils.isNotBlank(entityRelatedObject.getString("kewwords"))) {
					selectBuffer.append(String.format(" and( A.name like %1$s or A.url like %1$s  or C.serial_no like %1$s or C.name like %1$s or A.remark like %1$s)", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}

				if (entityRelatedObject.containsKey("interfaceCatalogIds") && StringUtils.isNotBlank(entityRelatedObject.getString("interfaceCatalogIds"))) {
					String interfaceCatalogIds = entityRelatedObject.get("interfaceCatalogIds").toString().replaceAll(",", "','");
					selectBuffer.append(" and B.catalog_id in('").append(interfaceCatalogIds).append("')");
				}
				if (entityRelatedObject.containsKey("tableName") && StringUtils.isNotBlank(entityRelatedObject.getString("tableName")))
					selectBuffer.append(" and A.table_name like '%").append(entityRelatedObject.getString("tableName")).append("%'");

				if (entityRelatedObject.containsKey("url") && StringUtils.isNotBlank(entityRelatedObject.getString("url")))
					selectBuffer.append(" and A.url like '%").append(entityRelatedObject.getString("url")).append("%'");
				if (entityRelatedObject.containsKey("remark") && StringUtils.isNotBlank(entityRelatedObject.getString("remark")))
					selectBuffer.append(" and A.remark like '%").append(entityRelatedObject.getString("remark")).append("%'");
				if (entityRelatedObject.containsKey("isPublicCode") && StringUtils.isNotBlank(entityRelatedObject.getString("isPublicCode")))
					selectBuffer.append(" and A.is_public_code = '").append(entityRelatedObject.getString("isPublicCode")).append("'");
				if (entityRelatedObject.containsKey("typeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("typeCode")))
					selectBuffer.append(" and A.type_code = '").append(entityRelatedObject.getString("typeCode")).append("'");
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

		resultModel = gpInterfaceUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "系统接口列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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

	@RequestMapping(value = "/updateInterfaceConstants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateInterfaceConstants() throws IOException, DateParseException, ParseException {
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			throw new GlobalException("缺少必要参数！");
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (!jsonObject.containsKey("jsConstantsPath"))
			throw new GlobalException("缺少必要参数！");
		ResultModel result = new ResultModel();

		// 删除之前的常量文件
		String jsConstantsPath = jsonObject.getString("jsConstantsPath");
		FileUtil.deleteFile(new File(jsConstantsPath));

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT * FROM gp_interface A");
		sqlMap.put("Sql", selectBuffer.toString());
		result = gpInterfaceUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> interfaceList = CastObjectUtil.cast(result.getData());

		ArrayList<GpInterface> gpInterfaceList = new ArrayList<GpInterface>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo requestMappingInfo = item.getKey();
			HandlerMethod handlerMethod = item.getValue();

			String domainId = null;
			String id = Tools.getUUID();
			Byte isGenerateCode = CustomSymbolic.DCODE_BOOLEAN_T;
			String name = null;
			String remark = null;
			String serialNo = String.valueOf(new SnowFlakeSerialNoWorkerUtl(CustomSymbolic.SNOWFLAKE_SERIAL_NO_DATACENTER_ID, CustomSymbolic.SNOWFLAKE_SERIAL_NO_WORKDER_ID).nextId());

			String tableName = null;
			Byte typeCode = null;
			String url = null;

			// 获取接口路径URL
			PatternsRequestCondition patternsRequestCondition = requestMappingInfo.getPatternsCondition();
			for (String urlPattern : patternsRequestCondition.getPatterns()) {
				if (urlPattern.equals("/error"))
					continue;
				if (urlPattern.contains("{")) {
					urlPattern = urlPattern.substring(0, urlPattern.indexOf("{") - 1) + "/";
				}
				url = urlPattern;
			}
			if (StringUtils.isBlank(url))
				continue;

			if (!handlerMethod.getMethod().getDeclaringClass().getSimpleName().contains("GenSwgApp"))
				isGenerateCode = CustomSymbolic.DCODE_BOOLEAN_F;
			// 获取JS常量文件中的变量名，并写入JS常量文件中
			String className = handlerMethod.getMethod().getDeclaringClass().getSimpleName().replaceAll("GenSwgApp", "").replaceAll("SwgApp", "").replaceAll("Controller", "");
			String methodName = handlerMethod.getMethod().getName();
			String jsConstantName = "var RU_" + (className + "_" + methodName).toUpperCase();

			String path = "\"" + url + "\";";
			String text = jsConstantName + " = " + path + System.getProperty("line.separator", "/n");

			FileOutputStream fileOutputStream = new FileOutputStream(new File(jsConstantsPath), true);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(text.getBytes());
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			// 处理应用领域
			if (className.toUpperCase().startsWith(DomainEnum.DA.name()))
				domainId = DomainEnum.DA.getId();
			else if (className.toUpperCase().startsWith(DomainEnum.MF.name()))
				domainId = DomainEnum.MF.getId();
			else if (className.toUpperCase().startsWith(DomainEnum.WP.name()))
				domainId = DomainEnum.WP.getId();
			else if (className.toUpperCase().startsWith(DomainEnum.GP.name()))
				domainId = DomainEnum.GP.getId();
			else
				domainId = DomainEnum.GP.getId();

			// 获取接口调用方式typeCode
			RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
			String type = methodsCondition.toString();
			if (type != null && type.startsWith("[") && type.endsWith("]")) {
				type = type.substring(1, type.length() - 1);
				if (DiInterfaceType.POST.name().equals(type)) {
					typeCode = DiInterfaceType.POST.getCode();
				} else if (DiInterfaceType.GET.name().equals(type)) {
					typeCode = DiInterfaceType.GET.getCode();
				} else {
					typeCode = DiInterfaceType.POSTORGET.getCode();
				}
			}

			// 获取接口名称name和接口描述remark
			ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				name = apiOperation.value();
				remark = apiOperation.notes();
			}

			// 将类名转换为表名
			tableName = CamelCaseUtl.toUnderlineName(className);

			// 将接口信息插入数据库
			GpInterface gpInterface = new GpInterface();
			gpInterface.setId(id);
			gpInterface.setAddTime(DateUtils.getCurrentTime());
			gpInterface.setName(name);
			gpInterface.setDomainId(domainId);
			gpInterface.setTableName(tableName);
			gpInterface.setUrl(url);

			gpInterface.setRemark(remark);
			gpInterface.setTypeCode(typeCode);
			gpInterface.setUpdateTime(DateUtils.getCurrentTime());

			// 如果是POST接口，设为非公共接口
			gpInterface.setIsPublicCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (typeCode == DiInterfaceType.POST.getCode())
				gpInterface.setIsPublicCode(CustomSymbolic.DCODE_BOOLEAN_F);

			// 如果包含，说明是修改记录，是否公共、添加时间、编号这3个字段不做修改、
			for (Map<String, Object> interfaceMap : interfaceList) {
				if (interfaceMap.get("url").equals(url)) {
					gpInterface.setId(interfaceMap.get("id").toString());
					if (interfaceMap.get("serial_no") != null)
						gpInterface.setSerialNo(interfaceMap.get("serial_no").toString());
					if (interfaceMap.get("is_public_code") != null)
						gpInterface.setIsPublicCode(Byte.valueOf(interfaceMap.get("is_public_code").toString()));
					gpInterface.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(interfaceMap.get("add_time").toString()));
					break;
				}
			}

			gpInterfaceList.add(gpInterface);

		}

		result = gpInterfaceUntBll.addListWithDffOrAdd(gpInterfaceList);

		result.setResultMessage("接口记录更新成功……");
		return result;
	}

}
