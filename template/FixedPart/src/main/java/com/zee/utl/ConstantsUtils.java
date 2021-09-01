package com.zee.utl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.zee.bll.extend.unity.gp.GpDictionaryTypeUntBll;
import com.zee.bll.extend.unity.gp.GpInterfaceUntBll;
import com.zee.bll.extend.unity.gp.GpPageUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.extend.gp.GpPage;
import com.zee.set.enumer.DiInterfaceType;
import com.zee.set.enumer.OperResult;
import com.zee.set.symbolic.CustomSymbolic;

import net.sf.json.JSONObject;

@RestController
public class ConstantsUtils {

	// 读取目录路径
	private static String READ_PATH = "E://workspace//芒果大数据//代码//PC//FrontPage//pc//ss//gp//html";
	// private static String READ_PATH =
	// "E://JAVA//JavaProject//HuaPingMango//代码//PC//FrontPage//pc//ss//gp//html";
	// 写目录路径
	private static String WRITE_PATH = "E://JAVA//JavaProject//HuaPingMango//代码//PC//FrontPage//pc//global//js//constant//";
	// private static String WRITE_PATH =
	// "E://workspace//芒果大数据//代码//PC//FrontPage//pc//global//js//constant//";
	// private static String WRITE_PATH =
	// "E://JAVA//JavaProject//HuaPingMango//代码//PC//FrontPage//pc//global//js//constant//";
	// 生成页面路径js名称
	private static String TRAVERSE_PAGE = "Page.js";
	// 生成接口js名称
	private static String TRAVERSE_INTERFACE = "Interface.js";
	// 生成字典js
	private static String TRAVERSE_DICTIONARY_JS = "Dictionary.js";
	// 生成字典java
	private static String TRAVERSE_DICTIONARY_JAVA = "SymbolicConstant.java";
	// 写目录路径
	private static String WRITE_DICTIONARY_JAVA_PATH = "E://workspace//芒果大数据//代码//PC//Mango//src//main//java//com//jusfoun//utl//";
	// private static String WRITE_DICTIONARY_JAVA_PATH =
	// "E://JAVA//JavaProject//HuaPingMango//代码//PC//Mango//src//main//java//com//jusfoun//utl";
	@Autowired
	@Qualifier("gpPageUntBll")
	protected GpPageUntBll gpPageUntBll;
	@Autowired
	@Qualifier("gpDictionaryTypeUntBll")
	protected GpDictionaryTypeUntBll gpDictionaryTypeUntBll;
	@Autowired
	@Qualifier("gpInterfaceUntBll")
	protected GpInterfaceUntBll gpInterfaceUntBll;
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@RequestMapping(value = "constants")
	public ResultModel constants(@RequestParam String type) {
		ResultModel result = new ResultModel();
		try {
			if (type.equals("interface")) {
				FileUtil.deleteFile(new File(WRITE_PATH + TRAVERSE_INTERFACE));// 删除之前的
				interfaceMethod();
				System.out.println("生成接口js，插入接口数据，执行完毕");
			} else if (type.equals("page")) {
				FileUtil.deleteFile(new File(WRITE_PATH + TRAVERSE_PAGE));// 删除之前的
				pageMethod(READ_PATH, "html");
				System.out.println("生成页面js，插入页面数据，执行完毕");
			} else if (type.equals("dict")) {
				dictionaryMethod();
				System.out.println("生成字典类型js常量，java常量，执行完毕");
			}
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setResultMessage(OperResult.CUSTOM_S.getText());
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultMessage(OperResult.CUSTOM_F.getText() + "：" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public void interfaceMethod() throws Exception {

		ResultModel resultModel = new ResultModel();
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT A.url url FROM gp_interface A");
		sqlMap.put("Sql", selectBuffer.toString());
		String jsonStr = "{\"pageIndex\":1,\"pageSize\":10000}";
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		sqlMap.put("Page", jsonObject);
		resultModel = gpInterfaceUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		List<String> urls = new ArrayList<String>();
		for (Map map2 : modelList) {
			urls.add(map2.get("url").toString());
		}

		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			HashMap<String, String> handlerMethodsMap = new HashMap<String, String>();
			RequestMappingInfo info = item.getKey();
			HandlerMethod method = item.getValue();

			PatternsRequestCondition p = info.getPatternsCondition();
			for (String url : p.getPatterns()) {
				if (url.equals("/error"))
					continue;
				if (url.contains("{")) {
					url = url.substring(0, url.indexOf("{") - 1) + "/";
				}
				handlerMethodsMap.put("url", url);
			}
			String className = method.getMethod().getDeclaringClass().getSimpleName();
			className = className.replaceAll("GenSwgApp", "");
			className = className.replaceAll("SwgApp", "");
			className = className.replaceAll("Controller", "");
			handlerMethodsMap.put("className", className); // 类名
			handlerMethodsMap.put("method", method.getMethod().getName()); // 方法名
			RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
			String type = methodsCondition.toString();
			if (type != null && type.startsWith("[") && type.endsWith("]")) {
				type = type.substring(1, type.length() - 1);
				handlerMethodsMap.put("type", type); // 请求类型
			}
			// 变量名
			String name = "var RU_" + (handlerMethodsMap.get("className") + "_" + handlerMethodsMap.get("method")).toUpperCase();
			// 路径
			String path = "\"" + handlerMethodsMap.get("url") + "\";";
			String text = name + " = " + path + System.getProperty("line.separator", "/n");
			FileUtil.writeText(WRITE_PATH, TRAVERSE_INTERFACE, true, text);
			if (StringUtils.isNotBlank(handlerMethodsMap.get("url")) && !urls.contains(handlerMethodsMap.get("url"))) {
				GpInterface jsonData = new GpInterface();
				jsonData.setId(Tools.getUUID());
				jsonData.setDomainId(Tools.getUUID());
				jsonData.setTableName(handlerMethodsMap.get("className"));
				jsonData.setUrl(handlerMethodsMap.get("url"));
				jsonData.setAddTime(new Date());
				jsonData.setIsPublicCode((byte) 0);
				if ("POST".equals(handlerMethodsMap.get("type"))) {
					jsonData.setTypeCode(DiInterfaceType.POST.getCode());
				} else if ("GET".equals(handlerMethodsMap.get("type"))) {
					jsonData.setTypeCode(DiInterfaceType.GET.getCode());
				}
				gpInterfaceUntBll.add(jsonData);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void dictionaryMethod() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                   ");
		selectBuffer.append("		A.id id,                                             ");
		selectBuffer.append("		A.name name,                                         ");
		selectBuffer.append("		A.constant_name constantName                         ");
		selectBuffer.append("	FROM                                                     ");
		selectBuffer.append("		gp_dictionary_type A                                 ");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpDictionaryTypeUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		String text_js = FileUtil.readText(WRITE_PATH + TRAVERSE_DICTIONARY_JS) + System.getProperty("line.separator", "/n");
		String text_java = FileUtil.readText(WRITE_DICTIONARY_JAVA_PATH + TRAVERSE_DICTIONARY_JAVA).replaceAll("}", "") + System.getProperty("line.separator", "/n");
		for (Map<String, Object> modelMap : modelList) {
			String id = modelMap.get("id").toString();
			String constantName = modelMap.get("constantName").toString();
			String name = modelMap.get("name").toString();
			text_js += "var " + constantName + " = " + "\"" + id + "\";//" + name + System.getProperty("line.separator", "/n");
			text_java += "public static final String " + constantName + " = " + "\"" + id + "\";//" + name + System.getProperty("line.separator", "/n");
		}
		text_java += "}";
		// 生成js常量
		FileUtil.writeText(WRITE_PATH, TRAVERSE_DICTIONARY_JS, false, text_js);
		// 生成java常量
		FileUtil.writeText(WRITE_DICTIONARY_JAVA_PATH, TRAVERSE_DICTIONARY_JAVA, false, text_java);
	}

	/**
	 * @param filePath
	 * @param suffix 后缀
	 * @param pre_text 生成内容前缀
	 * @throws IOException
	 */
	public void pageMethod(String filePath, String suffix) throws IOException {

		ResultModel resultModel = new ResultModel();
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT A.url url FROM gp_page A");
		sqlMap.put("Sql", selectBuffer.toString());
		String jsonStr = "{\"pageIndex\":1,\"pageSize\":10000}";
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		sqlMap.put("Page", jsonObject);
		resultModel = gpPageUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		List<String> urls = new ArrayList<String>();
		for (Map map2 : modelList) {
			urls.add(map2.get("url").toString());
		}

		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						// System.out.println("文件夹:" + file2.getAbsolutePath());
						pageMethod(file2.getAbsolutePath(), suffix);
					} else {
						String fileName = file2.getName();
						if (fileName.contains("List")) {
							fileName = fileName.replaceAll("List", "_List");
						} else if (fileName.contains("Add")) {
							fileName = fileName.replaceAll("Add", "_Add");
						} else if (fileName.contains("Edit")) {
							fileName = fileName.replaceAll("Edit", "_Edit");
						} else if (fileName.contains("Delete")) {
							fileName = fileName.replaceAll("Delete", "_Delete");
						} else if (fileName.contains("Detail")) {
							fileName = fileName.replaceAll("Detail", "_Detail");
						}
						if (suffix.equals(FileUtil.getExtensionName(fileName))) {
							String name = "var RP_";
							String path = file2.getAbsolutePath().replaceAll("\\\\", "/");
							if (path.contains("/pc")) {
								path = path.substring(path.indexOf("/pc"));
							}
							String proName = path.replaceAll("/pc/ss/gp/html/", "");
							proName = proName.substring(0, proName.indexOf("/"));
							name += proName.toUpperCase();
							String text = name + (FileUtil.getFileNameNoEx(fileName)).toUpperCase() + " = \"" + path + "\";" + System.getProperty("line.separator", "/n");
							FileUtil.writeText(WRITE_PATH, TRAVERSE_PAGE, true, text);
							if (!urls.contains(path)) {
								GpPage jsonData = new GpPage();
								jsonData.setId(Tools.getUUID());
								jsonData.setDomainId(Tools.getUUID());
								jsonData.setName("芒果");
								jsonData.setUrl(path);
								jsonData.setAddTime(new Date());
								jsonData.setIsPublicCode((byte) 0);
								gpPageUntBll.add(jsonData);
							}
						}
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}

}