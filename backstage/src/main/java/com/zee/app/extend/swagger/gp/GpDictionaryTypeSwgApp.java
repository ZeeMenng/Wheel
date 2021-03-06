package com.zee.app.extend.swagger.gp;

import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.ent.extend.gp.GpDictionaryType;
import com.zee.ent.parameter.base.BaseParameter;
import com.zee.ent.parameter.gp.GpDictionaryParameter;
import com.zee.set.symbolic.SqlSymbolic;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.zee.utl.CastObjectUtil;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zee.app.generate.swagger.gp.GpDictionaryTypeGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.gp.GpDictionaryTypeParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 字典类型。 对外接口，扩展自GpDictionaryTypeGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpDictionaryType")
public class GpDictionaryTypeSwgApp extends GpDictionaryTypeGenSwgApp {

    @Autowired
    @Qualifier("gpDictionaryUntBll")
    protected GpDictionaryUntBll gpDictionaryUntBll;

    @ApiOperation(value = "新增记录", notes = "新增单条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpDictionaryType")})
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel add(@RequestBody GpDictionaryType jsonData) {

        ResultModel result = gpDictionaryTypeUntBll.add(jsonData);
        if (jsonData.getDictionaryList() != null) {
            ArrayList<GpDictionary> dictionaryArrayList = new ArrayList<GpDictionary>();
            for (GpDictionary dictionary : jsonData.getDictionaryList()) {
                dictionary.setTypeId(result.getObjectId());
                dictionaryArrayList.add(dictionary);
            }
            gpDictionaryUntBll.add(dictionaryArrayList);
        }

        return result;
    }



    @ApiOperation(value = "修改记录", notes = "修改指定记录")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpDictionaryType") })
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel update(@RequestBody GpDictionaryType jsonData) {
        ResultModel result = gpDictionaryTypeUntBll.update(jsonData);
        gpDictionaryUntBll.deleteByTypeId(jsonData.getId());
        if (jsonData.getDictionaryList() != null) {
            ArrayList<GpDictionary> dictionaryArrayList = new ArrayList<GpDictionary>();
            for (GpDictionary dictionary : jsonData.getDictionaryList()) {
                dictionary.setTypeId(result.getObjectId());
                dictionaryArrayList.add(dictionary);
            }
            gpDictionaryUntBll.add(dictionaryArrayList);
        }

        return result;
    }



    @ApiOperation(value = "批量修改", notes = "同时修改多条记录")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpDictionaryTypeUpdateList")})
    @RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateList(@RequestBody GpDictionaryTypeParameter.UpdateList jsonData) {

        ResultModel result = new ResultModel();
        if ((jsonData.getEntity().getName().equals("") || jsonData.getEntity().getName() == null)
                && (jsonData.getEntity().getConstantName().equals("") || jsonData.getEntity().getConstantName() == null)
                && (jsonData.getEntity().getRemark().equals("") || jsonData.getEntity().getRemark() == null)
        ) {
            result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
            return result;
        } else {
            result = gpDictionaryTypeUntBll.updateList(jsonData);
            return result;
        }
    }

    @ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel getModelByPath(@PathVariable("id") String id) {
        ResultModel dictionaryTypeResult = gpDictionaryTypeUntBll.getModel(id);
        GpDictionaryType dictionaryType = CastObjectUtil.cast(dictionaryTypeResult.getData());

        GpDictionaryParameter.GetList jsonData = new GpDictionaryParameter.GetList();

        GpDictionaryParameter.GetList.EntityRelated entityRelated = new GpDictionaryParameter.GetList.EntityRelated();
        BaseParameter.BaseParamGetList.Order order = new BaseParameter.BaseParamGetList.Order();
        BaseParameter.BaseParamGetList.Order order1 = new BaseParameter.BaseParamGetList.Order();

        entityRelated.setTypeId(id);
        order.setColumnName("priority");
        order.setIsASC(true);

        order1.setColumnName("code");
        order1.setIsASC(true);

        jsonData.setEntityRelated(entityRelated);
        jsonData.setOrderList(new ArrayList<BaseParameter.BaseParamGetList.Order>() {
            {
                add(order);
                add(order1);
            }
        });

        ResultModel dictionaryResult = gpDictionaryUntBll.getList(jsonData);
        if(dictionaryResult.getTotalCount()!=0) {
            ArrayList<GpDictionary> dictionaryList = CastObjectUtil.cast(dictionaryResult.getData());
            dictionaryType.setDictionaryList(dictionaryList);
            dictionaryTypeResult.setData(dictionaryType);
        }
        return dictionaryTypeResult;
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
        selectBuffer.append(SqlSymbolic.SQL_SELECT_DICTIONARYTYPE_LIST);
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
                    selectBuffer.append(String.format(" and( A.name like %1$s or A.constant_name like %1$s)", "'%" + entityRelatedObject.getString("keywords") + "%'"));
                }
                if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
                    selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
                if (entityRelatedObject.containsKey("constantName") && StringUtils.isNotBlank(entityRelatedObject.getString("constantName")))
                    selectBuffer.append(" and A.constant_name like '%").append(entityRelatedObject.getString("constantName")).append("%'");
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

        resultModel = gpDictionaryTypeUntBll.getListBySQL(map);

        return resultModel;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportExcel() {
        ResultModel resultModel = getListByJsonData();
        String fileName = "字典信息列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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



