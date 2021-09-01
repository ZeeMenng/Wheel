package com.zee.set.enumer;

/**
 * @author Zee
 * @createDate 2017年4月18日 下午2:10:04
 * @updateDate 2017年4月18日 下午2:10:04
 * @description 日志记录中的操作类型，目前定义了9种操作类型。0代表其它。
 */
public enum OperType {
	//
	CUSTOM((byte)0, "自定义方法操作"),
	// 新增操作
	ADD((byte)10, "添加记录"), ADDLIST((byte)11, "批量添加"),ADDLISTWIDTHDFFORADD((byte)12, "批量新增或修改为记录不同值"),
	// 删除操作
	DELETE((byte)20, "删除记录"),DELETEBYUNIQUE((byte)23, "根据唯一字段删除记录"), DELETELIST((byte)21, "批量删除"),DELETEBYFOREIGNKEY((byte)22, "根据外键字段删除记录"),
	// 修改操作
	UPDATE((byte)30, "修改记录"),UPDATELIST((byte)31, "批量修改"),UPDATELISTWIDTHDFF((byte)32, "批量修改为记录不同值"),UPDATELISTWIDTHDFFORADD((byte)33, "批量修改或新增为记录不同值"),
	// 查询操作
	GETMODEL((byte)40, "单条查询"), GETMODELBYUNIQUE((byte)44, "根据唯一字段（非主键）单条查询"),ISUNIQUE((byte)45, "根据唯一字段（非主键）判断值是否存在"),GETLIST((byte)41, "模糊查询"), GETLISTBYSQL((byte)42, "自定义查询"), GETLISTBYFOREIGNKEY((byte)43, "根据外键查询");

	private String text;
	private Byte code;

	private OperType(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (OperType c : OperType.values()) {
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

	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}

}
