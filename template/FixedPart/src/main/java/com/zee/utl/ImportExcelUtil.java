package com.zee.utl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;

public class ImportExcelUtil {

	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/**
	 * 将流中的Excel数据转成List<Map>
	 * 
	 * @param in
	 *            输入流
	 * @param fileName
	 *            文件名（判断Excel版本）
	 * @param mapping
	 *            字段名称映射
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> parseExcel(InputStream in, String fileName) throws Exception {
		// 根据文件名来创建Excel工作薄
		Workbook work = getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		org.apache.poi.ss.usermodel.Sheet sheet = null;
		Row row = null;
		Row row2 = null;
		Cell cell = null;
		Cell cell2 = null;
		// 返回数据
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null)
				continue;

			// 取第一行标题
			row = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(0);
			row2 = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(1);
			String title[] = null;
			String title2[] = null;
			if (row != null) {
				title = new String[row.getLastCellNum()];

				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					title[y] = (String) getCellValue(cell);
				}
				title2 = new String[row2.getLastCellNum()];

				for (int y = row2.getFirstCellNum(); y < row2.getLastCellNum(); y++) {
					cell2 = row2.getCell(y);
					title2[y] = (String) getCellValue(cell2);
				}

			} else
				continue;
			// log.info(JSON.toJSONString(title));

			// 遍历当前sheet中的所有行
			for (int j = 2; j < ((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum() + 1; j++) {
				row = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(j);
				Map<String, Object> m = new HashMap<String, Object>();
				// 遍历所有的列
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					String key = title2[y];
					// log.info(JSON.toJSONString(key));
					m.put(key, getCellValue(cell));
				}
				ls.add(m);
			}

		}
		work.close();
		return ls;
	}

	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr
	 *            ,fileName
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字

		switch (cell.getCellType()) {
		case STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}

	public static <T> Object transformJsonToBeanList(String jsonArr, Class<T> type) {
		JSONArray jsonArray = JSONArray.fromObject(jsonArr);
		return JSONArray.toCollection(jsonArray, type);
	}

}
