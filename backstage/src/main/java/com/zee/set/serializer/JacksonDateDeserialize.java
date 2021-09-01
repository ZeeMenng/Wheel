package com.zee.set.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Zee
 * @createDate 2017年4月14日 上午11:04:30
 * @updateDate 2017年4月14日 上午11:04:30
 * @description @JsonDeserialize(using = JacksonDateDeserialize.class)
 *              此注解用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码，类似于@JsonSerialize。
 *              此类表示反序列化日期时属性时固定为此种格式
 */
public class JacksonDateDeserialize extends JsonDeserializer<Date> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		Date date = null;
		try {
			date = sdf.parse(jp.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
