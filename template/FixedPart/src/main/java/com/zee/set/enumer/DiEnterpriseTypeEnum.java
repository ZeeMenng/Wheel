package com.zee.set.enumer;

/**
 * 
 * @author lxl
 * @createDate 2018年7月25日 下午4:49:10
 * @updateDate 2018年7月25日 下午4:49:10
 * @description 企业类型枚举
 */
public enum DiEnterpriseTypeEnum {
	
	SHENGCHAN((byte) 5, "生产企业"),
	JIAGONG((byte) 6, "加工企业"),
	WULIU((byte) 3, "物流企业"),
	XIAOSHOU((byte) 7, "销售企业"),
	;

	private String text;

	private Byte code;

	private DiEnterpriseTypeEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiEnterpriseTypeEnum c : DiEnterpriseTypeEnum.values()) {
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
	