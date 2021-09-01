package com.zee.app.extend.swagger.gp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.zee.app.generate.swagger.gp.GpResourceGenSwgApp;
import com.zee.bll.extend.split.gp.GpPageSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpResource;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/5/7 15:00:36
 * @description 通用文件信息存储表。 对外接口，扩展自GpResourceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpResource")
public class GpResourceSwgApp extends GpResourceGenSwgApp {

	@Value("${upload.swfPath}")
	private String swfPath;// flvplayer.swf播放器地址

	@Autowired
	@Qualifier("gpPageSplBll")
	protected GpPageSplBll gpPageSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpResource") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpResource jsonData) {
		ResultModel result = gpResourceUntBll.add(jsonData);

		return result;
	}

	@RequestMapping(value = "/ckeditorVideoUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel ckeditorVideoUpload(HttpServletRequest request) {

		StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
		ResultModel resultModel = saveUploadFile(multipartRequest);
		@SuppressWarnings("unchecked")
		ArrayList<GpResource> resourceList = (ArrayList<GpResource>) resultModel.getData();
		GpResource resource = resourceList.get(0);
		resource.setPath(resource.getPath());
		resourceList.set(0, resource);
		resultModel.setData(resourceList);
		return resultModel;
	}

	@RequestMapping(value = "/ckeditorFileUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel ckeditorFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
		ResultModel resultModel = saveUploadFile(multipartRequest);
		return resultModel;
	}

	@RequestMapping(value = "/saveUploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel saveUploadFile(MultipartHttpServletRequest multipartRequest) {
		GpUser currentUser = getCurrentUser();
		// if (currentUser == null)
		// throw new GlobalException("未登录用户禁止使用文件上传功能！");

		String domainId = currentUser == null ? "" : currentUser.getCurrentDomain() == null ? "" : currentUser.getCurrentDomain().getId();

		String pageId = null;
		String moduleId = multipartRequest.getParameter("moduleId");
		String pageName = "";

		String pageUrl = multipartRequest.getParameter("pageUrl");
		if (StringUtils.isNotBlank(pageUrl)) {
			ResultModel pageResult = gpPageSplBll.getModelByPageUrl(domainId, pageUrl);
			pageId = pageResult.getObjectId();

			pageName = pageUrl;
			if (pageName.indexOf("/") >= 0)
				pageName = pageName.substring(pageName.lastIndexOf('/') + 1, pageUrl.length());
			if (pageName.endsWith(".html") || pageName.endsWith(".htm") || pageName.endsWith(".jsp"))
				pageName = pageName.substring(0, pageName.lastIndexOf('.'));
		}
		ResultModel result = new ResultModel();
		ArrayList<GpResource> resourceList = new ArrayList<GpResource>();

		Integer priority = 0;

		StringBuffer savePathBuffer = new StringBuffer();
		savePathBuffer.append(diskPath).append(File.separator);
		savePathBuffer.append(currentUser == null ? "" : currentUser.getCurrentDomain() == null ? "" : currentUser.getCurrentDomain().getName()).append(File.separator);
		if (StringUtils.isNotBlank(pageUrl))
			savePathBuffer.append(pageName).append(File.separator);
		savePathBuffer.append(new SimpleDateFormat("yyyyMMdd").format(new Date())).append(File.separator);
		String savePath = savePathBuffer.toString();

		String path = savePath.substring(diskPath.length(), savePath.length());
		path = path.replaceAll("\\\\", "/");

		// 如果不存在，创建文件夹
		File file = new File(savePath.toString());
		if (!file.exists()) {
			file.mkdirs();
		}

		MultiValueMap<String, MultipartFile> fileMap = multipartRequest.getMultiFileMap();

		for (Map.Entry<String, List<MultipartFile>> entity : fileMap.entrySet()) {
			List<MultipartFile> multipartFileList = multipartRequest.getFiles(entity.getKey());
			for (MultipartFile multipartFile : multipartFileList) {
				Long size = multipartFile.getSize();
				String originalName = multipartFile.getOriginalFilename();
				String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();
				String newName = Tools.getResouceName() + "." + extension;
				File uploadFile = new File(savePath + newName.toString());
				try {
					FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
				} catch (IOException e) {
					throw new GlobalException("上传文件失败" + e.getMessage());
				}

				GpResource gpResource = new GpResource();
				gpResource.setId(Tools.getUUID());
				gpResource.setDomainId(domainId);
				gpResource.setExtension(extension);
				gpResource.setModuleId(moduleId);
				gpResource.setNewName(newName);
				gpResource.setOriginalName(originalName);
				gpResource.setPageId(pageId);
				gpResource.setPath(path + newName);
				gpResource.setPriority(priority);
				gpResource.setSize(size);
				gpResource.setUserId(currentUser == null ? null : currentUser.getId());
				gpResource.setAddTime(DateUtils.getCurrentDate());
				resourceList.add(gpResource);
			}
		}

		result = gpResourceUntBll.add(resourceList);
		for (int i = 0; i < resourceList.size(); i++) {
			GpResource resource = resourceList.get(i);
			resource.setPath(linkPath + resource.getPath());
			resourceList.set(i, resource);
		}

		result.setData(resourceList);
		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = gpResourceUntBll.getModel(id);
		if (!result.getIsSuccess())
			return result;
		GpResource resource = (GpResource) result.getData();

		File file = new File(diskPath + resource.getPath());
		if (file.exists()) {
			file.delete();
		}
		result = gpResourceUntBll.delete(id);
		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = gpResourceUntBll.getModel(id);
		if (!result.getIsSuccess())
			return result;
		GpResource resource = (GpResource) result.getData();

		File file = new File(diskPath + resource.getPath());
		if (file.exists()) {
			file.delete();
		}

		result = gpResourceUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = { RequestMethod.GET, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpResourceUntBll.getModel(id);

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
		selectBuffer.append("select A.id id,A.domain_id domainId,A.module_id moduleId,A.page_id pageId,A.user_id userId,A.original_name originalName,A.new_name newName,A.extension extension,A.path path,A.priority priority,A.size size,A.add_time addTime  from gp_resource A inner join gp_resource B on A.id=B.id where 1=1 ");

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

				if (entityRelatedObject.containsKey("originalName") && StringUtils.isNotBlank(entityRelatedObject.getString("originalName")))
					selectBuffer.append(" and A.original_name like '%").append(entityRelatedObject.getString("originalName")).append("%'");
				if (entityRelatedObject.containsKey("beginAddTime") && StringUtils.isNotBlank(entityRelatedObject.getString("beginAddTime")))
					selectBuffer.append(" and A.add_time >='").append(entityRelatedObject.getString("beginAddTime")).append("'");
				if (entityRelatedObject.containsKey("endAddTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endAddTime")))
					selectBuffer.append(" and A.add_time <='").append(entityRelatedObject.getString("endAddTime")).append("'");
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
					selectBuffer.append("A." + Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpResourceUntBll.getListBySQL(map);

		return resultModel;
	}

}
