package com.zee.app.generate.swagger.gp;

import java.io.IOException;
import java.util.HashMap;
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

import com.zee.app.generate.swagger.base.BaseSwgApp;
import com.zee.bll.extend.split.gp.GpRegionCountrySplBll;
import com.zee.bll.extend.unity.gp.GpRegionCountryUntBll;
import com.zee.ent.extend.gp.GpRegionCountry;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.gp.GpRegionCountryParameter;
import com.zee.utl.DateUtils;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:00:55
 * @updateDate 2021/9/6 15:48:41
 * @description ������Ϣ�� ����ӿڣ���չ��BaseSwgApp���Զ����ɡ�
 */

@Api(value = "GpRegionCountry",tags="������Ϣ��")
@RequestMapping(value = "/generate/swagger/gp/gpRegionCountry")
public class GpRegionCountryGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("gpRegionCountryUntBll")
	protected GpRegionCountryUntBll gpRegionCountryUntBll;

	@Autowired
	@Qualifier("gpRegionCountrySplBll")
	protected GpRegionCountrySplBll gpRegionCountrySplBll;

	@ApiOperation(value = "������¼", notes = "����������¼")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ���", required = true, dataType = "GpRegionCountry") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpRegionCountry jsonData) {
		ResultModel result = gpRegionCountryUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "��������", notes = "����������¼")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ����������б�", required = true, dataType = "GpRegionCountryAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody GpRegionCountryParameter.AddList jsonData) {
		ResultModel result = gpRegionCountryUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "ɾ����¼", notes = "��������ɾ����Ӧ��¼")
	@ApiImplicitParam(paramType = "query", name = "id", value = "�û�ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = gpRegionCountryUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "ɾ����¼", notes = "��������ɾ����Ӧ��¼��·��ƴ��ģʽ")
	@ApiImplicitParam(paramType = "path", name = "id", value = "�û�ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = gpRegionCountryUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "����ɾ��", notes = "���������б�����ɾ����Ӧ��¼")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ����������б�", required = true, dataType = "GpRegionCountryDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpRegionCountryParameter.DeleteByIdList jsonData) {
		ResultModel result = gpRegionCountryUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "�޸ļ�¼", notes = "�޸�ָ����¼")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ�����ʵ������", required = true, dataType = "GpRegionCountry") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpRegionCountry jsonData) {
		ResultModel result = gpRegionCountryUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "�����޸�", notes = "ͬʱ�޸Ķ�����¼")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ����������б��Ҫ�޸�Ϊ����Ϣ����ʵ��", required = true, dataType = "GpRegionCountryUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpRegionCountryParameter.UpdateList jsonData) {
		ResultModel result = gpRegionCountryUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "�����޸�", notes = "ͬʱ�޸Ķ�����¼���������Ϊ��ֵͬ")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ����������б�", required = true, dataType = "GpRegionCountryAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody GpRegionCountryParameter.AddList jsonData) {
		ResultModel result = gpRegionCountryUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "�����޸�", notes = "ͬʱ�޸Ķ�����¼���������Ϊ��ֵͬ,���û�д�����¼��ִ������")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ����������б�", required = true, dataType = "GpRegionCountryAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody GpRegionCountryParameter.AddList jsonData) {
		ResultModel result = gpRegionCountryUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "������ѯ", notes = "����������ѯ��¼��ϸ��Ϣ")
	@ApiImplicitParam(paramType = "query", name = "id", value = "�û�ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = gpRegionCountryUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "������ѯ", notes = "����������ѯ��¼��ϸ��Ϣ,·��ƴ��ģʽ")
	@ApiImplicitParam(paramType = "path", name = "id", value = "�û�ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpRegionCountryUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "ģ����ѯ", notes = "���ݲ�ѯ����ģ����ѯ")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json�ַ�������ѯ����", required = true, dataType = "GpRegionCountryGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody GpRegionCountryParameter.GetList jsonData) {
		// ����ҵ���뷵������
		ResultModel result = gpRegionCountryUntBll.getList(jsonData);

		return result;
	}
    
	@ApiOperation(value = "ģ����ѯ", notes = "���ݲ�ѯ����ģ����ѯ")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.chinese_name chineseName,A.english_name englishName,A.alpha2 alpha2,A.alpha3 alpha3,A.numeric_key numericKey,A.iso iso,A.is_independent_code isIndependentCode,A.longitude longitude,A.latitude latitude,A.area area,A.is_display_code isDisplayCode,A.remark remark  from gp_region_country A inner join gp_region_country B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("chineseName") && StringUtils.isNotBlank(entityRelatedObject.getString("chineseName")))
					selectBuffer.append(" and A.chinese_name like '%").append(entityRelatedObject.getString("chineseName")).append("%'");
				if (entityRelatedObject.containsKey("englishName") && StringUtils.isNotBlank(entityRelatedObject.getString("englishName")))
					selectBuffer.append(" and A.english_name like '%").append(entityRelatedObject.getString("englishName")).append("%'");
				if (entityRelatedObject.containsKey("alpha2") && StringUtils.isNotBlank(entityRelatedObject.getString("alpha2")))
					selectBuffer.append(" and A.alpha2 like '%").append(entityRelatedObject.getString("alpha2")).append("%'");
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

		resultModel = gpRegionCountryUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "������Ϣ�б�����" + DateUtils.getCurrentDateStr() + ".xls";
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




