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
 * @createTime 2017��4��13�� ����3:11:58
 * @updateTime 2017��4��13�� ����3:11:58
 * @description Windowsϵͳ�����ı���ΪUTF-8(BOM)���£�BOM��Byte-Order
 *              Mark����˼��һ��Ϊ���ñ༭���Զ�ʶ����� �����ļ�ǰ3���ֽڼ�����EE,BB,BF��
 *              ����׼��UTF-8��Linux��֧��BOM �����벢������������
 */
public class CleanBom {

	public static void main(String[] args) {

		// ָ�������ļ��ĸ�Ŀ¼javafolder
		File parent = new File("D:/JAVA/JavaProject/wheel/backstage/src/main/java/com/zee");

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
	 * ���bom����
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
			fis.read(new byte[3]);// ��ȡǰ3��byte
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
	 * ������Ŀ¼������java�ļ�
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
	 * �ж��Ƿ�Ϊbom�����ļ�
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
