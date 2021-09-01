package com.zee.app.extend.swagger.gp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.zee.app.generate.swagger.gp.GpPageGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.parameter.gp.GpPageParameter;
import com.zee.set.enumer.DomainEnum;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.DictionaryUtil;
import com.zee.utl.FileUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统页面。 对外接口，扩展自GpPageGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpPage")
public class GpPageSwgApp extends GpPageGenSwgApp {

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpPage") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpPage jsonData) {
		ResultModel result = gpPageUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpPageUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpPageParameter.UpdateList jsonData) {
		jsonData.getEntity().setAddTime(DateUtils.getCurrentTime());
		ResultModel result = gpPageUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpPageUntBll.getModel(id);
		if (result.getData() != null) {
			GpPage gpPage = (GpPage) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_page A                                     ");
			selectBuffer.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id       ");
			selectBuffer.append("		WHERE A.id = '" + gpPage.getId() + "'                 ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpPageUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> modelMap = modelList.get(0);
			gpPage.setDomainName(modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "");
			result.setData(dictionaryUtil.dictTransform(gpPage));
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
		selectBuffer.append("	SELECT                                               ");
		selectBuffer.append("		A.id id,                                         ");
		selectBuffer.append("		A.domain_id domainId,                            ");
		selectBuffer.append("		B.name domainName,                            ");
		selectBuffer.append("		A.name name,                                     ");
		selectBuffer.append("		A.url url,                                       ");
		selectBuffer.append("		A.remark remark,                                 ");
		selectBuffer.append("		A.add_time addTime,                              ");
		selectBuffer.append("	  IF(A.is_public_code=0,'是','否') isPublicCode      ");
		selectBuffer.append("	FROM                                                 ");
		selectBuffer.append("		gp_page A                                        ");
		selectBuffer.append("	INNER JOIN gp_domain B ON A.domain_id = B.id         ");
		selectBuffer.append("	WHERE                                                ");
		selectBuffer.append("		1 = 1                                            ");

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
					selectBuffer.append(String.format(" and(  A.name like %1$s or A.url like %1$s )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}

				// 模糊匹配下拉框使用的关键字
				if (entityRelatedObject.containsKey(CustomSymbolic.AUTOCOMPLETE_KEY) && StringUtils.isNotBlank(entityRelatedObject.getString(CustomSymbolic.AUTOCOMPLETE_KEY)))
					selectBuffer.append(" and A.url like '%").append(entityRelatedObject.getString(CustomSymbolic.AUTOCOMPLETE_KEY)).append("%'");

				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("url") && StringUtils.isNotBlank(entityRelatedObject.getString("url")))
					selectBuffer.append(" and A.url like '%").append(entityRelatedObject.getString("url")).append("%'");

				if (entityRelatedObject.containsKey("remark") && StringUtils.isNotBlank(entityRelatedObject.getString("remark")))
					selectBuffer.append(" and A.remark like '%").append(entityRelatedObject.getString("remark")).append("%'");
				if (entityRelatedObject.containsKey("isPublicCode") && StringUtils.isNotBlank(entityRelatedObject.getString("isPublicCode")))
					selectBuffer.append(" and A.is_public_code = '").append(entityRelatedObject.getString("isPublicCode")).append("'");
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

		resultModel = gpPageUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "系统页面列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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

	@RequestMapping(value = "/updatePageConstants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updatePageConstants() throws IOException, DateParseException, ParseException {
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			throw new GlobalException("缺少必要参数！");
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (!jsonObject.containsKey("jsConstantsPath") || !jsonObject.containsKey("frontPagePath"))
			throw new GlobalException("缺少必要参数！");
		String jsConstantsPath = jsonObject.getString("jsConstantsPath");
		String frontPagePath = jsonObject.getString("frontPagePath");
		// 删除之前的常量文件
		FileUtil.deleteFile(new File(jsConstantsPath));
		return traverseGenerate(frontPagePath, jsConstantsPath);
	}

	private ResultModel traverseGenerate(String frontPagePath, String jsConstantsPath) throws IOException, DateParseException, ParseException {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT * FROM gp_page A");
		sqlMap.put("Sql", selectBuffer.toString());
		ResultModel result = gpPageUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> pageList = CastObjectUtil.cast(result.getData());
		ArrayList<GpPage> newPageList = new ArrayList<GpPage>();

		File file = new File(frontPagePath);
		if (!file.exists())
			throw new GlobalException("前端页面所在文件夹不存在!");
		File[] files = file.listFiles();
		if (files.length == 0)
			throw new GlobalException("前端页面所在文件夹为空!");
		for (File file2 : files) {
			if (file2.isDirectory()) {
				traverseGenerate(file2.getAbsolutePath(), jsConstantsPath);
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
				if (!"html".equals(FileUtil.getExtensionName(fileName)))
					continue;

				String name = "var RP_";
				String path = file2.getAbsolutePath().replaceAll("\\\\", "/");
				if (path.contains("/pc")) {
					path = path.substring(path.indexOf("/pc"));
				}
				String proName = path.replaceAll("/pc/ss/gp/html/", "");
				proName = proName.substring(0, proName.indexOf("/"));
				name += proName.toUpperCase();
				String text = name + (FileUtil.getFileNameNoEx(fileName)).toUpperCase() + " = \"" + path + "\";" + System.getProperty("line.separator", "/n");

				FileOutputStream fileOutputStream = new FileOutputStream(new File(jsConstantsPath), true);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
				bufferedOutputStream.write(text.getBytes());
				bufferedOutputStream.flush();
				bufferedOutputStream.close();

				GpPage gpPage = new GpPage();
				gpPage.setDomainId(DomainEnum.GP.getId());
				int beginIndex = path.lastIndexOf('/');
				int endIndex = path.lastIndexOf('.');
				if (beginIndex == -1)
					beginIndex = 0;
				else
					beginIndex++;
				if (endIndex == -1)
					endIndex = path.length();
				gpPage.setName(path.substring(beginIndex, endIndex));
				gpPage.setUrl(path);
				gpPage.setIsPublicCode((byte) 0);
				gpPage.setUpdateTime(DateUtils.getCurrentTime());

				// 如果包含，说明是修改记录，是否公共、添加时间、编号这3个字段不做修改、
				for (Map<String, Object> pageMap : pageList) {
					if (pageMap.get("url").equals(path)) {
						gpPage.setId(pageMap.get("id").toString());
						if (pageMap.get("serial_no") != null)
							gpPage.setSerialNo(pageMap.get("serial_no").toString());
						if (pageMap.get("is_public_code") != null)
							gpPage.setIsPublicCode(Byte.valueOf(pageMap.get("is_public_code").toString()));
						gpPage.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pageMap.get("add_time").toString()));

						break;
					}
				}
				newPageList.add(gpPage);

			}

		}

		result = gpPageUntBll.addListWithDffOrAdd(newPageList);
		result.setResultMessage("页面的JS常量及对应数据库中的记录更新成功！");
		return result;
	}

}
