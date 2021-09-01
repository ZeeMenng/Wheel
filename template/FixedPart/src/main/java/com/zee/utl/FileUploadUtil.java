package com.zee.utl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.zee.app.base.BaseApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * 上传工具类，待完善
 * @author lxy
 *
 */
@RestController
@RequestMapping(value = "/extend/swagger/utl/utlUploadUtil")
public class FileUploadUtil extends BaseApp {
	@Value("${upload.diskPath}")
	private String diskPath;// 硬盘存放地址

	@Value("${upload.linkPath}")
	private String linkPath;// 访问地址

	@Autowired
	private FileUtil fileUtil;

	private static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

	@Value("${upload.swfPath}")
	private String swfPath;// flvplayer.swf播放器地址

	private String commonUpload(List<MultipartFile> files) {
		String path = "";
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				log.error("上传失败，因为文件是空的");
			}
			try {
				String originalName = file.getOriginalFilename();
				String postfix = originalName.substring(originalName.lastIndexOf(".") + 1);
				String newName = FileUtil.getRandomFileName() + "." + postfix.toUpperCase();// 文件新名称，随机数加时间防止重复
				FileUtil.writeFile(fileUtil.getPathByFileName(newName).get("diskPath"), newName, file.getBytes());
				path = newName;
			} catch (Exception e) {
				log.error("上传失败", e);
			}
		}
		return path;
	}

	/**
	 * fileUpload文件上传（单个或多个）
	 * 前端文件名：upload
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel fileUpload(MultipartHttpServletRequest multipartRequest) {
		ResultModel resultModel = new ResultModel();

		// 存储路径
		String savePath = request.getServletContext().getRealPath(diskPath);
		// 完整路径
		String absolutePath = savePath + File.separator;
		// 用于在数据库存储的路径-从文件夹开始
		String relativePath = diskPath + "/";
		// 如果不存在，创建文件夹
		File file = new File(absolutePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileNames = "";
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile multipartFile = entity.getValue();
			String originalFilename = multipartFile.getOriginalFilename();
			String postfix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

			String fileName = Tools.getResouceName();
			File uploadFile = new File(absolutePath + fileName.toString());
			try {
				FileCopyUtils.copy(multipartFile.getBytes(), uploadFile);
				fileNames += fileName + "|";
			} catch (IOException e) {
				e.printStackTrace();
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				resultModel.setResultMessage("上传文件失败" + e.getMessage());
				return resultModel;
			}
		}

		if (fileNames.endsWith("|")) {
			fileNames = fileNames.substring(0, fileNames.length() - 1);
		}

		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		resultModel.setResultMessage("上传文件成功");

		resultModel.setData(relativePath + fileNames);

		List<MultipartFile> files = multipartRequest.getFiles("fileTitleImage");
		String path = this.commonUpload(files);
		resultModel.setData(path);

		return resultModel;
	}

	/**
	 * CKEditor编辑器图片上传实现
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ckImgUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void ckImgUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("upload");
		String fileName = this.commonUpload(files);
		String linkPath = fileUtil.getPathByFileName(fileName).get("linkPath") + fileName;
		String funNum = request.getParameter("CKEditorFuncNum");
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + funNum + ",'" + linkPath + "',''" + ")");
		out.println("</script>");
		out.flush();
		out.close();
	}

	@ResponseBody
	@RequestMapping(value = "/ckVideoUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> ckVideoUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("upload");
		String funNum = request.getParameter("CKEditorFuncNum");
		String fileName = this.commonUpload(files);
		String path = swfPath + "?vcastr_file=" + fileUtil.getPathByFileName(fileName).get("linkPath") + fileName;
		String resp = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + funNum + ",'" + path + "','')</script>";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<String>(resp, headers, HttpStatus.OK);
	}

}
