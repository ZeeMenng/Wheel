package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprDomainUserGenSwgApp;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 应用领域拥有的用户。 对外接口，扩展自GprDomainUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprDomainUser")
public class GprDomainUserSwgApp extends GprDomainUserGenSwgApp {

}
