package com.zee.set.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Zee
 * @createDate 2017年4月14日 上午11:06:42
 * @updateDate 2017年4月14日 上午11:06:42
 * @description com.zee.set.config.JacksonConfig使用，开启此配置，
 *              Spring自动序列化返回值为JSON字符串，如果为属性NULL，则对应成空字符串''，而非之前的'null' 字符串。
 *              但此配置开启后swagger无法正常使用，暂没有双全的方法。
 */
public class JacksonNullSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object dictionaryValue, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {

		generator.writeString("");

	}
}