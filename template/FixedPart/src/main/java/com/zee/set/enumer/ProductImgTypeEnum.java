package com.zee.set.enumer;

public enum ProductImgTypeEnum {
	
	CHANPIN((byte)0, "产品图片"),
	ZHENGSHU((byte)1, "证书图片"),
	;
	
	private String text;
	private Byte code;
	private ProductImgTypeEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (ProductImgTypeEnum c : ProductImgTypeEnum.values()) {
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
