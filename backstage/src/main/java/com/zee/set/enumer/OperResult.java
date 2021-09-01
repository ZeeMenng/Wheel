package com.zee.set.enumer;

/**
 * @author Zee
 * @createDate 2017年4月18日 下午4:13:34
 * @updateDate 2017年4月18日 下午4:13:34
 * @description 日志记录中的操作结果。第一位9固定，第二位9代表增，8代表删，7代表改，6代表查，0代表通用自定义方法。
 *              第三四位代表增删改查的子项，倒序，后面两位表示操作结果。
 *              00表示操作成功，01表示操作出错或出现异常。出错和异常是两码事，但暂不进行更细致的区分。
 */
public enum OperResult {

	TOKEN_EXPIRED(950000, "AccessToken过期，请重新获取受权。"),
	//
	CUSTOM_S(900000, "自定义方法操作成功！"),
	//
	ADD_S(999900, "添加记录成功！"),
	//
	ADDLIST_S(999800, "批量添加成功！"), ADDLISTWIDTHDFFORADD_S(999600, "批量新增或修改记录为不同值成功！"),
	//
	DELETE_S(989900, "删除记录成功！"),
	//
	DELETEBYUNIQUE_S(989500, "根据唯一字段删除记录成功！"),
	//
	DELETELIST_S(989800, "批量删除成功！"),
	//
	DELETEBYBUSINESSID_S(989700, "根据业务主键删除记录成功！"),
	//
	DELETEBYFOREIGNKEY_S(989600, "根据外键删除记录成功！"),
	//
	UPDATE_S(979900, "修改记录成功！"), UPDATELIST_S(979800, "批量修改记录成功！"), UPDATELISTWIDTHDFF_S(979700, "批量修改记录为不同值成功！"), UPDATELISTWIDTHDFFORADD_S(979600, "批量修改或新增记录为不同值成功！"),
	//
	GETMODEL_S(969900, "单条查询成功！"),
	//
	GETMODELBYUNIQUE_S(969500, "根据唯一字段（非主键）单条查询成功！"),
	//
	ISUNIQUE_S(969400, "根据唯一字段（非主键）判断值是否存在，查询成功！"),
	//
	GETLIST_S(969800, "模糊查询成功！"),
	//
	GETLISTBYSQL_S(969700, "自定义查询成功！"),
	//
	GETLISTBYFOREIGNKEY_S(969600, "根据外键查询成功！"),

	//
	CUSTOM_F(900001, "自定义方法操作失败！"),
	//
	ADD_F(999901, "添加记录出错！"),
	//
	ADDLIST_F(999801, "批量添加出错！"), ADDLISTWIDTHDFFORADD_F(999601, "批量新增或修改记录为不同值失败！"),
	//
	DELETE_F(989901, "删除记录出错！"),
	//
	DELETEBYUNIQUE_F(989501, "根据唯一字段删除记录成功！"),
	//
	DELETELIST_F(989801, "批量删除出错！"),
	//
	DELETEBYBUSINESSID_F(989701, "根据业务主键删除记录失败！"),
	//
	DELETEBYFOREIGNKEY_F(989601, "根据外键删除记录删除失败！"),
	//
	UPDATE_F(979901, "修改记录出错！"), UPDATELIST_F(979801, "批量修改记录出错！"), UPDATELISTWIDTHDFF_F(979701, "批量修改记录为不同值出错！"), UPDATELISTWIDTHDFFORADD_F(97961, "批量修改或新增记录为不同值出错！"),
	//
	GETMODEL_F(969901, "单条查询出错！"),
	//
	GETMODELBYUNIQUE_F(969501, "根据唯一字段（非主主键）单条查询出错！"),
	//
	ISUNIQUE_F(969401, "根据唯一字段（非主键）判断值是否存在，查询失败！"),
	//
	GETLIST_F(969801, "模糊查询出错！"),
	//
	GETLISTBYSQL_F(969701, "自定义查询出错！"),
	//
	GETLISTBYFOREIGNKEY_F(969601, "根据外键查询失败！");

	private String text;
	private Integer code;

	private OperResult(Integer code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Integer code) {
		for (OperResult c : OperResult.values()) {
			if (c.getCode() == code) {
				return c.text;
			}
		}
		return null;

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
