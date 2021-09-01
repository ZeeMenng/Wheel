package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprUserBaseGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-21 10:22:39
 * @description 用户归属的基地。 对外接口，扩展自GprUserBaseGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprUserBase")
public class  GprUserBaseSwgApp extends  GprUserBaseGenSwgApp {

}



