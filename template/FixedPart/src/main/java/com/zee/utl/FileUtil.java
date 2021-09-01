package com.zee.utl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * file输入输出工具类
 * @author lxy
 *
 */
@Component
public class FileUtil {
	
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	@Value("${upload.diskPath}")
	private String diskPath;//硬盘存放地址
	
	@Value("${upload.linkPath}")
	private String linkPath;//访问地址
	
	public String getDiskPath() {
		return diskPath;
	}

	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}

	public String getLinkPath() {
		return linkPath;
	}

	public void setLinkPath(String linkPath) {
		this.linkPath = linkPath;
	}


	/**
	 * 根据文件名判断文件类型并拼接输出路径
	 * @param fileName
	 * @return
	 */
	public Map<String, String> getPathByFileName(String fileName) {
		Map<String, String> map = new HashMap<String, String>();
		if (fileName == null) {
			log.error("传入的文件为空！");
		} else {
			// 获取文件后缀名并转化为写，用于后续比较
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			// 创建图片类型数组
			String img[] = { "bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx",
					"tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf",
					"ufo", "eps", "ai", "raw", "wmf" };
			for (int i = 0; i < img.length; i++) {
				if (img[i].equals(fileType)) {
					map.put("diskPath", diskPath + "img/");
					map.put("linkPath", linkPath + "img/");
				}
			}
			// 创建文档类型数组
			String document[] = { "txt", "doc", "docx", "xls", "htm", "html",
					"jsp", "rtf", "wpd", "pdf", "ppt" };
			for (int i = 0; i < document.length; i++) {
				if (document[i].equals(fileType)) {
					map.put("diskPath", diskPath + "document/");
					map.put("linkPath", linkPath + "document/");
				}
			}
			// 创建视频类型数组
			String video[] = { "mp4", "avi", "mov", "wmv", "asf", "navi",
					"3gp", "mkv", "f4v", "rmvb", "webm" };
			for (int i = 0; i < video.length; i++) {
				if (video[i].equals(fileType)) {
					map.put("diskPath", diskPath + "video/");
					map.put("linkPath", linkPath + "video/");
				}
			}
			// 创建音乐类型数组
			String music[] = { "mp3", "wma", "wav", "mod", "ra", "cd", "md",
					"asf", "aac", "vqf", "ape", "mid", "ogg", "m4a", "vqf" };
			for (int i = 0; i < music.length; i++) {
				if (music[i].equals(fileType)) {
					map.put("diskPath", diskPath + "music/");
					map.put("linkPath", linkPath + "music/");
				}
			}
		}
		return map;
	}
	
	
	/**
	 * 生成随机名字，前四位随机数，后面年月日_时分秒
	 * @return
	 */
	public static String getRandomFileName() {
		Random r = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		StringBuffer sb = new StringBuffer();
		sb.append(sdf.format(new Date()));
		sb.append("_");
		sb.append(String.format("%02d",r.nextInt(100)));
		sb.append(String.format("%02d",r.nextInt(100)));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomFileName());
	}
	
	/**
	 * Java文件操作 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * Java文件操作 获取不带扩展名的文件名
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	
	/**
	 * 读取指定路径文本文件
	 * @param filePath
	 * @return
	 */
	public static String readText(String filePath) {
		StringBuilder str = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String s;
			try {
				while ((s = in.readLine()) != null)
					str.append(s + '\n');
			} finally {
				in.close();
			}
		} catch (IOException e) {
			log.error("方法read异常", e);
			e.printStackTrace();
		}
		return str.toString();
	}

	/**
	 * 写出到指定路径文本文件
	 * @param dirPath 目录名
	 * @param fileName 文件名
	 * @param append 为true表示追加，false表示重头开始写
	 * @param text 要写入的文本字符串，text为null时直接返回
	 */
	public static void writeText(String dirPath, String fileName, boolean append, String text) {
		if (text == null){
			return;
		}
		File dir = new File(dirPath);
		if (!dir.exists()) {//文件不存在则创建
			dir.mkdirs();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(dirPath + fileName, append));
			try {
				out.write(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			log.error("方法write异常", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @param dirPath 目录
	 * @param fileName 文件名
	 * @param bs
	 */
	public static void writeFile(String dirPath, String fileName, byte[] bs) {
		if (bs == null){
			return;
		}
		File dir = new File(dirPath);
		if (!dir.exists()) {//文件不存在则创建
			dir.mkdirs();
		}
		try {
			FileUtils.writeByteArrayToFile(new File(dirPath + fileName), bs);
		} catch (IOException e) {
			log.error("方法write异常", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean
	 */
	public static boolean deleteFile(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	
}
