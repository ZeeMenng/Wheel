package com.zee.app.extend.swagger.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpCatalogInterfaceGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.parameter.gp.GpCatalogInterfaceParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.set.symbolic.SqlSymbolic;
import com.zee.utl.ClassFieldNullable;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/10/21 21:21:11
 * @description 接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。 对外接口，扩展自GpCatalogInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpCatalogInterface")
public class GpCatalogInterfaceSwgApp extends GpCatalogInterfaceGenSwgApp {

	@ApiOperation(value="新增记录",notes="新增单条记录")@ApiImplicitParams({@ApiImplicitParam(paramType="body",name="jsonData",value="json字符串",required=true,dataTypeClass=GpCatalogInterface.class)
	})

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpCatalogInterface jsonData) {

		ResultModel result = gpCatalogInterfaceUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "GpCatalogInterfaceAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateListWithDff(@RequestBody GpCatalogInterfaceParameter.AddList jsonData) {

		ArrayList<GpCatalogInterface> list = ClassFieldNullable.convertNull(jsonData.getEntityList(), new ArrayList<String>() {
			{
				add("fartherId");
			}
		});
		jsonData.setEntityList(list);
		ResultModel result = gpCatalogInterfaceUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "String") })
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(SqlSymbolic.SQL_SELECT_INTERFACE_CATALOG_LIST);

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

				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("serialNo") && StringUtils.isNotBlank(entityRelatedObject.getString("serialNo")))
					selectBuffer.append(" and A.serial_no like '%").append(entityRelatedObject.getString("serialNo")).append("%'");
				if (entityRelatedObject.containsKey("level") && StringUtils.isNotBlank(entityRelatedObject.getString("level")))
					selectBuffer.append(" and A.level like '%").append(entityRelatedObject.getString("level")).append("%'");
				if (entityRelatedObject.containsKey("categoryCode") && StringUtils.isNotBlank(entityRelatedObject.getString("categoryCode")))
					selectBuffer.append(" and A.category_code = '").append(entityRelatedObject.getString("categoryCode")).append("'");

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

		resultModel = gpCatalogInterfaceUntBll.getListBySQL(map);

		return resultModel;
	}

}
