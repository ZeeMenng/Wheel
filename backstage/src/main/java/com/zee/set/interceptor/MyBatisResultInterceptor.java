package com.zee.set.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2020年11月12日 下午2:13:17
 * @updateDate 2020年11月12日 下午2:13:17
 * @description 打印结果拦截器 〈功能详细描述〉 
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class MyBatisResultInterceptor implements Interceptor {

	@SuppressWarnings({})
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = invocation.proceed(); // 执行请求方法，并将所得结果保存到result中
		String str = "";
		if (result instanceof java.util.List || result.getClass().isArray())
			str = JSONArray.fromObject(result).toString();
		else
			str = JSONObject.fromObject(result).toString();
		//System.out.println(str);
		return result;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
	}
}