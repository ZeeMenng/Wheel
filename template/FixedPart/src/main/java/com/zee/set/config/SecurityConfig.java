package com.zee.set.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:18:31
 * @updateDate 2017年4月13日 下午3:18:31
 * @description Spring Security设置用类
 */
@Configuration
@ComponentScan(basePackages = { "com.zee.app.**.gp.**", "com.zee.app.**.pi.**", "com.zee.set.**"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 屏蔽掉Spring Security自身的CSRF防护
		http.csrf().disable();

	}

}