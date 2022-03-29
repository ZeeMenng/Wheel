package com.zee.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.gp.IGprDomainMessageUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GprDomainMessage;
import com.zee.ent.parameter.gp.GprDomainMessageParameter;
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
 * @updateDate 2022/3/29 18:24:20
 * @description 应用领域的站内信。 业务逻辑处理类，扩展自BaseUntBll<GprDomainMessage>，自动生成。
 */
public class GprDomainMessageGenUntBll extends BaseUntBll<GprDomainMessage> {

	@Autowired
	protected IGprDomainMessageUntDal gprDomainMessageUntDal;


	public ResultModel updateList(GprDomainMessageParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GprDomainMessageParameter.UpdateList updateListParam, boolean isLog) {

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

	public ResultModel getList(GprDomainMessageParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GprDomainMessageParameter.GetList getListParam, boolean isLog) {
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
			

			GprDomainMessageParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GprDomainMessageParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GprDomainMessageParameter.GetList.Order> orderList = getListParam.getOrderList();

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

			List<GprDomainMessage> list = baseUntDal.getList(map);
			PageInfo<GprDomainMessage> pageInfo = new PageInfo<GprDomainMessage>(list);
			List<GprDomainMessage> modelList = pageInfo.getList();

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
 
 	public ResultModel deleteByDomainId(String domainId) {
		return deleteByDomainId(domainId, isLogRead);
	}

	public ResultModel deleteByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setIncomeCount(0);
            result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprDomainMessageUntDal.deleteByDomainId( domainId);

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


 	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList) {
		return deleteByDomainIdList(domainIdList, isLogRead);
	}

	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject( domainIdList).toString());
			result.setIncomeCount(0);
            result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据外键列表，批量删除本表数据。");

			int i = gprDomainMessageUntDal.deleteByDomainIdList(domainIdList);

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


	public ResultModel getListByDomainId(String domainId) {
		return getListByDomainId(domainId, isLogRead);
	}

	public ResultModel getListByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
            result.setIncomeCount(0);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprDomainMessage> modelList = gprDomainMessageUntDal.getListByDomainId(domainId);

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
 
 	public ResultModel deleteByMessageId(String messageId) {
		return deleteByMessageId(messageId, isLogRead);
	}

	public ResultModel deleteByMessageId(String messageId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(messageId);
			result.setIncomeCount(0);
            result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprDomainMessageUntDal.deleteByMessageId( messageId);

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


 	public ResultModel deleteByMessageIdList(ArrayList<String> messageIdList) {
		return deleteByMessageIdList(messageIdList, isLogRead);
	}

	public ResultModel deleteByMessageIdList(ArrayList<String> messageIdList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject( messageIdList).toString());
			result.setIncomeCount(0);
            result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据外键列表，批量删除本表数据。");

			int i = gprDomainMessageUntDal.deleteByMessageIdList(messageIdList);

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


	public ResultModel getListByMessageId(String messageId) {
		return getListByMessageId(messageId, isLogRead);
	}

	public ResultModel getListByMessageId(String messageId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(messageId);
            result.setIncomeCount(0);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprDomainMessage> modelList = gprDomainMessageUntDal.getListByMessageId(messageId);

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
 
 	public ResultModel deleteByCompositeIdList(ArrayList<GprDomainMessage> gprDomainMessageList) {
		return deleteByCompositeIdList(gprDomainMessageList, isLogWrite);
	}

	public ResultModel deleteByCompositeIdList(ArrayList<GprDomainMessage> gprDomainMessageList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (gprDomainMessageList == null || gprDomainMessageList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gprDomainMessageList).toString());
			result.setIncomeCount(0);
            result.setAddTime(DateUtils.getCurrentTime());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = gprDomainMessageUntDal.deleteByCompositeIdList(gprDomainMessageList);

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
   


}





