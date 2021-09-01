package com.zee.utl;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zee
 * @createTime 2018年11月27日 下午3:56:58
 * @updateTime 2018年11月27日 下午3:56:58
 * @description 清理垃圾数据和附件。
 *              
 *              
 */
@Component("cleanJunckData")
public class CleanJunckData implements DatabaseMetaDataCallback {
	private static final Logger log = LoggerFactory.getLogger(CleanJunckData.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "druidDataSource")
	private DataSource dataSource;

	String[] parentTableArray = new String[] { "da_common_field", "gp_resource" };

	public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
		ResultSet rs = dbmd.getTables(dbmd.getUserName(), null, null, new String[] { "TABLE" });
		ArrayList l = new ArrayList();
		while (rs.next()) {
			l.add(rs.getString(3));
		}
		return l;
	}

	// 清除不可孤立存在的父表中的数据
	public void cleanParentTable() {
		int a = 0;
		for (int i = 0; i < parentTableArray.length; i++) {

			String selectCountSql = "select count(1) from %s";
			selectCountSql = String.format(selectCountSql, parentTableArray[i]);
			int count = jdbcTemplate.queryForObject(selectCountSql, Integer.class);

			for (int m = 0; m < count / 100 + 1; m++) {
				String selectIdListSql = "select id from %s limit %d,%d ";
				selectIdListSql = String.format(selectIdListSql, parentTableArray[i], m * 100, 100);

				List<Map<String, Object>> idList = jdbcTemplate.queryForList(selectIdListSql);
				for (int j = 0; j < idList.size(); j++) {
					Map<String, Object> idMap = idList.get(j);
					jdbcTemplate.update("{call clean_parent_table(?,?)}", parentTableArray[i], idMap.get("id"));
					System.out.printf("%d=================" + idMap.get("id"), a++);
					System.out.println("");
				}
			}
		}

	}

	// 清除中间表中的数据
	public void cleanIntermediateTable() {
		try {
			DatabaseMetaData md = dataSource.getConnection().getMetaData();
			ResultSet tableResultSet = md.getTables(null, null, "__r\\_%", new String[] { "TABLE" });
			while (tableResultSet.next()) {
				String tableName = tableResultSet.getString("TABLE_NAME"); // 表名
				System.out.println(tableName);

				ResultSet columnResultSet = md.getColumns(null, null, tableName, "%\\_id");
				boolean isUseful = true;
				while (columnResultSet.next()) {
					String columnName = columnResultSet.getString("COLUMN_NAME"); // 列名
					int dataType = columnResultSet.getInt("DATA_TYPE"); // 对应的java.sql.Types的SQL类型(列类型ID)
					if (columnName.endsWith("_id") && dataType == 1) {
						String parentTableName = tableName.substring(0, 2) + "_" + columnName.substring(0, columnName.indexOf("_id"));

						ResultSet parentTableResultSet = md.getTables(null, null, parentTableName, new String[] { "TABLE" });
						if (parentTableResultSet.wasNull())
							continue;
						StringBuffer sqlBuffer = new StringBuffer("delet from ");
						sqlBuffer.append(tableName);
						sqlBuffer.append(" where ").append(columnName);
						sqlBuffer.append(" not in(select id from ").append(parentTableName).append(")");
						String sql = sqlBuffer.toString();
						System.out.println(sql);
						// runScript(new StringReader(sql), false);
					}
					// 如果不包含isUseful则为false 然后break;
				}

			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	public void runScript(Reader reader, boolean sendFullScript) {
		if (reader == null) {
			log.error("执行脚本文件读入文件为空");
		}
		Connection conn = null;
		ScriptRunner runner = null;
		try {
			conn = dataSource.getConnection();
			runner = new ScriptRunner(conn);
			runner.setSendFullScript(sendFullScript);
			runner.setLogWriter(null);// 设置是否输出日志
			runner.setAutoCommit(true);
			runner.setStopOnError(false);
			runner.setRemoveCRs(true);
			runner.runScript(reader);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("执行sql脚本出现异常");
		} finally {
			if (runner != null) {
				runner.closeConnection();
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
