package com.zee.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zee.set.exception.GlobalException;

public class ReadExcelUtil {

	private static final String[] excel = { ".xls", ".xlsx" };
	private static final String EXTENSION_XLS = ".xls";
	private static final String EXTENSION_XLSX = ".xlsx";
	static InputStream is;

	/***
	 * <pre>
	 * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
	 *   xls:HSSFWorkbook
	 *   xlsx：XSSFWorkbook
	 * @param filePath
	 * @return
	 * @throws IOException
	 * </pre>
	 */
	public static Workbook getWorkbook(String filePath) throws IOException {
		Workbook workbook = null;
		is = new FileInputStream(filePath);
		// 将文件路径转小写
		String filePathLow = filePath.toLowerCase();
		for (int temp = 0; temp < excel.length; temp++) {
			if (filePathLow.endsWith(excel[temp])) {
				if (filePathLow.toLowerCase().endsWith(EXTENSION_XLS)) {
					workbook = new HSSFWorkbook(is);
				} else if (filePathLow.toLowerCase().endsWith(EXTENSION_XLSX)) {
					workbook = new XSSFWorkbook(is);
				}
			}
		}

		return workbook;
	}

	/**
	 * 文件检查
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws FileFormatException
	 */
	public static void preReadCheck(String filePath) throws FileNotFoundException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}

		if (!(filePath.toLowerCase().endsWith(EXTENSION_XLS) || filePath.toLowerCase().endsWith(EXTENSION_XLSX))) {
			throw new GlobalException("传入的文件不是excel");
		}
	}

	/**
	 * 取单元格的值
	 * 
	 * @param cell
	 *            单元格对象
	 * @param treatAsStr
	 *            为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
	 * @return
	 */
	public static String getCellValue(Cell cell, boolean a) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case STRING:
			return cell.getStringCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		case BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

	// /**
	// * 读取excel文件内容
	// *
	// * @param filePath
	// * @throws FileNotFoundException
	// * @throws FileFormatException
	// */
	// public List<User> readExcel(String filePath,MultipartFile Mfile) throws
	// Exception {
	// // 检查
	// preReadCheck(filePath);
	// // 获取workbook对象
	// Workbook workbook = null;
	// List<User> list = new ArrayList<User>();
	//
	// try {
	// workbook = getWorkbook(filePath);
	// // 读文件 一个sheet一个sheet地读取workbook.getNumberOfSheets();
	// Sheet sheet = workbook.getSheetAt(0);
	//
	// int firstRowIndex = sheet.getFirstRowNum();
	// int lastRowIndex = sheet.getLastRowNum();
	//
	// boolean flag = true;
	// String result = "2";
	// // 读取数据行
	// for (int rowIndex = firstRowIndex + 2; rowIndex < lastRowIndex;
	// rowIndex++) {
	// Row currentRow = sheet.getRow(rowIndex);// 当前行
	// int colnum = 1;
	// if (currentRow != null && currentRow.getCell(0) != null &&
	// !"".equals(currentRow.getCell(0))) {
	// User user = new User();
	// user.setEmpcode(getCellValue(
	// currentRow.getCell(colnum), true));
	// user.setUserName(getCellValue(
	// currentRow.getCell(colnum + 1), true));
	//
	// list.add(user);
	// }
	//
	// }
	//
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	//
	// } finally {
	// try {
	// is.close();
	// } catch (IOException e) {
	//
	// }
	// if (workbook != null) {
	// workbook = null;
	// }
	// }
	// return list;
	// }

}