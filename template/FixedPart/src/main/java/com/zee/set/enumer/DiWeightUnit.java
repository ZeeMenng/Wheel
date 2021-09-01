package com.zee.set.enumer;

/**
 * @author lxy
 * @createDate 2018年6月25日 下午9:52:57
 * @updateDate 2018年6月25日 下午9:52:57
 * @description 重量枚举
 */
public enum DiWeightUnit {

	QIANKE((byte) 1, "千克"),

	DUN((byte) 2, "吨"),

	GONGJIN((byte) 3, "公斤"),

	WANDUN((byte) 4, "万吨"),
	
	
	;

	private String text;

	private Byte code;

	private DiWeightUnit(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiWeightUnit c : DiWeightUnit.values()) {
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
	