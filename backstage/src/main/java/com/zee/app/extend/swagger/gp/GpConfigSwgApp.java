package com.zee.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpConfigGenSwgApp;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/19 11:57:31
 * @description 配置项信息。 对外接口，扩展自GpConfigGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpConfig")
public class GpConfigSwgApp extends GpConfigGenSwgApp {



}
