package com.zee.app.extend.swagger.gp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.zee.ent.extend.gp.*;
import com.zee.set.symbolic.SqlSymbolic;
import com.zee.utl.CastObjectUtil;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpMessageGenSwgApp;
import com.zee.bll.extend.split.gp.GprMessageUserSplBll;
import com.zee.bll.extend.unity.gp.GprDomainMessageUntBll;
import com.zee.bll.extend.unity.gp.GprMessageUserUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.gp.GpMessageParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;
import com.zee.utl.DateUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 站内信。 对外接口，扩展自GpMessageGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpMessage")
public class GpMessageSwgApp extends GpMessageGenSwgApp {

    @Autowired
    @Qualifier("gprMessageUserUntBll")
    protected GprMessageUserUntBll gprMessageUserUntBll;

    @Autowired
    @Qualifier("gprMessageUserSplBll")
    protected GprMessageUserSplBll gprMessageUserSplBll;

    @Autowired
    @Qualifier("gprDomainMessageUntBll")
    protected GprDomainMessageUntBll gprDomainMessageUntBll;

    @ApiOperation(value = "新增记录", notes = "新增单条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpMessage")})
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel add(@RequestBody GpMessage jsonData) {

        Date addTime = DateUtils.getCurrentDate();
        jsonData.setAddTime(addTime);
        ResultModel result = gpMessageUntBll.add(jsonData);

        String receiverUserIds = jsonData.getReceiverUserIds();
        String receiverUserNames = jsonData.getReceiverUserNames();
        String[] receiverUserIdsArray = receiverUserIds.split(",");
        String[] receiverUserNamesArray = receiverUserNames.split(",");
        for (int i = 0; i < receiverUserIdsArray.length; i++) {
            GprMessageUser gprMessageUser = new GprMessageUser();
            gprMessageUser.setMessageId(jsonData.getId());
            gprMessageUser.setUserId(receiverUserIdsArray[i]);
            gprMessageUser.setUserName(receiverUserNamesArray[i]);
            byte isReadCode = 0;
            gprMessageUser.setIsReadCode(isReadCode);
            gprMessageUser.setAddTime(addTime);
            gprMessageUserUntBll.add(gprMessageUser);
        }

        String receiverDomainIds = jsonData.getReceiverDomainIds();
        String receiverDomainNames = jsonData.getReceiverDomainNames();
        String[] receiverDomainIdsArray = receiverDomainIds.split(",");
        String[] receiverDomainNamesArray = receiverDomainNames.split(",");
        for (int i = 0; i < receiverDomainIdsArray.length; i++) {
            GprDomainMessage gprDomainMessage = new GprDomainMessage();
            gprDomainMessage.setMessageId(jsonData.getId());
            gprDomainMessage.setDomainId(receiverDomainIdsArray[i]);
            gprDomainMessage.setDomainName(receiverDomainNamesArray[i]);

            gprDomainMessageUntBll.add(gprDomainMessage);
        }


        return result;
    }

    @ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
    @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel delete(@RequestParam String id) {

        List<Map<String, Object>> modelList = getModelList(id);
        ArrayList<String> idList = new ArrayList<String>();
        for (Map<String, Object> map2 : modelList) {
            idList.add(map2.get("id").toString());
        }
        if (idList != null && idList.size() > 0) {
            gprMessageUserUntBll.deleteByIdList(idList);
        }

        // 删除gpr_domain_message表的相关消息
        List<Map<String, Object>> domainMesList = getDomainMessageList(id);
        ArrayList<String> dmList = new ArrayList<String>();
        for (Map<String, Object> dmap : domainMesList) {
            dmList.add(dmap.get("id").toString());
        }
        if (dmList != null && dmList.size() > 0) {
            gprDomainMessageUntBll.deleteByIdList(dmList);
        }

        ResultModel result = gpMessageUntBll.delete(id);

        return result;
    }

    /**
     * 根据messageId查询中间表列表
     *
     * @param messageId
     */
    private List<Map<String, Object>> getModelList(String messageId) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("SELECT A.id id FROM gpr_message_user A WHERE A.message_id = '" + messageId + "'");
        map.put("Sql", selectBuffer.toString());
        ResultModel resultModel = gprMessageUserUntBll.getListBySQL(map);
        List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
        return modelList;
    }

    /**
     * 根据messageId查询gpr_domain_message表
     *
     * @param messageId
     */
    private List<Map<String, Object>> getDomainMessageList(String messageId) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("SELECT A.id id FROM gpr_domain_message A WHERE A.message_id = '" + messageId + "'");
        map.put("Sql", selectBuffer.toString());
        ResultModel resultModel = gprDomainMessageUntBll.getListBySQL(map);
        List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
        return modelList;
    }

    @ApiOperation(value = "批量修改", notes = "同时修改多条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpMessageUpdateList")})
    @RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateList(@RequestBody GpMessageParameter.UpdateList jsonData) {

        jsonData.getEntity().setAddTime(DateUtils.getCurrentTime());
        ResultModel result = gpMessageUntBll.updateList(jsonData);

        return result;
    }

    @ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getModelByPath(@PathVariable("id") String id) {
        ResultModel result = gpMessageUntBll.getModel(id);

        GpMessage gpMessage = (GpMessage) result.getData();
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("	SELECT                                                            ");
        selectBuffer.append("		A.*,                                                          ");
        selectBuffer.append("		B.id pId                                                      ");
        selectBuffer.append("	FROM                                                              ");
        selectBuffer.append("		gp_message A                                                  ");
        selectBuffer.append("	LEFT JOIN gpr_message_user B on B.message_id = A.id               ");
        selectBuffer.append("	WHERE                                                             ");
        selectBuffer.append("		A.id = '" + gpMessage.getId() + "'                                 ");
        map.put("Sql", selectBuffer.toString());
        ResultModel resultModel = gpMessageUntBll.getListBySQL(map);
        List<Map<String, Object>> messageList = CastObjectUtil.cast(resultModel.getData());
        Map<String, Object> messageMap = messageList.get(0);

        ResultModel resultModel1 = new ResultModel();
        GprMessageUser jsonData1 = new GprMessageUser();

        jsonData1.setId((messageMap.get("pId") != null ? messageMap.get("pId").toString() : ""));
        byte isReadCode = 1;
        jsonData1.setIsReadCode(isReadCode);
        Date readTime = DateUtils.getCurrentDate();
        jsonData1.setReadTime(readTime);

        resultModel1 = gprMessageUserUntBll.update(jsonData1);

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
        selectBuffer.append(SqlSymbolic.SQL_SELECT_MESSAGE_LIST);

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
                if (entityRelatedObject.containsKey("keywords") && StringUtils.isNotBlank(entityRelatedObject.getString("keywords"))) {
                    selectBuffer.append(String.format(" and( A.title like %1$s or A.type_text like %1$s or A.content like %1$s  or A.user_name like %1$s or A.receiver_user_names like %1$s or A.receiver_domain_names like %1$s)", "'%" + entityRelatedObject.getString("keywords") + "%'"));
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

        resultModel = gpMessageUntBll.getListBySQL(map);

        return resultModel;
    }


    @ApiOperation(value = "查询当前登陆的用户通知公告列表forapp", notes = "查询当前登陆的用户通知公告列表forapp")
    @RequestMapping(value = "/getMessageListForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getMessageListForApp() {
        ResultModel resultModel = new ResultModel();

        GpUser gpUser = this.getCurrentUser();
        String id = gpUser.getId();

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("select A.id id,A.user_id userId,A.user_name userName,A.title title,A.content content,A.remark remark,A.add_time addTime ");
        selectBuffer.append(",(CASE when C.is_read_code=0 then '未读' when C.is_read_code=1 then '已读' end) isReadCode ");
        selectBuffer.append(",(CASE when B.is_admin_code='0' then '系统消息' when B.is_admin_code='1' then '用户消息' end) messageType ");
        selectBuffer.append(" from gp_message A LEFT JOIN gpr_message_user C on C.message_id = A.id LEFT JOIN gp_user B on A.user_id = B.id ");
        selectBuffer.append(" and A.user_name = B.user_name where C.user_id = '" + id + "' and B.is_admin_code='0' ");

        map.put("Sql", selectBuffer.toString());

        resultModel = gpMessageUntBll.getListBySQL(map);
        if (resultModel.getResultCode() == 969701) {
            // 不存在记录
            resultModel.setResultMessage("");
            resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
        }

        return resultModel;
    }

}
