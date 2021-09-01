package com.zee.set.enumer;

public enum DictionaryEnum {

	AREA_LATITUDE_TYPE_TEXT((byte) 0, "25fe79d29b55bdba65cf229346598886"), // 区域维度
	AUDIT_STATUS_TEXT((byte) 1, "b683e56d72c785da65f76cdba38d06fe"), // 审核状态
	DATA_TIME_TYPE_TEXT((byte) 2, "97afe4eee26aa23a604e502b622a6c0f"), // 时间维度
	SOURCE_CHANNEL_TYPE_TEXT((byte) 3, "a700900a555b71ff47bb8cf7f7bb0956"), // 来源渠道
	MOBOLE_OPERATE_TEXT((byte) 4, "f03c7b3ff277b15adc93c638cba4a43f"), // 智能手机操作熟练程度
	PEASANT_TYPE_TEXT((byte) 5, "a1c4f5ac1ac291938fae4d1e3bc47150"), // 农户类型
	ENTERPRISE_TYPE_CODE((byte) 6, "941b13c7c816cde7254b1bcdb431752c"), // 企业类型
	INDUSTRY_TYPE_CODE((byte) 7, "a8c2c18c8ae83bda514c3ae1fcc77a1a"), // 企业产业类型
	DI_BOOLEAN((byte) 8, "dc1f9015660bcbcee7f1dfc1a5dea1ea"), // boolean型字典
	BASE_TYPE_CODE((byte) 9, "ad02806ca91ed6f7be7c1d523f4c68aa"), // 基地类型
	IDENTIFICATION_TYPE_CODE((byte) 10, "98f54771eac5c1fa52160e7886b581fe"), // 认证类型
	CROP_TYPE_CODE((byte) 11, "f8ba8a76017c654648c239a4a0f37b34"), // 作物种类
	STRAINS_CODE((byte) 12, "48690fc04089cb696dfad2c1780153a7"), // 作物品种
	PRODUCE_SUMMATION_UNIT_CODE((byte) 13, "be9c1c8427ca3cbf7d2cf4a8980c4461"), // 产品产量单位
	IDENTIFICATION_AREA_CODE((byte) 14, "b9a7bb32ad454f89bd6eeb9c4ba0cfb6"), // 面积单位
	INPUT_TYPE_CODE((byte) 15, "4dc6fc902d4fd40ba72cfe62710e5237"), // 投入品类型
	PER_PRICE_UNIT_CODE((byte) 16, "1d5bbf4e32a185a919cd6cc3bf1c72db"), // 单价单位字典
	PER_COST_UNIT_CODE((byte) 17, "5943c4d653093cd90c9590461e69690d"), // 成本单位字典
	WEIGHT_UNIT_CODE((byte) 18, "2507ae7b1899bb66e25f4553e9d1fcd2"), // 重量单位字典
	PRICE_UNIT_CODE((byte) 19, "adb2790cc6001d5122aefb819390860c"), // 价钱单位字典
	DI_PROCESS_TYPE((byte) 20, "fba9b823668de16542f3b4d4a664538f"), // 加工类型
	DI_CHANNEL_OF_DISTRIBUTION_TYPE((byte) 21, "f768e57c0536a6ebe712c68885caa1cd"), // 销售渠道
	DI_MODE_OF_PAYMENT((byte) 22, "a3591b2bc52fc111e595680483c38bdd"), // 支付方式
	DI_AGRICULTURE_DISASTER_TYPE((byte) 23, "e94c59ca245b8cb85038922067beb00d"), // 农业灾害类型字典
	DI_PRICE_TYPE((byte) 24, "477041e2029754cf2c746db6958a7f89"), // 价格类型字典
	DI_PROCESS_BREED((byte) 25, "817d9f61ddf66623ffd2cf55e0f107ea"), // 加工品品种

;
	

	private String text;
	private Byte code;

	private DictionaryEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DictionaryEnum c : DictionaryEnum.values()) {
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
