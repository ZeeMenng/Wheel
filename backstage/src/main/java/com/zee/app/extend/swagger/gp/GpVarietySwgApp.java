package com.zee.app.extend.swagger.gp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpVarietyGenSwgApp;
import com.zee.bll.extend.split.gp.GpVarietySplBll;
import com.zee.bll.extend.unity.gp.GpVarietyUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpVariety;
import com.zee.ent.parameter.gp.GpVarietyParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-7-31 14:22:08
 * @description 品种表 对外接口，扩展自GpVarietyGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpVariety")
public class GpVarietySwgApp extends GpVarietyGenSwgApp {

	@Autowired
	@Qualifier("gpVarietyUntBll")
	protected GpVarietyUntBll gpVarietyUntBll;

	@Autowired
	@Qualifier("gpVarietySplBll")
	protected GpVarietySplBll gpVarietySplBll;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取品种数据", notes = "获取品种数据")
	@RequestMapping(value = "/getVarietyData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getVarietyData() {
		ResultModel resultModel = new ResultModel();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                               ");
			selectBuffer.append("       a.id, a.name, a.parent_id pId,                   ");
			selectBuffer.append("		a.relation_id code,                              ");
			selectBuffer.append("		a.describes,                                      ");
			selectBuffer.append("		a.resource_url resourceUrl                                      ");
			selectBuffer.append("	FROM                                                 ");
			selectBuffer.append("		gp_variety a                                     ");
			selectBuffer.append("	WHERE a.name IS NOT NULL ");

			String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
			if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				if (jsonObject.containsKey("id") && StringUtils.isNotBlank(jsonObject.getString("id"))) {
					selectBuffer.append("  AND a.id = '").append(jsonObject.getString("id")).append("'");
				}
				if (jsonObject.containsKey("code") && StringUtils.isNotBlank(jsonObject.getString("code"))) {
					selectBuffer.append("  AND a.relation_id like '").append(jsonObject.getString("code")).append("%'");
				}
			}
			selectBuffer.append("	ORDER BY a.priority ASC  ");

			map.put("Sql", selectBuffer.toString());
			resultModel = gpVarietyUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map2 : modelList) {
				Map<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", map2.get("id"));
				treeMap.put("pId", map2.get("pId"));
				treeMap.put("name", map2.get("name"));
				treeMap.put("resourceUrl", map2.get("resourceUrl"));
				treeMap.put("code", map2.get("code"));
				treeMap.put("describes", map2.get("describes"));
				dataList.add(treeMap);
			}
			resultModel.setData(dataList);
		} catch (Exception e) {
			resultModel.setIsSuccessCode((byte) 1);
			resultModel.setIsSuccessValue("false");
			e.printStackTrace();
		}
		return resultModel;
	}

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpVariety") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpVariety jsonData) {
		ResultModel result = new ResultModel();
		try {
			if (jsonData != null) {
				jsonData.setAddTime(new Date());
				jsonData.setId(Tools.getUUID());
				jsonData.setAddUserId(this.getCurrentUser().getId());
				String parentId = jsonData.getParentId();
				if (StringUtils.isNotBlank(parentId)) {
					GpVariety gpVariety = getGpVarietyById(parentId);
					if (gpVariety != null && StringUtils.isNotBlank(gpVariety.getRelationId())) {
						jsonData.setRelationId(getMaxRelationId(gpVariety.getRelationId()));
					}
				}
			}
			result = gpVarietyUntBll.add(jsonData);
		} catch (Exception e) {
			result.setIsSuccessCode((byte) 1);
			result.setIsSuccessValue("false");
			e.printStackTrace();
		}

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpVariety") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpVariety jsonData) {
		ResultModel result = new ResultModel();
		try {
			if (jsonData != null) {
				String parentId = jsonData.getParentId();
				if (StringUtils.isNotBlank(parentId)) {
					// 父级对象信息
					GpVariety gpVariety = getGpVarietyById(parentId);
					// 旧对象
					GpVariety oldVariety = getGpVarietyById(jsonData.getId());
					// 如果修改父级节点，再修改relationId，否则不修改relationId
					if (gpVariety != null && StringUtils.isNotBlank(gpVariety.getRelationId()) && !parentId.equals(oldVariety.getParentId())) {
						jsonData.setRelationId(getMaxRelationId(gpVariety.getRelationId()));
					}
				}
			}
			result = gpVarietyUntBll.update(jsonData);
		} catch (Exception e) {
			result.setIsSuccessCode((byte) 1);
			result.setIsSuccessValue("false");
			e.printStackTrace();
		}
		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = gpVarietyUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpVarietyUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "GpVarietyGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody GpVarietyParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = gpVarietyUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.name name,A.parent_id parentId,A.relation_id relationId,A.describes describes,A.resource_id resourceId,A.resource_url resourceUrl,A.stutas_code stutasCode,A.stutas_text stutasText,A.cycle_time cycleTime,A.priority priority,A.remark remark,A.add_user_id addUserId,A.add_time addTime,A.update_time updateTime  from gp_variety A inner join gp_variety B on A.id=B.id where 1=1 ");

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
				if (entityRelatedObject.containsKey("describes") && StringUtils.isNotBlank(entityRelatedObject.getString("describes")))
					selectBuffer.append(" and A.describes like '%").append(entityRelatedObject.getString("describes")).append("%'");
				if (entityRelatedObject.containsKey("relationId") && StringUtils.isNotBlank(entityRelatedObject.getString("relationId")))
					selectBuffer.append(" and A.relation_id like '%").append(entityRelatedObject.getString("relationId")).append("%'");
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

		resultModel = gpVarietyUntBll.getListBySQL(map);

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取品种树状结构的数据", notes = "获取品种树状结构的数据")
	@RequestMapping(value = "/getTreeNodeData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTreeNodeData() {
		ResultModel resultModel = new ResultModel();
		try {
			String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
			if (StringUtils.isBlank(jsonData))
				return resultModel;

			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                               ");
			selectBuffer.append("		A.id id,                                         ");
			selectBuffer.append("		A.parent_id fartherId,                           ");
			selectBuffer.append("		A.name                                           ");
			selectBuffer.append("	FROM                                                 ");
			selectBuffer.append("		gp_variety A                                     ");
			selectBuffer.append("	WHERE A.name IS NOT NULL ");
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("id") && StringUtils.isNotBlank(jsonObject.getString("id"))) {
				selectBuffer.append("	AND A.id != ");
				selectBuffer.append("'");
				selectBuffer.append(jsonObject.getString("id"));
				selectBuffer.append("'");
			}
			selectBuffer.append("	 ORDER BY A.priority ASC  ");

			map.put("Sql", selectBuffer.toString());
			resultModel = gpVarietyUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map2 : modelList) {
				Map<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", map2.get("id"));
				treeMap.put("pId", map2.get("fartherId"));
				treeMap.put("name", map2.get("name"));
				treeMap.put("open", true);
				treeNodes.add(treeMap);
			}
			resultModel.setData(treeNodes);
		} catch (Exception e) {
			resultModel.setIsSuccessCode((byte) 1);
			resultModel.setIsSuccessValue("false");
			e.printStackTrace();
		}
		return resultModel;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = gpVarietyUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = gpVarietyUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpVarietyDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpVarietyParameter.DeleteByIdList jsonData) {
		ResultModel result = gpVarietyUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpVarietyUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpVarietyParameter.UpdateList jsonData) {
		ResultModel result = gpVarietyUntBll.updateList(jsonData);

		return result;
	}

	/**
	 * 根据id查询出品种对象信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private GpVariety getGpVarietyById(String id) {
		GpVariety variety = new GpVariety();
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT    ");
		selectBuffer.append("		a.id, a.priority, a.parent_id parentId, a.relation_id relationId ");
		selectBuffer.append("	FROM       ");
		selectBuffer.append("		gp_variety a  ");
		selectBuffer.append("   WHERE a.id = ");
		selectBuffer.append("'");
		if (!StringUtils.isBlank(id)) {
			selectBuffer.append(id);
		}
		selectBuffer.append("'");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpVarietyUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		if (object != null) {
			List<Map<String, Object>> objectList = (List<Map<String, Object>>) object;
			if (objectList != null && objectList.size() == 1) {
				JSONObject jsonData = JSONObject.fromObject(objectList.get(0));
				variety = (GpVariety) JSONObject.toBean(jsonData, GpVariety.class);
			}
		}
		return variety;
	}

	/**
	 * 获取最大relationId
	 * @param parentRelationId 父节点的relationId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getMaxRelationId(String parentRelationId) {
		String relationId = "";
		int len = parentRelationId.length() + 4;// relationId规则以4位递增
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                          ");
		selectBuffer.append("		max(a.relation_id) relationId 				");
		selectBuffer.append("	FROM                                            ");
		selectBuffer.append("		gp_variety a                                ");
		selectBuffer.append("   WHERE         								    ");
		selectBuffer.append("   LENGTH(relation_id) = 	");
		selectBuffer.append(len);
		selectBuffer.append("   AND a.relation_id LIKE '");
		selectBuffer.append(parentRelationId).append("%'");

		map.put("Sql", selectBuffer.toString());
		resultModel = gpVarietyUntBll.getListBySQL(map);

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
		} else {
			relationId = parentRelationId + "0001";
		}
		if (objectList != null) {
			if (objectList.size() == 0) {
				relationId = parentRelationId + "0001";
			} else {
				objMap = objectList.get(0);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				// 解析出需要的id值
				if (jsonData.containsKey("relationId")) {
					String relId = jsonData.getString("relationId");
					if (StringUtils.isNotBlank(relId) && relId != "null") {
						relId = relId.substring(relId.length() - 4, relId.length());// 获取后四位
						int maxRelId = Integer.parseInt(relId) + 1;// 计算后String.format会自动补零
						// 其中0是被填充到缺省位的数字，4代表规定数字的总位数 d代表是整型
						relationId = parentRelationId + String.format("%04d", Integer.valueOf(maxRelId));
					} else {
						relationId = parentRelationId + "0001";
					}
				} else {
					relationId = parentRelationId + "0001";
				}
			}
		} else {
			relationId = parentRelationId + "0001";
		}
		return relationId;
	}

}
