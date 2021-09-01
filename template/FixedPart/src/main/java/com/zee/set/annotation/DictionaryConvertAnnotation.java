package com.zee.set.annotation;

import java.lang.annotation.*;

/**
 * @author Zee
 * @createDate 2021年2月24日 上午11:02:19
 * @updateDate 2021年2月24日 上午11:02:19
 * @description 字典解析注解类
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryConvertAnnotation {

	/**
	 * 转换的类型
	 * content : 统计内容转换
	 */
	String typeId() default "";

	/**
	 * 支持转换成两种格式，默认为第2种
	 * 1 : {"code":"code","name":"name"}  
	 * 2 : {"code":"name"}
	 */
	int transType() default 2;

	/**
	 * 填写该字段后 该字段会拿到 refField 配置的字段的值并且根据字典转换
	 */
	String codeField() default "";

	/**
	 * 指定字典的转换数据（若指定则以当前字典数据为准）
	 * eg: 0:女,1:男,2:未知
	 */
	String dictionaryList() default "";
	
	/**
	 * 如果转换的值不匹配是否要转换为NULL
	 */
	boolean ifNotMatchConvertToNull() default false;

	/**
	 * 带逗号的多字典 分隔符 默认逗号
	 */
	String delimiter() default ",";


}
