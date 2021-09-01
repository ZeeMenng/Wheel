package com.zee.app.generate.swagger.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import com.zee.app.base.BaseApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.symbolic.CustomSymbolic;

import net.sf.json.JSONArray;

public class BaseSwgApp extends BaseApp {

	@Value("${upload.diskPath}")
	public String diskPath;// 硬盘存放地址

	@Value("${upload.linkPath}")
	public String linkPath;// 访问地址

	@Resource
	public JdbcTemplate jdbcTemplate;

	protected GpUser getCurrentUser() {
		Object object = request.getAttribute(CustomSymbolic.REQUEST_CURRENT_USER);
		return object == null ? null : (GpUser) object;
	}

	protected String[] getResourcePathArray(String resourcePath) {
		if (StringUtils.isBlank(resourcePath))
			return new String[] {};
		String[] resourcePathArray = resourcePath.split("\\|");
		for (int i = 0; i < resourcePathArray.length; i++) {
			resourcePathArray[i] = linkPath + resourcePathArray[i];
		}
		return resourcePathArray;
	}

	@SuppressWarnings({ "unchecked" })
	protected Object exportExcel(String fileName, JSONArray titleArray, ResultModel resultModel) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("统计表");

		HSSFRow titleHssfRow = sheet.createRow(0);

		for (int i = 0; i < titleArray.size(); i++) {
			sheet.setColumnWidth((i + 1), 17 * 256);
		}

		HSSFCellStyle titleHssfCellStyle = hssfWorkbook.createCellStyle();
		HSSFFont hssfFont = hssfWorkbook.createFont();
		hssfFont.setBold(true);
		titleHssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
		titleHssfCellStyle.setFont(hssfFont);

		HSSFCell contentHssfCell = null;
		for (int i = 0; i < titleArray.size(); i++) {
			contentHssfCell = titleHssfRow.createCell(i);
			contentHssfCell.setCellValue(titleArray.getJSONObject(i).getString("columnText"));
			contentHssfCell.setCellStyle(titleHssfCellStyle);
		}

		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		titleHssfCellStyle = hssfWorkbook.createCellStyle();
		titleHssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
		hssfFont.setBold(false);
		titleHssfCellStyle.setFont(hssfFont);

		int rowNum = 1;
		for (Map<String, Object> map : list) {
			HSSFRow row = sheet.createRow(rowNum);

			for (int i = 0; i < titleArray.size(); i++) {
				contentHssfCell = row.createCell(i);
				{
					Object value = map.get(titleArray.getJSONObject(i).getString("columnName"));

					String cellValue = value == null ? "" : value.toString();
					contentHssfCell.setCellValue(cellValue);
				}

			}

			rowNum++;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		hssfWorkbook.write(out);
		out.close();
		hssfWorkbook.close();

		ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());

		final String userAgent = request.getHeader("USER-AGENT");
		if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
			fileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		return FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	@SuppressWarnings({ "unchecked" })
	protected Object exportExcel(String fileName, JSONArray titleArray) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("统计表");

		HSSFRow titleHssfRow = sheet.createRow(0);

		for (int i = 0; i < titleArray.size(); i++) {
			sheet.setColumnWidth((i + 1), 17 * 256);
		}

		HSSFCellStyle titleHssfCellStyle = hssfWorkbook.createCellStyle();
		HSSFFont hssfFont = hssfWorkbook.createFont();
		hssfFont.setBold(true);
		titleHssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
		titleHssfCellStyle.setFont(hssfFont);

		HSSFCell contentHssfCell = null;
		for (int i = 0; i < titleArray.size(); i++) {
			contentHssfCell = titleHssfRow.createCell(i);
			contentHssfCell.setCellValue(titleArray.getJSONObject(i).getString("columnText"));
			contentHssfCell.setCellStyle(titleHssfCellStyle);
		}

		titleHssfCellStyle = hssfWorkbook.createCellStyle();
		titleHssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
		hssfFont.setBold(false);
		titleHssfCellStyle.setFont(hssfFont);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		hssfWorkbook.write(out);
		out.close();
		hssfWorkbook.close();

		ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());

		final String userAgent = request.getHeader("USER-AGENT");
		if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
			fileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		return FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

}
