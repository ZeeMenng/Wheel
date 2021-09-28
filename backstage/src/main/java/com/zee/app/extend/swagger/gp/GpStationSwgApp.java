package com.zee.app.extend.swagger.gp;

import com.zee.set.symbolic.SqlSymbolic;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import com.zee.utl.CastObjectUtil;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.gp.GpStationGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.extend.gp.GpStation;
import com.zee.ent.parameter.gp.GpStationParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 岗位。 对外接口，扩展自GpStationGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpStation")
public class GpStationSwgApp extends GpStationGenSwgApp {

    @ApiOperation(value = "新增记录", notes = "新增单条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpStation")})
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel add(@RequestBody GpStation jsonData) {

        Date addTime = DateUtils.getCurrentDate();
        jsonData.setAddTime(addTime);
        ResultModel result = gpStationUntBll.add(jsonData);

        return result;
    }

    @ApiOperation(value = "修改记录", notes = "修改指定记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpStation")})
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel update(@RequestBody GpStation jsonData) {

        Date updateTime = DateUtils.getCurrentDate();
        jsonData.setUpdateTime(updateTime);
        ResultModel result = gpStationUntBll.update(jsonData);

        return result;
    }

    @ApiOperation(value = "批量修改", notes = "同时修改多条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpStationUpdateList")})
    @RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateList(@RequestBody GpStationParameter.UpdateList jsonData) {

        jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
        ResultModel result = gpStationUntBll.updateList(jsonData);

        return result;
    }

    @ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getModelByPath(@PathVariable("id") String id) {
        ResultModel result = gpStationUntBll.getModel(id);

        GpStation gpStation = (GpStation) result.getData();
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("	SELECT                                                            ");
        selectBuffer.append("		ft.name organizationName                                      ");
        selectBuffer.append("	FROM                                                              ");
        selectBuffer.append("		gp_station t                                                  ");
        selectBuffer.append("	LEFT JOIN gp_organization ft ON ft.id = t.organization_id         ");
        selectBuffer.append("	WHERE                                                             ");
        selectBuffer.append("		t.id = '" + gpStation.getId() + "'                                ");
        map.put("Sql", selectBuffer.toString());
        ResultModel resultModel = gpStationUntBll.getListBySQL(map);
        List<Map<String, Object>> stationList = CastObjectUtil.cast(resultModel.getData());
        Map<String, Object> stationMap = stationList.get(0);
        gpStation.setOrganizationName(stationMap.get("organizationName") != null ? stationMap.get("organizationName").toString() : "");
        result.setData(gpStation);

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
        selectBuffer.append(SqlSymbolic.SQL_SELECT_STATION_LIST);

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
                if (entityRelatedObject.containsKey("keywords") && StringUtils.isNotBlank(entityRelatedObject.getString("keywords")))
                    selectBuffer.append(String.format(" and( A.organization_name like %1$s or A.name like %1$s  or A.responsibility like %1$s)", "'%" + entityRelatedObject.getString("keywords") + "%'"));
                if (entityRelatedObject.containsKey("serialNo") && StringUtils.isNotBlank(entityRelatedObject.getString("serialNo")))
                    selectBuffer.append(" and A.serial_no like '%").append(entityRelatedObject.getString("serialNo")).append("%'");
                if (entityRelatedObject.containsKey("organizationName") && StringUtils.isNotBlank(entityRelatedObject.getString("organizationName")))
                    selectBuffer.append(" and A.organization_name like '%").append(entityRelatedObject.getString("organizationName")).append("%'");
                if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
                    selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
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

        resultModel = gpStationUntBll.getListBySQL(map);

        return resultModel;


    }

    @ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
    @RequestMapping(value = "/getListByOrganizationId/{organizationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getListByOrganizationId(@PathVariable("organizationId") String organizationId) {
        ResultModel resultModel = new ResultModel();

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer selectBuffer = new StringBuffer();
        selectBuffer.append("select A.id id,A.serial_no serialNo,A.organization_id organizationId,A.name name,A.responsibility responsibility,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_station A inner join gp_organization B on A.organization_id=B.id where 1=1 ");
        selectBuffer.append(" and A.organization_id='").append(organizationId).append("'");
        map.put("Sql", selectBuffer.toString());

        resultModel = gpStationUntBll.getListBySQL(map);

        return resultModel;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportExcel() {
        ResultModel resultModel = getListByJsonData();
        String fileName = "岗位列表数据" + DateUtils.getCurrentDateStr() + ".xls";
        String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
        JSONArray columnInfoList = new JSONArray();
        if (!StringUtils.isBlank(jsonData)) {
            JSONObject json = JSONObject.fromObject(jsonData);

            if (json.containsKey("columnInfo")) {
                columnInfoList = json.getJSONArray("columnInfo");
            }
        }

        if (resultModel != null) {
            try {
                exportExcel(fileName, columnInfoList, resultModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
