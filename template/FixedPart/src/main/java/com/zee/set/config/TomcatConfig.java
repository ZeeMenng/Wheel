package com.zee.set.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zee
 * @createDate 2021年2月4日 下午6:14:00
 * @updateDate 2021年2月4日 下午6:14:00
 * @description Tomcat相关配置，升级SpringBoot版本号，前端Get方法传入的特殊字符无法解析
 */
@Configuration
public class TomcatConfig {

	@Bean
	public TomcatServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addConnectorCustomizers((Connector connector) -> {
			connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
			connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
		});
		return factory;
	}
}
