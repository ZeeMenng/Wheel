/*导出数据库*/
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
	A.`name` = B.`name`
	
