package com.zee.set.enumer;

/**
 * 
 * @author lxl
 * @createDate 2018年7月25日 下午4:49:10
 * @updateDate 2018年7月25日 下午4:49:10
 * @description 面积枚举
 */
public enum DiAreaUnitEnum {
	MU((byte) 1, "亩"),
	WANMU((byte) 2, "万亩"),
	GONGQING((byte) 3, "公顷"),

	;

	private String text;

	private Byte code;

	private DiAreaUnitEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiAreaUnitEnum c : DiAreaUnitEnum.values()) {
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
	