package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprRoleDomainGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/8/11 11:39:55
 * @description 角色拥有的功能模块权限。 对外接口，扩展自GprRoleDomainGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprRoleDomain")
public class  GprRoleDomainSwgApp extends  GprRoleDomainGenSwgApp {

}



