package com.zee.utl;

import java.lang.reflect.Field;


/**
 * @author Zee
 * @createDate 2020年10月10日 上午11:18:02
 * @updateDate 2020年10月10日 上午11:18:02
 * @description 判断某个类中是否存在某个属性
 */
public class ClassUtil {

	public static Boolean isExistFieldName(Class<?> cla,String fieldName) throws NoSuchFieldException {

		// 获取这个类的所有属性
		Field[] fields = cla.getDeclaredFields();
		boolean flag = false;
		// 循环遍历所有的fields
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(fieldName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
