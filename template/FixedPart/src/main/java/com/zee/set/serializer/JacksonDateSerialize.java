package com.zee.set.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2017年5月31日 上午17:18:28
 * @updateDate 2017年5月31日 上午17:18:28
 * @description @JsonSerialize(using = JacksonDateSerialize.class)
 *              注解用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，此类表示序列化一个Date时用我们特定的格式。
 *              但自己目前尝试，这种设置方法在Swagger中并不起作用，不知道正式使用时如何。自己之前使用的日期序列化方式是：
 *              @JsonFormat(pattern = SymbolicConstant.DATETIME_FORMAT)
 *              但此设置也和Swagger有冲突。另外网上介绍，有全局设置的方式，但自己目前还未找到可在当前环境中使用的全局设置办法。
 */

public class JacksonDateSerialize extends JsonSerializer<Date> {


	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat(CustomSymbolic.DATETIME_FORMAT);
		jgen.writeString(sdf.format(value));
	}
}