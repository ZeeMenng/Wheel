package com.zee.set.enumer;

/**
 * 
 * @author lxl
 * @createDate 2018年7月25日 下午4:49:10
 * @updateDate 2018年7月25日 下午4:49:10
 * @description 产量枚举
 */
public enum DiYieldUnitEnum {
	QIANKE((byte) 1, "千克/亩"), 
	
	KE((byte) 2, "克/亩");

	private String text;

	private Byte code;

	private DiYieldUnitEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiYieldUnitEnum c : DiYieldUnitEnum.values()) {
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
