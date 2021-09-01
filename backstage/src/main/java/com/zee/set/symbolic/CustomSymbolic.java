package com.zee.set.symbolic;

/**
 * @author Zee
 * @createDate 2017年4月13日 下午3:18:52
 * @updateDate 2017年4月13日 下午3:18:52
 * @description 常量类
 */
public class CustomSymbolic {

	public static final String INITIAL_PASSWORD = "HuaPing190109";
	// 同一请求中的当前登录用户
	public static final String REQUEST_CURRENT_USER = "CurrentUser";
	// 当前的应用领域
	public static final String REQUEST_CURRENT_DOMAIN = "CurrentDomain";
	// Druid登录用户名
	public static final String DRUID_LOGIN_USERNAME = "Zee";
	// Druid登录密码
	public static final String DRUID_LOGIN_PASSWORD = "18500358676";
	// 密钥
	public static final String JWT_SECRET = "#ZhiMaKaiMen1234@*";
	// JWT签发者
	public static final String JWT_ISSUER = "Jusfoun";
	public static final String JWT_LOGIN_LOG_ID = "loginLogId";
	public static final String JWT_DOMAIN_ID = "domainId";
	public static final String JWT_HEADER_PARAM = "Authorization";
	public static final String JWT_HEADER_PREFIX = "Bearer ";

	public static final String AUTHENTICATION_URL = "/oauth/token";
	public static final String API_ROOT_URL = "/extend/**";

	// 设备异常时间，单位：小时
	public static final String DEVICE_ERROR_TIME = "2";
	// 物联网设备故障提醒标题
	public static final String DEVICE_ERROR_MSG_TITLE = "物联网设备故障提醒";

	// 设备状态
	public static final Integer DEVICE_STATUS_0 = 0;// 正常
	public static final Integer DEVICE_STATUS_1 = 1;// 异常

	// 失效时间，单位秒
	public static final Integer JWT_ACCESS_DEAD_TIME = 30 * 24 * 60 * 60;
	public static final Integer JWT_REFRESH_DEAD_TIME = 300 * 24 * 60 * 60;
	public static final String ERROR_PAGE = "/error";

	public static final String TABLE_ID = "id";
	public static final String TABLE_SERIAL_NO = "serialNo";
	public static final String ADD_TIME = "addTime";
	public static final String UPDATE_TIME = "updateTime";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

	// public static final String SQLQUERY_KEYWORDS_ORDERBY = "orderBy";
	// public static final String SQLQUERY_KEYWORDS_PAGESIZE = "pageSize";
	// public static final String SQLQUERY_KEYWORDS_PAGEINDEX = "pageIndex";
	public static final Integer SQLQUERY_PAGESIZE_DEFAULTVALUE = 15;
	public static final Integer SQLQUERY_PAGEINDEX_DEFAULTVALUE = 1;

	public static final String CONTROLLER_PARAM_JSON = "jsonData";
	public static final String CONTROLLER_ENTITY_JSON = "jsonEntity";
	public static final String AUTOCOMPLETE_KEY = "autoCompleteKey";

	public static final String CONTROLLER_LOG_JSON = "jsonLog";

	public static final String REQUEST_URL_PARAMETER = "requestUrl";
	public static final String LOCAL_JSONDATA_PARAMETERNAME = "submitData";

	public static final String SQLQUERY_KEYWORDS_ORDERBY_FIELD = "orderByField";

	public static final String SQLQUERY_KEYWORDS_ORDERBY_RULE = "orderByRule";

	public static final Integer SQLQUERY_KEYWORDS_PAGESIZE_MAX = 1000000;

	public static final byte DCODE_BOOLEAN_T = 0;
	public static final byte DCODE_BOOLEAN_F = 1;

	public static final String ORGANIZATION_ID_HUAPING_QIYE = "ff343ghgjvnevevr9rjf923jf0923jf9";// 华坪县企业

	public static final String SENTIMENT_SEARCH_URL = "http://apiyuqing.9cfcf.com/article/search";// 舆情搜索请求
	public static final String SENTIMENT_SECRETKEY = "22590592bb813ba955f8a96091e2e9d5";// 舆情密钥

	public static final String CONTRIBUTION_PRICE = "contributionPrice";// 贡献价格数据
	public static final String CONTRIBUTION_YIELD = "contributionYield";// 贡献产量数据
	public static final String CONTRIBUTION_FARM_OPER = "contributionFarmOper";// 贡献农事操作数据

	// 栏目表pi_channel的id
	public static final String CHANNEL_HOTNEW = "6b8e0f738daed0e79952eba26faa82dc";// 热点
	public static final String CHANNEL_BRAND = "061f1ee79c1ae60be0a089227de9af7d";// 品牌
	public static final String CHANNEL_HOME = "3b8f6e987d853f93c899d82c32216c3e";// 首页
	public static final String CHANNEL_TRACE = "dd7bb191cc357bdf6a72c1e5c3f0ced5";// 溯源
	public static final String CHANNEL_DEMAND = "e35a1ae2b0d5b3b2bc4a974126353027";// 供求
	public static final String CHANNEL_BAZAAR = "1ea3ad71d14df858658faaf3df1a679e";// 市场
	public static final String CHANNEL_INTERACTION = "9b879659459acb5df1f987fd47a68eb0";// 互动
	public static final String CHANNEL_SERVE = "f6399d07963955cd9f130a36b83e8409";// 服务
	public static final String CHANNEL_MARKET = "f05ceea60e43ceea731284802e3f12f6";// 行情
	public static final String CHANNEL_PRICE = "1c067d6d8336fec09d9a8aa6e0d9e36a";// 价格
	public static final String CHANNEL_PROCESS = "2b6e1eb98ed2027292f244d49952979a";// 加工
	public static final String CHANNEL_TRAIN = "afad9c378053afeb54db93993eb40c60";// 培训
	public static final String CHANNEL_PESTS = "1da6e5de08b7eabeb97bff0ad7384c7c";// 虫害
	public static final String CHANNEL_WEATHER = "ae64d31600ff9b2398bee723fe928245";// 气象
	public static final String CHANNEL_POLICY = "b8cd038d85c616e5c63d207339c2a07f";// 政策
	public static final String CHANNEL_INSURANCE = "1244c13a8d23b7bec4f7af216ad4c351";// 保险
	public static final String CHANNEL_SUBSIDY = "ae7d9b90fd2895816096fe815ec958b0 ";// 补贴
	public static final String CHANNEL_POVERTY = "a027bd637de73eacf3c11c3afeea38b9";// 扶贫
	public static final String CHANNEL_FINANCING = "4cf9f045f84fb07a62c9196d0e6bd858";// 融资
	public static final String CHANNEL_DOCTRINE = "64e618ae9121cca7fda2e4cb60489041";// 三品一标
	public static final String CHANNEL_RULE = "66e177223f98b51fa3306d82b274fdc0";// 认证规则
	public static final String CHANNEL_PLANT = "90cb31e180b0f1579f59db9a7629fde6";// 种植
	public static final String CHANNEL_RECOMMEND = "6252e4700a6a4f6a832d0b55c4dc5f72";// 推介
	public static final String CHANNEL_WEBSIT = "dde558fd9baf441090be1f840b63d8f1";// 企业微网站
	public static final String CHANNEL_MALL = "7e08ff47f4634674843ed10f71254d45";// 芒果微商城

	// 栏目表pi_channel的relation_id
	public static final String CHANNEL_RELATIONID_HOME = "01";// 首页
	public static final String CHANNEL_RELATIONID_HOTNEW = "0101";// 热点
	public static final String CHANNEL_RELATIONID_SERVE = "0102";// 服务
	public static final String CHANNEL_RELATIONID_POLICY = "010201";// 政策
	public static final String CHANNEL_RELATIONID_INSURANCE = "01020101";// 保险
	public static final String CHANNEL_RELATIONID_SUBSIDY = "01020102";// 补贴
	public static final String CHANNEL_RELATIONID_POVERTY = "01020103";// 扶贫
	public static final String CHANNELV_FINANCING = "01020104";// 融资
	public static final String CHANNEL_RELATIONID_DOCTRINE = "01020105";// 三品一标
	public static final String CHANNEL_RELATIONID_RULE = "01020106";// 认证规则
	public static final String CHANNEL_RELATIONID_PROCESS = "010202";// 加工
	public static final String CHANNEL_RELATIONID_TRAIN = "010203";// 培训
	public static final String CHANNEL_RELATIONID_PESTS = "010204";// 病虫
	public static final String CHANNEL_RELATIONID_WEATHER = "010205";// 气象
	public static final String CHANNEL_RELATIONID_PLANT = "010206";// 种植
	public static final String CHANNEL_RELATIONID_BAZAAR = "0103";// 市场
	public static final String CHANNEL_RELATIONID_PRICE = "010301";// 价格
	public static final String CHANNEL_RELATIONID_MARKET = "010302";// 行情
	public static final String CHANNEL_RELATIONID_DEMAND = "010303";// 供求
	public static final String CHANNEL_RELATIONID_BRAND = "010304";// 品牌
	public static final String CHANNEL_RELATIONID_TRACE = "0104";// 溯源
	public static final String CHANNEL_RELATIONID_INTERACTION = "0105";// 互动
	public static final String CHANNEL_RELATIONID_RECOMMEND = "010305";// 推介
	public static final String CHANNEL_RELATIONID_WEBSIT = "01030501";// 企业微网站
	public static final String CHANNEL_RELATIONID_MALL = "01030502";// 芒果微商城

	// 文章类型表pi_content_type的id
	public static final String CONTENT_TYPE_HEADLINE = "096bbfd1f15685395f0d5b4bd516e640";// 头条
	public static final String CONTENT_TYPE_SAY = "5e0399a53bce096c0eab56e7a3671f18";// 说
	public static final String CONTENT_TYPE_IMAGE = "778a3e846ce7fff470256b308dff02db";// 图
	public static final String CONTENT_TYPE_IMAGETEXT = "7fcf5967c79e58d971eff384f88a42a0";// 图文
	public static final String CONTENT_TYPE_SEE = "954effd987455f4a12c647c09779915f";// 视
	public static final String CONTENT_TYPE_FOCUS = "c9126117dc414c9844248141a6fddaf3";// 焦点
	public static final String CONTENT_TYPE_HEAR = "d9ebd8db0dfce217ad73ca2118fd09dc";// 听
	public static final String CONTENT_TYPE_COMMON = "da22285ea9b05d024aec2ea434bc3fee";// 普通

	public static final String DI_PROCESS_TYPE = "fba9b823668de16542f3b4d4a664538f";// 加工类型字典
	public static final String DI_MODULE = "13b14d3711ab1a259343fcc5900ef44e";// 模块级别字典
	public static final String DI_ENTERPRISE_NATURE = "1605f2c1a18e0c3a33aca6cbad3e48c7";// 企业性质字典
	public static final String DI_ENTERPRISE_CREDENTIALS_TYPE = "19f8c2e388322f7eb766c4af85868ce0";// 企业证书类型字典
	public static final String DI_PER_PRICE_UNIT = "1d5bbf4e32a185a919cd6cc3bf1c72db";// 单价单位字典
	public static final String DI_GENDER = "2275e63ff0101d36c6e93eb37ec44f31";// 性别字典
	public static final String DI_WEIGHT_UNIT = "2507ae7b1899bb66e25f4553e9d1fcd2";// 重量单位字典
	public static final String DI_REGION_LEVEL = "25fe79d29b55bdba65cf229346598886";// 区域等级字典
	public static final String DI_ORGANIZATION = "295c924bc5bf42bdafa12d264c19decd";// 组织机构级别字典
	public static final String DI_OUTPUTVALUE_FORECAST_UNIT = "2c38c6c3e7c0d2bc8344f81b22d6e582";// 产值预测单位类型字典
	public static final String DI_OPER_TYPE = "3da9f48ca09921d6bda10b504962c5ad";// 操作类型字典
	public static final String DI_ENTERPRISE_CREDENTIALS_STATUS_TYPE = "4382915278b9906c8f9fc6cd4fd56e2b";// 企业证书状态类型字典
	public static final String DI_POPULAR_CONTENT_ORIGIN_TYPE = "45b3fc76d3a7b9a84f6c6519b98903c5";// 舆情文章表数据来源类型字典
	public static final String DI_PRICE_TYPE = "477041e2029754cf2c746db6958a7f89";// 价格类型字典
	public static final String DI_ORGANIC_GROWAREA_UNIT_TYPE = "482a9b973c98c0c22f3fe9dd83116146";// 有机种植面积单位类型字典
	public static final String DI_CROP_STRAINS = "48690fc04089cb696dfad2c1780153a7";// 作物品种字典
	public static final String DI_INPUT_TYPE = "4dc6fc902d4fd40ba72cfe62710e5237";// 投入品类型字典
	public static final String DI_PERUNITYIELD_FORECAST_UNIT = "4ff2cf98032150fd79ccc55122db982f";// 单产预测单位类型字典
	public static final String DI_COST_UNIT = "5943c4d653093cd90c9590461e69690d";// 成本单位字典
	public static final String DI_COMMENT = "62b8cffad9da00073a8013783059070b";// 评论字典
	public static final String DI_QUOTA_UNIT_TYPE = "62d6c5af3e06c50063fe0aeb648397c8";// 指标单位类型字典
	public static final String DI_ENTERPRISE_SHAREHOLDER_TYPE = "68f05b564563d2dd20cf66bb4f150259";// 企业股东类型字典
	public static final String DI_CONTENT_STATUS = "702335f52ef313fe3cb6a04d6f77c1de";// content状态字典
	public static final String DI_DIMENSIONS_ENTERPRISE_TYPE = "77cd08e4a78b76bf6a7825fbe2bcab77";// 规模企业类型字典
	public static final String DI_VOCATIONAL_WORK_TYPE = "7b4e5744cfcb134589ad38e61e40f441";// 业务类型字典
	public static final String DI_PROCESS_BREED = "817d9f61ddf66623ffd2cf55e0f107ea";// 加工品品种字典
	public static final String DI_AFTER_AUDITING_LIMIT = "85e643522c0c026eb4f3820ecc23bff4";// 审核后的限制字典
	public static final String DI_SINGLE_WEIGHT_NORMS = "8e0c69f22fe07c4f1a64c0ae9b52c8d4";// 单颗重量规格字典
	public static final String DI_ACQUISITION = "9158d15d5314b7db6b179f3f255dc065";// 采集状态字典
	public static final String DI_ENTERPRISE_TYPE = "941b13c7c816cde7254b1bcdb431752c";// 企业类型字典
	public static final String DI_PRICE_REGION = "95985d6c8b1bdb670c1b2ed83db31b4f";// 价格区间字典
	public static final String DI_TIME_DIMENSION = "97afe4eee26aa23a604e502b622a6c0f";// 时间维度字典
	public static final String DI_IDENTIFICATION_TYPE = "98f54771eac5c1fa52160e7886b581fe";// 认证类型字典
	public static final String DI_PEASANT_TYPE = "a1c4f5ac1ac291938fae4d1e3bc47150";// 农户类型字典
	public static final String DI_MODE_OF_PAYMENT = "a3591b2bc52fc111e595680483c38bdd";// 支付方式类型字典
	public static final String DI_ENTERPRISE_INCIDENCE_RELATION = "a5e85b5fbda76b5393b860841e47b390";// 企业关联关系类型字典
	public static final String DI_DATA_ORIGIN_CHANNEL = "a700900a555b71ff47bb8cf7f7bb0956";// 数据来源渠道字典
	public static final String DI_ENTERPRISE_INDUSTR_TYPE = "a8c2c18c8ae83bda514c3ae1fcc77a1a";// 企业产业类型字典
	public static final String DI_MEDIA_TYPE = "ac1a2895f794a09f8e2be365b4f7b9e7";// 媒体类型字典
	public static final String DI_BASE_TYPE = "ad02806ca91ed6f7be7c1d523f4c68aa";// 基地类型字典
	public static final String DI_PRICE_UNIT = "adb2790cc6001d5122aefb819390860c";// 价钱单位字典
	public static final String DI_FEELING_TYPE = "ae7a32a28c4b0d1064d2b76e5d534897";// 情感值类型字典
	public static final String DI_MODULEDATA_AUDITING_STATUS_TYPE = "b683e56d72c785da65f76cdba38d06fe";// 模板数据表审核状态类型字典
	public static final String DI_AREA_UNIT = "b9a7bb32ad454f89bd6eeb9c4ba0cfb6";// 面积单位字典
	public static final String DI_ORG_TYPE = "be4fca93012401dd8600752e40c3d511";// 机构类型字典
	public static final String DI_PROCESS_OUTPUT = "be9c1c8427ca3cbf7d2cf4a8980c4461";// 产品产量字典
	public static final String DI_POPULAR_TABLE_STATUS_TYPE = "cbd2b2fab6fb0d9a0e134ccf80023a4e";// 舆情相关表状态类型字典
	public static final String DI_EBUSINESS_TYPE = "cd924a789656342d92bcac2f918de386";// 电商平台类型字典
	public static final String DI_EARLY_WARNING = "d8ef4cb8d95419d6f4d673336b6b83b6";// 预警类型字典
	public static final String DI_REQUEST_METHOD = "d9e634b861944c5f8c63544d16786dad";// HTTP请求方法
	public static final String DI_BOOLEAN = "dc1f9015660bcbcee7f1dfc1a5dea1ea";// boolean型字典
	public static final String DI_CATEGORY = "dd6c407283cfc1d4e749a6e0d6c33a12";// 地区类别字典
	public static final String DI_AGRICULTURE_DISASTER_TYPE = "e94c59ca245b8cb85038922067beb00d";// 农业灾害类型字典
	public static final String DI_HTML_TYPE = "ed53409f3deb9750eb92883b040a086c";// html类型字典
	public static final String DI_MOBILE_OPERATE_TYPE = "f03c7b3ff277b15adc93c638cba4a43f";// 智能手机操作熟练程度类型字典
	public static final String DI_PRODUCING_UNIT_TYPE = "f2643ad0c38c0a3a5fcb62d3c45f9720";// 原产区类型字典
	public static final String DI_CHANNEL_OF_DISTRIBUTION_TYPE = "f768e57c0536a6ebe712c68885caa1cd";// 销售渠道类型字典
	public static final String DI_QUOTA_DATA_STATUS_TYPE = "f7b936b246dc0ead6ea88dc7b0d24501";// 指标数据状态类型字典
	public static final String DI_CROP_BREED = "f8ba8a76017c654648c239a4a0f37b34";// 作物种类字典
	public static final String DI_ENTERPRISE_RELATION_TYPE = "fb498fc2c61dd4c81bc7e15310b1ad8e";// 企业上下游关系类型字典

	// 用于生成记录号serialNo的、雪花算法（Snowflake）的开始时间（2020/9/17
	// 17:38:36）及dataCentId和workerId暂时写成固定
	public static final long SNOWFLAKE_SERIAL_NO_STARTTIME = 1600335516000L;
	public static final int SNOWFLAKE_SERIAL_NO_DATACENTER_ID = 1;
	public static final int SNOWFLAKE_SERIAL_NO_WORKDER_ID = 1;

}