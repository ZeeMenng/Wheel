package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprResourceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/7/4 16:32:20
 * @description 附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。 对外接口，扩展自GprResourceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprResource")
public class  GprResourceSwgApp extends  GprResourceGenSwgApp {

}



