package com.zee.set.interceptor;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;

import com.zee.bll.extend.unity.gp.GprConfigUserUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.exception.GlobalException;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;

/**
 * @author Zee
 * @createDate 2020年11月12日 下午2:09:05
 * @updateDate 2020年11月12日 下午2:09:05
 * @description MyBatis拦截器打印不带问号的完整sql语句
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }), @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MyBatisSQLInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws IllegalAccessException, InvocationTargetException {

		// 获取xml中的一个select/update/insert/delete节点，是一条SQL语句
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		// 获取参数，if语句成立，表示sql语句有参数，参数格式是map形式
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
			// System.out.println("parameter = " + parameter);
		}
		// 获取到执行SQL语句的方法
		String dalMethod = mappedStatement.getId();
		// System.out.println("sqlId = " + sqlId);

		// 跳过没必要的SQL
		ArrayList<String> skipMethod = new ArrayList<String>();
		skipMethod.add("com.zee.dao.unity.gp.IGpTokenUntDal.getModel");
		skipMethod.add("com.zee.dao.split.gp.IGpInterfaceSplDal.isPermitted");
		skipMethod.add("com.zee.dao.split.gp.IGpUserSplDal.getModelByUserName");
		skipMethod.add("com.zee.dao.split.gp.IGprDomainUserSplDal.isPermitted");
		skipMethod.add("com.zee.dao.split.gp.IGpInterfaceSplDal.getModelByUrl");
		skipMethod.add("com.zee.dao.unity.gp.IGpDomainUntDal.getModel");
		skipMethod.add("com.zee.dao.unity.gp.IGprMessageUserUntDal.getListBySQL");
		skipMethod.add("com.zee.dao.unity.gp.IGpModuleUntDal.getListBySQL");
		skipMethod.add("com.zee.dao.unity.gp.IGpOperLogUntDal.add");

		BoundSql boundSql = mappedStatement.getBoundSql(parameter); // BoundSql就是封装myBatis最终产生的sql类
		if (!skipMethod.contains(dalMethod)) {
			Configuration configuration = mappedStatement.getConfiguration(); // 获取节点的配置
			String sql = getSql(configuration, boundSql, dalMethod); // 获取到最终的sql语句
			System.out.println("sql = " + sql);
		}

		// 执行完上面的任务后，不改变原有的sql执行过程
		return invocation.proceed();
	}

	// 封装了一下sql语句，使得结果返回完整xml路径下的sql语句节点id + sql语句
	public String getSql(Configuration configuration, BoundSql boundSql, String dalMethod) {
		String sql = showSql(configuration, boundSql);
		//DataAuthorityControl(sql);
		StringBuilder str = new StringBuilder(100);
		str.append(dalMethod);
		str.append(":");
		str.append(sql);
		return str.toString();
	}

	// 进行？的替换
	public String showSql(Configuration configuration, BoundSql boundSql) {
		// 获取参数
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		// sql语句中多个空格都用一个空格代替
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (CollectionUtils.isNotEmpty(parameterMappings) && parameterObject != null) {
			// 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			// 如果根据parameterObject.getClass(）可以找到对应的类型，则替换
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

			} else {
				// MetaObject主要是封装了originalObject对象，提供了get和set的方法用于获取和设置originalObject的属性值,主要支持对JavaBean、Collection、Map三种类型对象的操作
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						// 该分支是动态sql
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else {
						// 打印出缺失，提醒该参数缺失并防止错位
						sql = sql.replaceFirst("\\?", "缺失");
					}
				}
			}
		}
		return sql;
	}

	// 如果参数是String，则添加单引号， 如果是日期，则转换为时间格式器并加单引号； 对参数是null和不是null的情况作了处理
	private String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	/**
	 * @param sql
	 * 
	 * 数据级权限的控制，可以在这里添加
	 * 如果解析出的SQL中操作的字段，不在当前用户的权限中，抛出没有权限的异常，或者更改当前的SQL。
	 * 如果是写操作，刚去掉这个字段，如果是读操作可以将这个字段的返回变成***，或者部分打码，等等类似操作。
	 * 
	 */
	public String DataAuthorityControl(String sql) {
		try {
			CCJSqlParserManager pm = new CCJSqlParserManager();
			List<String> tableList = new ArrayList<String>();
			Statement statement = pm.parse(new StringReader(sql));

			// 如果是超级用户或超级角色，禁止修改删除
			if (statement instanceof Update || statement instanceof Delete) {
				/*
				 * if (sql.contains("7ddd711beab34cf9844037ad7b919ac1")) { throw
				 * new GlobalException("超级用户不可修改"); } if
				 * (sql.contains("83c11795be9e4383a4d1cc3e5b861c58")) { throw
				 * new GlobalException("超级角色不可修改"); }
				 * 
				 * ResultModel resultModel = new ResultModel(); List<Map<String,
				 * Object>> configList =
				 * CastObjectUtil.cast(resultModel.getData()); for (Map<String,
				 * Object> configMap : configList) { String code =
				 * CastObjectUtil.cast(configMap.get("code")); if
				 * (code.equals("superUser")) { String superUser =
				 * CastObjectUtil.cast(configMap.get("configValue")); if
				 * (sql.contains(superUser)) { throw new
				 * GlobalException("超级用户不可修改"); } } if
				 * (code.equals("superRole")) { String superRole =
				 * CastObjectUtil.cast(configMap.get("configValue")); if
				 * (sql.contains(superRole)) { throw new
				 * GlobalException("超级用户不可修改"); } } }
				 */
			}

			if (statement instanceof Select)

			{
				Select selectStatement = (Select) statement;
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				tableList = tablesNamesFinder.getTableList(selectStatement);
			}
			if (statement instanceof Insert) {
				Insert selectStatement = (Insert) statement;
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				tableList = tablesNamesFinder.getTableList(selectStatement);
			}
			if (statement instanceof Update) {
				Update selectStatement = (Update) statement;
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				tableList = tablesNamesFinder.getTableList(selectStatement);
			}
			if (statement instanceof Delete) {
				Delete selectStatement = (Delete) statement;
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				tableList = tablesNamesFinder.getTableList(selectStatement);
			}

		} catch (JSQLParserException e) {
			ResultModel result = new ResultModel();
			result.setOriginException(e);
			result.setResultMessage(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		}
		return sql;
	}

	/**
	 * 
	 * 直接注入不成功，用Bean注入
	 * @return
	 */
	@Bean
	public GprConfigUserUntBll getGprConfigUserUntBll() {
		return new GprConfigUserUntBll();
	}

	public List<String> getColumns(String singleSql) throws Exception {
		if (singleSql == null) {
			throw new Exception("params is null!");
		}
		CCJSqlParserManager ccjSqlParserManager = new CCJSqlParserManager();
		Statement statement;
		List<String> columns = new ArrayList<String>();
		try {
			statement = ccjSqlParserManager.parse(new StringReader(singleSql));
			if (statement instanceof Select) {
				Select selectStatement = (Select) statement;
				SelectBody selectBody = selectStatement.getSelectBody();
				List<SelectItem> selectItems = ((PlainSelect) selectBody).getSelectItems();
				if (selectItems != null) {
					for (SelectItem item : selectItems) {
						if (item instanceof AllColumns) {
							String column = item.toString();
							columns.add(column);
						}
						if (item instanceof AllTableColumns) {
							columns.add(item.toString());
						}
						if (item instanceof SelectExpressionItem) {
							Alias alias = ((SelectExpressionItem) item).getAlias();
							Expression expression = ((SelectExpressionItem) item).getExpression();
							if (alias != null) {
								String column = alias.getName();
								columns.add(column);
							} else if (expression != null) {
								columns.add(expression.toString());
							}
						}
					}
				}
			}
		} catch (JSQLParserException e) {
			throw new JSQLParserException(e.getMessage());
		}
		return columns;
	}

}
