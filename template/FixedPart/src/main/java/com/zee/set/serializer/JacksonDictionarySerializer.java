package com.zee.set.serializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.utl.CastObjectUtil;

import cn.hutool.core.util.ReflectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Zee
 * @createDate 2021年2月25日 下午4:40:41
 * @updateDate 2021年2月25日 下午4:40:41
 * 
 * @description @JsonSerialize(using = JacksonDictionarySerializer.class)
 *              注解用于字典属性或者getter方法上，结合自定义的@DictionaryConvertAnnotation注解
 *              用于在序列化时将此属性由编码解析为字典文本。如果此属性为新建而非生成的字典字段，可以将此属性的Get方法中直接返回生成字典属性的值
 *               
 * 
 */
@Component
public class JacksonDictionarySerializer extends JsonSerializer<Object> {

	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;

	@Override
	public void serialize(Object dictionaryValue, JsonGenerator generator, SerializerProvider provider) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		StringBuffer dictionaryText = new StringBuffer("");
		String currentName = generator.getOutputContext().getCurrentName();
		// 定义转换类型
		int transType = 2;
		try {
			// 获取字段
			Field field = generator.getCurrentValue().getClass().getDeclaredField(currentName);

			// 获取字典属性
			DictionaryConvertAnnotation dictionaryConvertAnnotation = field.getDeclaredAnnotation(DictionaryConvertAnnotation.class);
			if (dictionaryConvertAnnotation == null) {
				objectMapper.writeValue(generator, dictionaryValue);
				return;
			}

			// 如果是引用其他字段则值从其他字段取
			if (StringUtils.isNotEmpty(dictionaryConvertAnnotation.codeField())) {
				dictionaryValue = ReflectUtil.getFieldValue(generator.getCurrentValue(), dictionaryConvertAnnotation.codeField());
			}

			// 获取字典type
			String dictionaryTypeId = dictionaryConvertAnnotation.typeId();
			transType = dictionaryConvertAnnotation.transType();
			if (dictionaryTypeId == null || StringUtils.isEmpty(dictionaryTypeId)) {
				if (transType == 2) {
					objectMapper.writeValue(generator, dictionaryText.toString());
				} else {
					objectMapper.writeValue(generator, BaseEnum.builder().code(dictionaryValue).name(dictionaryText.toString()).build());
				}
				return;
			}
			// 通过字典key转换成获取字典value
			String val = dictionaryValue == null ? "" : dictionaryValue.toString();

			if (!StringUtils.isEmpty(val)) {
				// 根据TypeId和Code解析出Name
				ResultModel model = gpDictionaryUntBll.getListByTypeId(dictionaryTypeId);
				ArrayList<GpDictionary> dictionaryList = CastObjectUtil.cast(model.getData());
				GpDictionary gpDictionary = dictionaryList.stream().filter(dictionary -> dictionary.getCode().equals(Byte.parseByte(val))).findFirst().orElse(null);
				dictionaryText = dictionaryText.append(gpDictionary.getText());
			}
			if (transType == 2) {
				objectMapper.writeValue(generator, dictionaryText);
			} else {
				objectMapper.writeValue(generator, BaseEnum.builder().code(dictionaryValue).name(dictionaryText.toString()).build());
			}
		} catch (NoSuchFieldException e) {
			if (transType == 2) {
				objectMapper.writeValue(generator, dictionaryText);
			} else {
				objectMapper.writeValue(generator, BaseEnum.builder().code(dictionaryValue).name(dictionaryText.toString()).build());
			}
		}
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class BaseEnum {

		private Object code;

		private String name;
	}

}
