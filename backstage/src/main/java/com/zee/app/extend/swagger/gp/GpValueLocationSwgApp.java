package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpValueLocationGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/8/11 12:00:42
 * @description 调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。 对外接口，扩展自GpValueLocationGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpValueLocation")
public class  GpValueLocationSwgApp extends  GpValueLocationGenSwgApp {

}



