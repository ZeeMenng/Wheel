package com.zee.set.serializer;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Zee
 * @createDate 2017年4月14日 上午10:53:28
 * @updateDate 2017年4月14日 上午10:53:28
 * @description @JsonSerialize(using = JacksonDoubleSerialize.class)
 *              注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，此类表示序列化一个double时在其后面限制两位小数点（
 *              四舍五入）。
 */

public class JacksonDoubleSerialize extends JsonSerializer<Double> {

	private DecimalFormat df = new DecimalFormat("##.00");

	@Override
	public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString(df.format(value));
	}
}