package com.zee.set.enumer;


public enum RecommendProductType {

	OTHERS((byte)2, "加工品"),

	TOKEN_EXPIRE((byte) 1, "鮮果");



	private String text;

	private Byte code;

	private RecommendProductType(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (RecommendProductType c : RecommendProductType.values()) {
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
