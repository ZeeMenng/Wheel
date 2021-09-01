package com.zee.app.extend.swagger.gp;

import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprConfigDomainGenSwgApp;
import com.zee.bll.extend.unity.gp.GpConfigUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpConfig;
import com.zee.ent.extend.gp.GprConfigDomain;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.set.symbolic.SqlSymbolic;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/20 10:44:14
 * @description 应用领域配置信息。 对外接口，扩展自GprConfigDomainGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprConfigDomain")
public class GprConfigDomainSwgApp extends GprConfigDomainGenSwgApp {

	@Autowired
	@Qualifier("gpConfigUntBll")
	protected GpConfigUntBll gpConfigUntBll;

	@ApiOperation(value = "新增应用领域配置", notes = "新增应用领域配置")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpConfig") })
	@RequestMapping(value = "/addDomainConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addDomainConfig(@RequestBody GpConfig jsonData) {

		jsonData.setDefaultValue(jsonData.getConfigValue());
		ResultModel result = gpConfigUntBll.add(jsonData);

		GprConfigDomain gprConfigDomain = new GprConfigDomain();
		gprConfigDomain.setConfigId(result.getObjectId());
		gprConfigDomain.setDomainId(jsonData.getBusinessId());
		gprConfigDomain.setConfigValue(jsonData.getConfigValue());

		result = gprConfigDomainUntBll.add(gprConfigDomain);

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GprConfigDomain") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GprConfigDomain jsonData) {

		ResultModel result = gprConfigDomainUntBll.update(jsonData);

		GpConfig gpConfig = new GpConfig();

		gpConfig.setId(jsonData.getConfigId());
		gpConfig.setConfigValue(jsonData.getConfigValue());
		gpConfig.setName(jsonData.getName());
		gpConfig.setCode(jsonData.getCode());
		gpConfig.setRemark(jsonData.getRemark());

		result = gpConfigUntBll.update(gpConfig);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getDomainConfigByPath/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDomainConfigByPath(@PathVariable("id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		String sql = String.format(SqlSymbolic.SQL_SELECT_DOMAIN_CONFIG, id);
		map.put("Sql", sql);
		ResultModel result = gprConfigDomainUntBll.getListBySQL(map);
		if (result.getTotalCount() == 1)
			result.setData(((List<Map<String, Object>>) result.getData()).get(0));

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(SqlSymbolic.SQL_SELECT_DOMAIN_CONFIG_LIST);

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("kewwords") && StringUtils.isNotBlank(entityRelatedObject.getString("kewwords"))) {
					selectBuffer.append(String.format(" and( B.code like %1$s or B.name like %1$s or A.config_value like %1$s  or C.name like %1$s )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
				}

			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if (jsonObject.containsKey("orderList")) {
				JSONArray orderListArray = jsonObject.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gprConfigDomainUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询Key是否重复", notes = "查询Key是否重复")
	@RequestMapping(value = "/isUniqueCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel isUniqueCode() {
		ResultModel result = new ResultModel();
		String code = request.getParameter("code");
		String oldCode = request.getParameter("oldCode");
		if (StringUtils.isNotEmpty(oldCode) && code.equals(oldCode)) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();

		String sql = String.format(SqlSymbolic.SQL_SELECT_CONFIG_KEY, code);
		map.put("Sql", sql);
		result = gprConfigDomainUntBll.getListBySQL(map);
		result.setIsSuccessCode(result.getTotalCount() == 0 ? CustomSymbolic.DCODE_BOOLEAN_T : CustomSymbolic.DCODE_BOOLEAN_F);
		return result;
	}

}
