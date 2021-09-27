package com.zee.set.symbolic;

/**
 * @author Zee
 * @createDate 2021年2月2日 上午11:28:52
 * @updateDate 2021年2月2日 上午11:28:52
 * @description SQL常量类
 */
public class SqlSymbolic {

	// 查询出某个用户没有拥有、但相应角色拥有的应用领域。
	public static final String SQL_SELECT_ROLE_DOMAIN_ID = "select domain_id from gpr_role_domain where role_id in (%s) and domain_id not in (select domain_id from gpr_domain_user where user_id='%s') group by domain_id";

	// 根据角色，查出这个角色下的用户和应用领域
	public static final String SQL_SELECT_USER_DOMAIN_BY_ROLE = "select A.domain_id,B.user_id from gpr_role_domain A inner join gpr_user_role B on A.role_id=B.role_id where A.role_id='%s'";

	public static final String SQL_SELECT_ROLE_GET_LIST = "SELECT distinct A.id id,A.name name,A.remark remark,A.add_time addTime, A.update_time updateTime FROM gp_role A left join gpr_role_domain B on A.id=B.role_id WHERE 1 = 1";

	// 根据code查询配置项的Key是否唯一
	public static final String SQL_SELECT_CONFIG_KEY = "SELECT id FROM gp_config  WHERE code = '%s'";

	// 根据配置id查询应用领域配置项
	public static final String SQL_SELECT_DOMAIN_CONFIG = "SELECT A.id id,A.domain_id domainId,B.default_value defaultValue,A.config_id configId,A.config_value configValue,A.add_time addTime,A.update_time updateTime,B.code code,B.name name,B.remark remark,C.name domainName FROM gpr_config_domain A inner join gp_config B on A.config_id=B.id inner join gp_domain C on A.domain_id=C.id WHERE B.id = '%s'";

	// 应用配置列表模糊查询
	public static final String SQL_SELECT_DOMAIN_CONFIG_LIST = "select B.id id,A.config_value configValue,A.update_time updateTime,B.code code,B.name name,C.name domainName  from gpr_config_domain A inner join gp_config B on A.config_id=B.id inner join gp_domain C on A.domain_id=C.id WHERE 1=1 ";

	// 根据用户ID和应用领域ID查询当前用户在当前应用领域下的配置项
	public static final String SQL_SELECT_USER_CONFIG_LIST = "select A.id configId,A.code code,IFNULL( C.config_value, IFNULL( B.config_value, A.default_value ) ) configValue from gp_config A inner join gpr_config_domain B on A.id=b.config_id  left join gpr_config_user C on A.id=C.config_id  where  B.domain_id='%s' and (C.user_id='%s' or C.user_id is null)";

	// 根据config_id和user_id查询gpr_config_user表，判断记录是否存在
	public static final String SQL_SELECT_USER_CONFIG_UNIQUE = "select id from  gpr_config_user where config_id='%s' and user_id='%s' ";

	// 查询服务目录
	public static final String SQL_SELECT_INTERFACE_CATALOG_LIST = "select A.id id,A.name name,A.serial_no serialNo,A.level level,A.farther_id fartherId,A.priority priority,A.category_code categoryCode,A.category_text categoryText,A.remark remark  from gp_catalog_interface A inner join gp_catalog_interface B on A.id=B.id where 1=1";

	//查询系统组件
	public static final String SQL_SELECT_CONTROL_LIST="select A.id id,A.domain_id domainId,A.code code,B.name domainName,A.page_id pageId,A.page_url pageUrl,A.name name,A.serial_no serialNo,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_control A left join gp_domain B on A.domain_id=B.id where 1=1 ";
	

	//查询系统组件
	public static final String SQL_SELECT_CONTROL_UNIQUE="select A.id id,A.domain_id domainId,A.code code,B.name domainName,A.page_id pageId,A.page_url pageUrl,A.name name,A.serial_no serialNo,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_control A left join gp_domain B on A.domain_id=B.id where A.id = '%s' ";


	// 查询地区列表
	public static final String SQL_SELECT_REGION_LIST = "select A.id id,A.farther_id fartherId,A.code code,A.name name,A.english_name englishName,A.farther_code fartherCode,A.level level,A.category_code categoryCode,A.category_text categoryText,A.latitude latitude,A.longitude longitude,A.area area,A.iso iso,A.country_iso countryIso,A.is_display_code isDisplayCode,A.remark remark  from gp_region A inner join gp_region B on A.id=B.id where 1=1 ";

	// 查询字典列表
	public static final String SQL_SELECT_DICTIONARY_LIST = "select A.id id,A.type_id typeId,A.code code,A.text text,A.priority priority,A.remark remark,A.add_time addTime,A.update_time updateTime,B.name typeName,B.constant_name typeConstantName from gp_dictionary A inner join gp_dictionary_type B on A.type_id=B.id where 1=1 ";

	// 查询字典项列表
	public static final String SQL_SELECT_DICTIONARYTYPE_LIST = "select A.id id,A.name name,A.constant_name constantName,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_dictionary_type A inner join gp_dictionary_type B on A.id=B.id where 1=1 ";


	//查询组织机构列表
	public static final String SQL_SELECT_ORGANIZATION_LIST = "select A.id id,A.serial_no serialNo,A.name name,A.short_name shortName,A.type_code typeCode,A.type_text typeText,A.level level,A.farther_id fartherId,A.priority priority,A.phone phone,A.email email,A.fax fax,A.postcode postcode,A.address address,A.responsibility responsibility,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_organization A inner join gp_organization B on A.id=B.id where 1=1 ";

	//查询组织机构岗位列表
	public static final String SQL_SELECT_STATION_LIST = "select A.id id,A.serial_no serialNo,A.organization_id organizationId,A.organization_name organizationName,A.name name,A.priority priority,A.responsibility responsibility,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_station A inner join gp_station B on A.id=B.id where 1=1 ";

}