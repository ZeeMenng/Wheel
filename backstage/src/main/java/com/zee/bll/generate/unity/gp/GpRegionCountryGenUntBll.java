package com.zee.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.gp.IGpRegionCountryUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpRegionCountry;
import com.zee.ent.parameter.gp.GpRegionCountryParameter;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.utl.DateUtils;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/9/8 9:50:20
 * @description 地区信息。 业务逻辑处理类，扩展自BaseUntBll<GpRegionCountry>，自动生成。
 */
public class GpRegionCountryGenUntBll extends BaseUntBll<GpRegionCountry> {

	@Autowired
	protected IGpRegionCountryUntDal gpRegionCountryUntDal;


	public ResultModel updateList(GpRegionCountryParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GpRegionCountryParameter.UpdateList updateListParam, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(updateListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
            result.setIncomeCount(updateListParam.getIdList().size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELIST.getCode());
			result.setOperTypeText(OperType.UPDATELIST.getText());
			result.setRemark("");

			int i = baseUntDal.updateList(updateListParam.getIdList(),updateListParam.getEntity());

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELIST_S.getCode());
			result.setResultMessage(OperResult.UPDATELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATE_F.getCode());
			result.setResultMessage(OperResult.UPDATE_F.getText() );
			result.setReturnValue(e.getMessage());
            result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
		}

		return result;
	}

	public ResultModel getList(GpRegionCountryParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GpRegionCountryParameter.GetList getListParam, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			JSONObject jsonObject = JSONObject.fromObject(getListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
            result.setIncomeCount(0);
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLIST.getCode());
            result.setOperTypeText(OperType.GETLIST.getText());
			result.setRemark("");
			

			GpRegionCountryParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GpRegionCountryParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GpRegionCountryParameter.GetList.Order> orderList = getListParam.getOrderList();

			Map<String, Object> map = new HashMap<String, Object>();
			if (entityRelated != null)
				map.put("EntityRelated", entityRelated);
			if (page != null) {
				map.put("Page", page);
			}
			if (orderList != null) {
				map.put("OrderList", orderList);
			}

			PageHelper.startPage(page.getPageIndex(), page.getPageSize());

			List<GpRegionCountry> list = baseUntDal.getList(map);
			PageInfo<GpRegionCountry> pageInfo = new PageInfo<GpRegionCountry>(list);
			List<GpRegionCountry> modelList = pageInfo.getList();

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(pageInfo.getTotal());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLIST_F.getCode());
			result.setResultMessage(OperResult.GETLIST_F.getText() );
			result.setReturnValue(e.getMessage());
            result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}


	public ResultModel deleteByIso(String iso){
		return deleteByIso( iso, isLogRead);
	}

	public ResultModel deleteByIso(String iso, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(iso);
			result.setIncomeCount(1);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETEBYUNIQUE.getCode());
			result.setOperTypeText(OperType.DELETEBYUNIQUE.getText());
			result.setRemark("");

			int i = gpRegionCountryUntDal.deleteByIso(iso);

            result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    

	public ResultModel getModelByIso(String iso){
		return getModelByIso( iso, isLogRead);
	}

	public ResultModel getModelByIso(String iso, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(iso);
			result.setIncomeCount(0);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODELBYUNIQUE.getCode());
			result.setOperTypeText(OperType.GETMODELBYUNIQUE.getText());
			result.setRemark("");

			GpRegionCountry t = gpRegionCountryUntDal.getModelByIso(iso);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODELBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else{
				result.setObjectId(t.getId());
				result.setReturnValue(JSONObject.fromObject(t).toString());
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODELBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    
   public ResultModel isUniqueIso(String iso){
		return isUniqueIso(iso, isLogRead);
	}

	public ResultModel isUniqueIso(String iso, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(iso);
			result.setIncomeCount(0);
			result.setObjectId(null);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ISUNIQUE.getCode());
			result.setOperTypeText(OperType.ISUNIQUE.getText());
			result.setRemark("");

			int i = gpRegionCountryUntDal.isUniqueIso(iso);

			result.setTotalCount(i);
            result.setReturnValue(String.valueOf(i));
			result.setResultCode(OperResult.ISUNIQUE_S.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i == 0){
                result.setData(true);
            }else{
                result.setData(false);
				
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ISUNIQUE_F.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

}





