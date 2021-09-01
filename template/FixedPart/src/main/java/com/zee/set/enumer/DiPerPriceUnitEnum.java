package com.zee.set.enumer;

/**
 * @author lxy
 * @createDate 2018年6月25日 下午9:53:41
 * @updateDate 2018年6月25日 下午9:53:41
 * @description 单价枚举
 */
public enum DiPerPriceUnitEnum {

	YUAN((byte) 1, "元/公斤"),

	WANYUAN((byte) 2, "万元/公斤"),

	YIYUAN((byte) 3, "亿元/公斤"),

	MEIYUAN((byte) 4, "美元/公斤"),
	
	WANMEIYUAN((byte) 5, "万美元/公斤"),
	
	BAIWANMEIYUAN((byte) 6, "百万美元/公斤"),
	
	YUANJIN((byte) 7, "元/斤"),
	
	;

	private String text;

	private Byte code;

	private DiPerPriceUnitEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiPerPriceUnitEnum c : DiPerPriceUnitEnum.values()) {
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
