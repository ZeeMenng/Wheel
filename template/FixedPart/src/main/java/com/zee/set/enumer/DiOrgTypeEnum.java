package com.zee.set.enumer;

/**
 * 
 * @author lxl
 * @createDate 2018年7月25日 下午4:49:10
 * @updateDate 2018年7月25日 下午4:49:10
 * @description 组织机构类型枚举
 */
public enum DiOrgTypeEnum {
	
	XIANZHENGFU((byte) 1, "县政府"),
	SHENGSHISHUANGGUANDANWEI((byte) 2, "省市双管单位"),
	ZHENZHENGFU((byte) 3, "镇政府"),
	HEZUOSHE((byte) 4, "合作社"),
	SHENGCHANQIYE((byte) 5, "生产企业"),
	JIAGONGQIYE((byte) 6, "加工企业"),
	XIAOSHOUQIYE((byte) 7, "销售企业"),
	WULIUQIYE((byte) 8, "物流企业"),
	QITA((byte) 9, "其他"),
	;

	private String text;

	private Byte code;

	private DiOrgTypeEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DiOrgTypeEnum c : DiOrgTypeEnum.values()) {
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
	