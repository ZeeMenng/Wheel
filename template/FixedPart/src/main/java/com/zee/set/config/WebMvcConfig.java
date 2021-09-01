package com.zee.set.config;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.set.interceptor.InterfaceRequestInterceptor;
import com.zee.set.serializer.JacksonNullSerializer;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2021年2月5日 下午3:43:05
 * @updateDate 2021年2月5日 下午3:43:05
 * 
 * @description 升级SpringBoot后，时间转换配置不生效，重写addInterceptors方法以添加接口请求拦截器，用于登录校验 和接口权限校验。
 * 
 * @description 升级SpringBoot后，时间转换配置不生效，重写extendMessageConverters方法以转换时间。
 * 
 * @description Spring自动序列化返回值为JSON字符串，重写configureMessageConverters，如果为属性NULL，则对应成空字符串''，而非之前的'null'字符串。但此配置开启后swagger无法正常使用，暂没有双全的方法。
 * 
 * @description Spring自动序列化返回值为JSON字符串，重写configureMessageConverters，以初始化JacksonDictionarySerializer中的Bean
 *              
 * 
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${spring.jackson.date-format}")
	private String dateFormatPattern;

	@Value("${spring.jackson.time-zone}")
	private String timeZone;

	@Resource(name = "interfaceRequestInterceptor")
	InterfaceRequestInterceptor interfaceRequestInterceptor;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/WebContent/**").addResourceLocations("forward:/WEB-INF/index.html");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interfaceRequestInterceptor).addPathPatterns(CustomSymbolic.API_ROOT_URL).excludePathPatterns(CustomSymbolic.AUTHENTICATION_URL);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 自定义转换
		converters.add(0, customValueConverter());
	}

	@Bean
	MappingJackson2HttpMessageConverter customValueConverter() {

		// 采用这种方式初始化化MappingJackson2HttpMessageConverter，是为了注入JacksonDictionarySerializer中的属性值
		Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
		SpringHandlerInstantiator handlerInstantiator = new SpringHandlerInstantiator(applicationContext.getAutowireCapableBeanFactory());
		objectMapperBuilder.handlerInstantiator(handlerInstantiator);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapperBuilder.build());

		// 生成JSON时,将所有Long转换成String
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

		ObjectMapper objectMapper = converter.getObjectMapper();
		objectMapper.registerModule(simpleModule);

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 时间格式化，引用spring boot yml 里的格式化配置和时区配置
		objectMapper.setDateFormat(new SimpleDateFormat(dateFormatPattern));
		objectMapper.setTimeZone(TimeZone.getTimeZone(timeZone));

		// NULL的序列化为""，此方法放开会导致有些Controll的返回不再是JSON对象，而是外层包括了引号的JSON字符串，比如判断是否唯一用户名的方法
		// 不直接用objectMapper.getSerializerProvider().setNullValueSerializer(new
		// JacksonNullSerializer());是为了单独处理字典解析的注解
		objectMapper.registerModule(new SimpleModule() {
			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.addBeanSerializerModifier(new CustomBeanSerializerModifier());
			}
		});

		// 设置格式化内容
		converter.setObjectMapper(objectMapper);
		return converter;

	}

	public class CustomBeanSerializerModifier extends BeanSerializerModifier {

		@Override
		public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

			for (BeanPropertyWriter beanProperty : beanProperties) {
				DictionaryConvertAnnotation annotation = beanProperty.getAnnotation(DictionaryConvertAnnotation.class);
				// 对于字典字段此处不做处理，此处代码可能未生效
				if (annotation == null)
					beanProperty.assignNullSerializer(new JacksonNullSerializer());
			}

			return beanProperties;
		}
	}

}
