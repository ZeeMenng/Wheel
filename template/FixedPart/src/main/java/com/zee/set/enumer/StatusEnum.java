package com.zee.set.enumer;

public enum StatusEnum {
	
	//用于溯源设备
	ZHENGCHANG((byte)0, "正常"),
	YICHANG((byte)1, "异常"),
	
	//用于舆情
	KAIQI((byte)2, "启用"),
	GUANBI((byte)3, "关闭"),
	
	XINJIAN((byte)4, "新建"),
	;
	private String text;
	private Byte code;
	private StatusEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (StatusEnum c : StatusEnum.values()) {
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
