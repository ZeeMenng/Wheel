package com.zee.app.extend.swagger.gp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprCatalogInterfaceGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.gp.GprCatalogInterfaceParameter;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/10/21 21:21:11
 * @description 后台接口所属分类。 对外接口，扩展自GprCatalogInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprCatalogInterface")
public class GprCatalogInterfaceSwgApp extends GprCatalogInterfaceGenSwgApp {

	@ApiOperation(value = "更换接口分类", notes = "批量划分某分类方式下的某接口的分类")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "GprCatalogInterfaceAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody GprCatalogInterfaceParameter.AddList jsonData) {

		gprCatalogInterfaceSplBll.deleteInvalidRecord(jsonData.getEntityList());
		ResultModel result = gprCatalogInterfaceUntBll.add(jsonData.getEntityList());

		return result;
	}

}
