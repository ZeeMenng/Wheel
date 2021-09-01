package com.zee.utl;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author Zee
 * @createDate 2020年11月17日 下午9:38:18
 * @updateDate 2020年11月17日 下午9:38:18
 * @description 将对象属性值中的NULL或空字符串转换成特殊编码，以配合MyBatis的检验（入空值）。
 */
public class ClassFieldNullable {

	public static <T> T convertNull(T t, ArrayList<String> fieldNameList) {

		for (String fieldName : fieldNameList) {
			try {
				Field field;

				field = t.getClass().getSuperclass().getDeclaredField(fieldName);

				field.setAccessible(true);
				Object fieldValue = field.get(t);
				if (fieldValue == null) {
					field.set(t, "-1");
				} else if (StringUtils.isEmpty(fieldValue.toString())) {
					field.set(t, "-2");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return t;

	}

	public static <T> ArrayList<T> convertNull(ArrayList<T> tList, ArrayList<String> fieldNameList) {
		if (tList == null)
			return tList;
		ArrayList<T> newTList = new ArrayList<T>();
		for (T t : tList) {
			for (String fieldName : fieldNameList) {
				try {
					Field field = t.getClass().getSuperclass().getDeclaredField(fieldName);
					field.setAccessible(true);
					Object fieldValue;
					fieldValue = field.get(t);
					if (fieldValue == null) {
						field.set(t, "-1");
					} else if (StringUtils.isEmpty(fieldValue.toString())) {
						field.set(t, "-2");
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
			newTList.add(t);
		}
		return newTList;

	}

}
