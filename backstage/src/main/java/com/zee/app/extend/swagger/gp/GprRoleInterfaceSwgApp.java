package com.zee.app.extend.swagger.gp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprRoleInterfaceGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.parameter.gp.GprRoleInterfaceParameter;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 角色拥有的接口权限。 对外接口，扩展自GprRoleInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprRoleInterface")
public class GprRoleInterfaceSwgApp extends GprRoleInterfaceGenSwgApp {
	
	
	
	@ApiOperation(value = "删除接口权限", notes = "删除接口权限")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpRole") })
	@RequestMapping(value = "/deleteInterfaceAuthority", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteInterfaceAuthority(@RequestBody GprRoleInterfaceParameter.DeleteByCompositeIdList jsonData) {
		ResultModel result = gprRoleInterfaceSplBll.deleteByCompositeIdList(jsonData.getEntityList());

		return result;
	}

	
	@ApiOperation(value = "查询接口权限", notes = "查询某一角色的接口权限")
	@ApiImplicitParam(paramType = "path", name = "roleId", value = "角色ID", required = true, dataType = "String")
	@RequestMapping(value = "/getListByRoleId/{roleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByRoleId(@PathVariable("roleId") String roleId) {
		ResultModel result = gprRoleInterfaceUntBll.getListByRoleId(roleId);
		List<GprRoleInterface> roleInterfaceList=(List<GprRoleInterface>)result.getData();
		result.setData(roleInterfaceList.stream().filter(gprRoleInterface->gprRoleInterface.getIsEnableCode()==CustomSymbolic.DCODE_BOOLEAN_T).map(GprRoleInterface::getInterfaceId).collect(Collectors.toList()));
		
		return result;
	}

	
	

}



