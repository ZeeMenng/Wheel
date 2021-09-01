package com.zee.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.gp.IGpMessageUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpMessage;
import com.zee.ent.parameter.gp.GpMessageParameter;
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
 * @updateDate 2021/2/2 18:48:15
 * @description 系统消息。 业务逻辑处理类，扩展自BaseUntBll<GpMessage>，自动生成。
 */
public class GpMessageGenUntBll extends BaseUntBll<GpMessage> {

	@Autowired
	protected IGpMessageUntDal gpMessageUntDal;


	public ResultModel updateList(GpMessageParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GpMessageParameter.UpdateList updateListParam, boolean isLog) {

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

	public ResultModel getList(GpMessageParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GpMessageParameter.GetList getListParam, boolean isLog) {
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
			

			GpMessageParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GpMessageParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GpMessageParameter.GetList.Order> orderList = getListParam.getOrderList();

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

			List<GpMessage> list = baseUntDal.getList(map);
			PageInfo<GpMessage> pageInfo = new PageInfo<GpMessage>(list);
			List<GpMessage> modelList = pageInfo.getList();

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
 
 	public ResultModel deleteByUserId(String userId) {
		return deleteByUserId(userId, isLogRead);
	}

	public ResultModel deleteByUserId(String userId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
			result.setIncomeCount(0);
            result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gpMessageUntDal.deleteByUserId( userId);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETE_F.getCode());
			result.setResultMessage(OperResult.DELETE_F.getText() );
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


 	public ResultModel deleteByUserIdList(ArrayList<String> userIdList) {
		return deleteByUserIdList(userIdList, isLogRead);
	}

	public ResultModel deleteByUserIdList(ArrayList<String> userIdList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject( userIdList).toString());
			result.setIncomeCount(0);
            result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据外键列表，批量删除本表数据。");

			int i = gpMessageUntDal.deleteByUserIdList(userIdList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETELIST_F.getCode());
			result.setResultMessage(OperResult.DELETELIST_F.getText() );
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


	public ResultModel getListByUserId(String userId) {
		return getListByUserId(userId, isLogRead);
	}

	public ResultModel getListByUserId(String userId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
            result.setIncomeCount(0);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GpMessage> modelList = gpMessageUntDal.getListByUserId(userId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_F.getText() );
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


}





