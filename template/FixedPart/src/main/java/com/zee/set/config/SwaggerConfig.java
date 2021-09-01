package com.zee.set.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;
import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:17:54
 * @updateDate 2021年2月7日 下午3:17:54
 * @description Swagger相关的配置，升级为springfox swagger 3.0后配置出现问题
 */

// @Configuration
// @EnableOpenApi
public class SwaggerConfig {

	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.zee";

	@Bean
	public Docket gpApi() {
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.OAS_30).groupName("GpApi").apiInfo(gpApiInfo()).select().paths(PathSelectors.any())//
				.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))//
				.build()//
				.enable(true);
	}

	@Bean
	public Docket daApi() {
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.OAS_30).groupName("DaApiInfo").apiInfo(daApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.pathMapping("/");
	}

	@Bean
	public Docket mfApi() {
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.OAS_30).groupName("MfApiInfo").apiInfo(mfApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.pathMapping("/");
	}

	@Bean
	public Docket wpApi() {
		Predicate<String> or = regex("/extend/swagger/gp/.*");
		return new Docket(DocumentationType.OAS_30).groupName("wpApiInfo").apiInfo(wpApiInfo()).select().paths(or).build()//
				.genericModelSubstitutes(DeferredResult.class)//
				.forCodeGeneration(false)//
				.pathMapping("/");
	}

	private ApiInfo gpApiInfo() {
		return new ApiInfo("通用功能（gp）", // 标题
				"用户、角色、权限、日志、消息、附件等通用表的API文档。", // 描述
				"General Purpose", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo daApiInfo() {
		return new ApiInfo("数据采集（da）", // 标题
				"数据采集系统相关表的API文档", // 描述
				"Data Acquisition", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo mfApiInfo() {
		return new ApiInfo("监测预警（mf）", // 标题
				"数据采集系统相关表的API文档", // 描述
				"Monitoring and Forecasting", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	private ApiInfo wpApiInfo() {
		return new ApiInfo("门户网站（wp）", // 标题
				"门户网站资讯版（PI）和数据版（PE）相关表的API文档", // 描述
				"Web Portals", // 版本
				"", new Contact("Zee", "", ""), // 作者
				"", // 许可
				"", // 许可地址
				Collections.emptyList());
	}

	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("api_key", "api_key", "header");
	}

}
