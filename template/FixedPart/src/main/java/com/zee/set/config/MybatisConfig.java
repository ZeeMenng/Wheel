package com.zee.set.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:17:27
 * @updateDate 2017年4月13日 下午3:17:27
 * @description MyBatis相关的配置，目前使用的数据源是DataSourceConfig类中配置的druidDataSource
 */
@Configuration
@AutoConfigureAfter({ DataSourceConfig.class })
@EnableTransactionManagement
@MapperScan(basePackages = "com.zee.dao")
public class MybatisConfig implements TransactionManagementConfigurer {

	@Resource(name = "druidDataSource")
	private DataSource dataSource;

	@Value("${mybatis.configLocation}")
	private String configLocation;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
			Interceptor[] interceptor = { offsetLimitInterceptor() };
			sessionFactory.setPlugins(interceptor);
			sqlSessionFactory = sessionFactory.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

	public OffsetLimitInterceptor offsetLimitInterceptor() throws Exception {
		OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
		offsetLimitInterceptor.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
		return offsetLimitInterceptor;
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

}
