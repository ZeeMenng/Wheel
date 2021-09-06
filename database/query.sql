/*导出数据库*/
mysqldump -uroot -p850934828 wheel > D:\JAVA\JavaProject\wheel\database\wheel.sql

/*清除字段中的空格*/
update `gp_region_country` set `chinese_name`=replace(`chinese_name`,' ','');