package com.zee.app.extend.swagger.gp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprConfigUserGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GprConfigUser;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.SqlSymbolic;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/19 11:23:52
 * @description 用户配置信息。 对外接口，扩展自GprConfigUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprConfigUser")
@Service("gprConfigUserSwgApp")
public class GprConfigUserSwgApp extends GprConfigUserGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GprConfigUser") })
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addOrUpdate(@RequestBody GprConfigUser jsonData) {
		// 赋值为当前用户的ID
		jsonData.setUserId(getCurrentUser().getId());

		// 查询是否存在
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = String.format(SqlSymbolic.SQL_SELECT_USER_CONFIG_UNIQUE, jsonData.getConfigId(), jsonData.getUserId());
		map.put("Sql", sql);
		ResultModel result = gprConfigUserUntBll.getListBySQL(map);

		// 存在则修改、不存在则删除
		if (result.getTotalCount() > 0) {
			result = gprConfigUserSplBll.updateByCompositeId(jsonData);
		} else {
			result = gprConfigUserUntBll.add(jsonData);
		}
		return result;
	}

	@ApiOperation(value = "查询应用配置", notes = "查询当前用户在当前应用领域下的配置项")
	@RequestMapping(value = "/getCurrentUserConfig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getCurrentUserConfig() {

		ResultModel resultModel = new ResultModel();

		GpUser user = getCurrentUser();
		if (user == null)
			throw new GlobalException("未能获取到登录用户信息……");
		String userId = user.getId();
		String domainId = user.getCurrentDomain().getId();
		Map<String, Object> map = new HashMap<String, Object>();

		String sql = String.format(SqlSymbolic.SQL_SELECT_USER_CONFIG_LIST, domainId, userId);

		map.put("Sql", sql);

		resultModel = gprConfigUserUntBll.getListBySQL(map);

		return resultModel;
	}

}
