package com.zee.set.enumer;

/**
 * 
 * @author lxl
 * @createDate 2018年7月25日 下午4:49:10
 * @updateDate 2018年7月25日 下午4:49:10
 * @description 数据控制枚举
 */
public enum DataControlEnum {
	ALL((byte) 1, "全部数据"),
	OWN((byte) 2, "用户个人数据"),

	;

	private String text;

	private Byte code;

	private DataControlEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DataControlEnum c : DataControlEnum.values()) {
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
	