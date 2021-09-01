package com.zee.utl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lxy
 * @createDate 2018年6月20日 下午7:32:13
 * @updateDate 2018年6月20日 下午7:32:13
 * @description Bean工具
 */
public class BeanUtil {
	
	private static Logger log = LoggerFactory.getLogger(BeanUtil.class);
	
	public static void populate(Object bean, Map<String, Object> map){
        try {
            //注册格式
            ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
            ConvertUtils.register(new SqlDateConverter(null), java.util.Date.class);
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
            //封装数据
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

	public static Object map2Bean(Map<String, Object> map, Class<?> beanClass) {
		if (map == null)
			return null;

		Object obj = null;
		try {
			obj = beanClass.newInstance();
			BeanUtil.populate(obj, map);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}

	public static Map<String, Object> bean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
	
	/**
	 * 将实体中的null转为空字符串
	 * @param obj
	 * @return
	 */
	public static Object checkNull(Object obj) {
		Class<? extends Object> clazz = obj.getClass();
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 可访问私有变量
			field.setAccessible(true);
			// 获取属性类型
			String type = field.getGenericType().toString();
			// 如果type是类类型，则前面包含"class "，后面跟类名
			if ("class java.lang.String".equals(type)) {
				// 将属性的首字母大写
				String methodName = field.getName().replaceFirst(
						field.getName().substring(0, 1),
						field.getName().substring(0, 1).toUpperCase());
				System.out.println(methodName);
				try {
					Method methodGet = clazz.getMethod("get" + methodName);
					// 调用getter方法获取属性值
					String str = (String) methodGet.invoke(obj);
					if (StringUtils.isBlank(str)) {
						// Method methodSet = clazz.getMethod("set" +
						// methodName, new Class[] { String.class });
						// methodSet.invoke(o, new Object[] { "" });
						System.out.println(field.getType()); // class
																// java.lang.String
						// 如果为null的String类型的属性则重新复制为空字符串
						field.set(obj,
								field.getType().getConstructor(field.getType())
										.newInstance(""));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}
