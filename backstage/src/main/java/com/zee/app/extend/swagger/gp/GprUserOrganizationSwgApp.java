package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprUserOrganizationGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 用户所属组织机构。 对外接口，扩展自GprUserOrganizationGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprUserOrganization")
public class GprUserOrganizationSwgApp extends GprUserOrganizationGenSwgApp {

}



