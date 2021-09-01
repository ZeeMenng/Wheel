package com.zee.set.enumer;

public enum RoleEnum {

	SUPERADMIN("83c11795be9e4383a4d1cc3e5b861c58", "超级管理员"),

	EXPERT("207a4c5c068647449314f71a7b1d8250", "专家");

	private String id;
	private String name;

	private RoleEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static String getText(String id) {
		for (RoleEnum c : RoleEnum.values()) {
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
