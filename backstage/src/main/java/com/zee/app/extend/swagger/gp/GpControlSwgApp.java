package com.zee.app.extend.swagger.gp;

import java.util.HashMap;
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

import com.zee.app.generate.swagger.gp.GpControlGenSwgApp;
import com.zee.bll.extend.unity.gp.GpPageUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpPage;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.set.symbolic.SqlSymbolic;
import com.zee.utl.CastObjectUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统控件。 对外接口，扩展自GpControlGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpControl")
public class GpControlSwgApp extends GpControlGenSwgApp {

	@Autowired
	@Qualifier("gpPageUntBll")
	protected GpPageUntBll gpPageUntBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpControl") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpControl jsonData) {

		// 根据所属页面主键，查询并设置所属应用领域主键
		String pageId = jsonData.getPageId();
		ResultModel result = gpPageUntBll.getModel(pageId);
		GpPage gpPage = CastObjectUtil.cast(result.getData());
		jsonData.setDomainId(gpPage.getDomainId());

		result = gpControlUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpControl") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpControl jsonData) {

		// 根据所属页面主键，查询并设置所属应用领域主键
		String pageId = jsonData.getPageId();
		ResultModel result = gpPageUntBll.getModel(pageId);
		GpPage gpPage = CastObjectUtil.cast(result.getData());
		jsonData.setDomainId(gpPage.getDomainId());

		result = gpControlUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = String.format(SqlSymbolic.SQL_SELECT_CONTROL_UNIQUE, id);
		map.put("Sql", sql);

		ResultModel resultModel = gpControlUntBll.getListBySQL(map);
		if (resultModel.getTotalCount() != 0) {
			List<Map<String, Object>> controlList = CastObjectUtil.cast(resultModel.getData());
			resultModel.setData(controlList.get(0));
		}
		return resultModel;
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
		selectBuffer.append(SqlSymbolic.SQL_SELECT_CONTROL_LIST);

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
					selectBuffer.append(String.format(" and( A.code like %1$s or A.name like %1$s or A.page_url like %1$s  or B.name like %1$s )", "'%" + entityRelatedObject.getString("kewwords") + "%'"));
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
					selectBuffer.append("A." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpControlUntBll.getListBySQL(map);

		return resultModel;
	}

}
