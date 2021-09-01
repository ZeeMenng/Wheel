package com.zee.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.io.IOUtils;

/**
 * @author Zee
 * @createTime 2017年4月13日 下午3:11:58
 * @updateTime 2017年4月13日 下午3:11:58
 * @description Windows系统开发的编码为UTF-8(BOM)导致，BOM是Byte-Order
 *              Mark的意思。一种为了让编辑器自动识别编码 。在文件前3个字节加上了EE,BB,BF，
 *              但标准的UTF-8（Linux不支持BOM ）编码并不会这样做。
 */
public class HuaWeiTest {

	public static void main(String[] args) {

		// 指定查找文件的父目录javafolder
		// File parent = new
		// File("E:/workspace/芒果_BigData/代码/PC/Mango/src/main/java/com/jusfoun");
		File parent = new File("D:/JAVA/JavaProject/HuaPingMango/代码/PC/Mango/src/main/java/com/jusfoun");

		// File parent = new File("E:/workspace/芒果_BigData/代码/PC/Mango/src/main/java/com/jusfoun");
		List<File> javaFiles = findJavaFile(parent);

		int count = 0;
		for (File javaFile : javaFiles) {
			if (isBomFile(javaFile)) {
				count++;

				cleanBom(javaFile);
			}

		}
		System.out.println("bom=" + count);

	}

	/**
	 * 清除bom编码
	 *
	 * @param file
	 */
	public static void cleanBom(File file) {

		File tempFile = new File(file.getAbsolutePath() + ".tmp");

		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(tempFile);
			fis = new FileInputStream(file);
			fis.read(new byte[3]);// 读取前3个byte
			IOUtils.copy(fis, fos);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
		}

		if (!file.delete()) {
			System.out.println("Could not delete file");
		}

		if (!tempFile.renameTo(file)) {
			System.out.println("Could not rename file");
		}

		System.out.println(file.getAbsolutePath() + ">>clean bom");

	}

	/**
	 * 查找子目录下所有java文件
	 *
	 * @param parent
	 * @return
	 */
	public static List<File> findJavaFile(File parent) {

		List<File> result = new ArrayList<File>();

		Stack<File> stack = new Stack<File>();
		stack.push(parent);

		while (!stack.isEmpty()) {

			File popFile = stack.pop();

			if (popFile.isDirectory()) {
				for (File file : popFile.listFiles()) {
					stack.add(file);
				}
			} else {
				if (popFile.getName().endsWith(".java")) {
					result.add(popFile);
				}
			}
		}

		return result;
	}

	/**
	 * 判断是否为bom编码文件
	 *
	 * @param file
	 * @return
	 */
	public static boolean isBomFile(File file) {
		boolean isBom = false;
		FileInputStream fileIS = null;
		try {
			fileIS = new FileInputStream(file);

			byte[] bomBytes = new byte[3];
			fileIS.read(bomBytes);
			System.out.println("===");
			// EF BB BF
			if (bomBytes[0] == -17 && bomBytes[1] == -69 && bomBytes[2] == -65) {
				isBom = true;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fileIS);

		}
		return isBom;
	}
}
