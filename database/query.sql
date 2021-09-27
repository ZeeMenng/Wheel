/*修改时间*/

update gp_dictionary set add_time=now(),update_time=now();
update gp_dictionary_type set add_time=now(),update_time=now();

/*修改地区信息表父键*/
UPDATE wheel.gp_region A,
wheel.gp_region B 
SET B.farther_id =A.id
WHERE
	  B.farther_code=A.code;

﻿/*导出数据库*/
mysqldump -uroot -p850934828 wheel > D:\JAVA\JavaProject\wheel\database\wheel.sql

/*清除字段中的空格*/
update `gp_region_country` set `chinese_name`=replace(`chinese_name`,' ','');

/**修改主键为UUID**/
UPDATE gp_region_country set id = MD5(UUID());

/***替换字段串**/
update gp_region_country set iso=REPLACE(iso,"ISO 3166-2:","")；

/**根据一个表中的字段修改另一个表中的字段信息**/
UPDATE wheel.gp_region A,
wheel.region_iso B 
SET A.iso = B.iso,
A.english_name = B.en_name,
A.category_fine_grit = B.category 
WHERE
	A.`name` = B.`name`;
	
	
UPDATE wheel.gp_organization A,
wheel.gp_organization_bak B 
SET A.LEVEL = B.level_code 
WHERE
	A.`id` = B.`id`;
	
UPDATE wheel.gp_station A,
wheel.gp_organization_bak B 
SET A.organization_name = B.NAME 
WHERE
	A.`organization_id` = B.`id`;
	
/**按照汉字拼音进行排序的方法，这种写法对程序不太友好；或者直接把字段的编码设置为gbk、排序集设置为gpk_chinese_ci，但这样可能引发数据库导入导出间的乱码问题**/
SELECT
	A.id id,
	A.serial_no serialNo,
	A.organization_id organizationId,
	A.organization_name organizationName,
	A.NAME NAME,
	A.priority priority,
	A.responsibility responsibility,
	A.remark remark,
	A.add_time addTime,
	A.update_time updateTime 
FROM
	gp_station A
	INNER JOIN gp_station B ON A.id = B.id 
WHERE
	1 = 1 
ORDER BY
	CONVERT ( organizationName USING gbk ) ASC

