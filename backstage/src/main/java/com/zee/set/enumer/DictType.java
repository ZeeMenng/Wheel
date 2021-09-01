package com.zee.set.enumer;

public enum DictType {
	
	MEDIATYPE_0((byte)0, "其他"),
	MEDIATYPE_1((byte)1, "新闻"),
	MEDIATYPE_3((byte)3, "论坛"),
	MEDIATYPE_4((byte)4, "微博"),
	MEDIATYPE_5((byte)5, "贴吧"),
	MEDIATYPE_6((byte)6, "博客"),
	MEDIATYPE_10((byte)10, "微信公众号"),
	MEDIATYPE_11((byte)11, "视频"),
	
	SENTIMENTTYPE_0((byte)0, "其他"),
	SENTIMENTTYPE_1((byte)1, "负面"),
	SENTIMENTTYPE_2((byte)2, "中性"),
	SENTIMENTTYPE_3((byte)3, "正面"),

	DATASOURCE_1((byte)1, "数据中心"),
	DATASOURCE_2((byte)2, "手工输入"),
	
	MODULE_LEVEL_FIRST((byte)1, "第一级"),
	MODULE_LEVEL_SECOND((byte)2, "第二级"),
	MODULE_LEVEL_THIRD((byte)3, "第三级"),
	MODULE_LEVEL_FOURTH((byte)4, "第四级"),
	MODULE_LEVEL_FIFTH((byte)5, "第五级"),
	MODULE_LEVEL_SIXTH((byte)6, "第六级"),
	
	;
	private String text;
	private Byte code;

	private DictType(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DictType c : DictType.values()) {
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
