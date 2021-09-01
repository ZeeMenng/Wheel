package com.zee.set.enumer;

/**
 * @author Zee
 * @createDate 2018年6月20日 下午8:15:44
 * @updateDate 2018年6月20日 下午8:15:44
 * @description 退出方式字典项
 */
public enum LogoutType {

	OTHERS((byte) 0, "其它退出"),

	TOKEN_EXPIRE((byte) 1, "Token过期"),

	INITIATIVE((byte) 2, "主动退出"),

	PASSIVE((byte) 3, "主动退出");

	private String text;

	private Byte code;

	private LogoutType(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (LogoutType c : LogoutType.values()) {
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
