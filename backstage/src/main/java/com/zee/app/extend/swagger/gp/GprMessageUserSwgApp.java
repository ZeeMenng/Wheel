package com.zee.app.extend.swagger.gp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GprMessageUserGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 消息接收者及消息读取状态。 对外接口，扩展自GprMessageUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprMessageUser")
public class GprMessageUserSwgApp extends GprMessageUserGenSwgApp {

	@ApiOperation(value = "用户消息提醒", notes = "根据当前用户id及用户名称查询未读个人消息数")
	@RequestMapping(value = "/getUserListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getUserListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select C.user_id userId,C.user_name userName,C.content content,C.add_time addTime ");
		selectBuffer.append(" from gpr_message_user A LEFT JOIN gp_message C on A.message_id = C.id LEFT JOIN gp_user B on B.id = C.user_id where 1=1 and A.is_read_code = '0' and B.is_admin_code = '1' ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");

				if (entityRelatedObject.containsKey("userId") && StringUtils.isNotBlank(entityRelatedObject.getString("userId")))
					selectBuffer.append(" and A.user_id = '").append(entityRelatedObject.getString("userId")).append("'");
				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName")))
					selectBuffer.append(" and A.user_name = '").append(entityRelatedObject.getString("userName")).append("'");
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
					selectBuffer.append("C." + orderColumnObject.getString("add_time"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gprMessageUserUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "系统消息提醒", notes = "根据当前用户id及用户名称查询未读系统消息数")
	@RequestMapping(value = "/getSysListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSySListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select C.user_id userId,C.user_name userName,C.content content,C.add_time addTime ");
		selectBuffer.append(" from gpr_message_user A LEFT JOIN gp_message C on A.message_id = C.id LEFT JOIN gp_user B on B.id = C.user_id where 1=1 and A.is_read_code = '0' and B.is_admin_code = '0' ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");

				if (entityRelatedObject.containsKey("userId") && StringUtils.isNotBlank(entityRelatedObject.getString("userId")))
					selectBuffer.append(" and A.user_id = '").append(entityRelatedObject.getString("userId")).append("'");
				// if (entityRelatedObject.containsKey("userName") &&
				// StringUtils.isNotBlank(entityRelatedObject.getString("userName")))
				// selectBuffer.append(" and A.user_name =
				// '").append(entityRelatedObject.getString("userName")).append("'");
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
					selectBuffer.append("C." + orderColumnObject.getString("add_time"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gprMessageUserUntBll.getListBySQL(map);

		return resultModel;
	}

}
