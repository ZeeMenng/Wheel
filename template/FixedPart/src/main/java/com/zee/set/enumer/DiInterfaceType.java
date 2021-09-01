package com.zee.set.enumer;

/**
 * @author Zee
 * @createDate 2020年8月26日 下午2:10:04
 * @updateDate 2020年8月26日 下午2:10:04
 * @description 接口类型
 */
public enum DiInterfaceType {
	//
	GET((byte)1, "GET方式"),
	// 新增操作
	POST((byte)2, "POST方式"),
	//
	POSTORGET((byte)0, "POST||GET方式");
	
	private String text;
	private Byte code;

	private DiInterfaceType(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiInterfaceType c : DiInterfaceType.values()) {
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
