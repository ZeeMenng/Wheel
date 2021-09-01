package com.zee.set.enumer;

public enum DomainEnum {

	WP("032769fd7e376c04fb13c66419a72598", "通用门户网站"),

	GP("09335bd69ab9826df8b69589a2568055", "玉轮后台管理"),

	DA("61016ec489463334d638ceb433e8e1d1", "数据采集体系"),

	MF("615d66b4781394e5f40867013f87f669", "监测预警平台"),

	IOSAPP("9e47d87dd1706cf7710e6fcfe0e6e0a7", "手机APP苹果"),

	AndroidAPP("d768d86480c54990a60a80d112c4b6fe", "手机APP安卓");

	private String id;
	private String name;

	private DomainEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getText(String id) {
		for (DomainEnum c : DomainEnum.values()) {
			if (c.getId() == id) {
				return c.name;
			}
		}
		return null;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
