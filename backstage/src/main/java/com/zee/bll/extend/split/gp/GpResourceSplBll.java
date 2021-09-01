package com.zee.bll.extend.split.gp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpResourceGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpResource;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.Tools;

import net.sf.json.JSONArray;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2018/5/7 15:00:36
 * @description 通用文件信息存储表。 业务逻辑处理类，扩展自BaseSplBll<GpResource>，可手动更改。
 */
@Service("gpResourceSplBll")
public class GpResourceSplBll extends GpResourceGenSplBll {

	public ResultModel delete(String id) {
		return delete(id, isLogWrite);
	}

	public ResultModel delete(String id, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(id);
			result.setIncomeCount(0);
			result.setObjectId(id);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据主键，删除上传附件记录，同时级联删除上传附件相关表的记录。");

			int i = gpResourceSplDal.delete(id);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETE_F.getCode());
			result.setResultMessage(OperResult.DELETE_F.getText());
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
		return deleteByDomainId(domainId, isLogWrite);
	}

	public ResultModel deleteByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setIncomeCount(0);
			result.setObjectId(domainId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据应用领域主键，删除上传附件记录，同时级联删除上传附件相关表的记录。");

			int i = gpResourceSplDal.deleteByDomainId(domainId);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETE_F.getCode());
			result.setResultMessage(OperResult.DELETE_F.getText());
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

	public ResultModel deleteByIdList(ArrayList<String> idList) {
		return deleteByIdList(idList, isLogWrite);
	}

	public ResultModel deleteByIdList(ArrayList<String> idList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(idList).toString());
			result.setIncomeCount(idList.size());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据主键列表，删除上传附件记录，同时级联删除上传附件相关表的记录。");

			int i = gpResourceSplDal.deleteByIdList(idList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setIncomeCount(i);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETELIST_F.getCode());
			result.setResultMessage(OperResult.DELETELIST_F.getText());
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
		return deleteByDomainIdList(domainIdList, isLogWrite);
	}

	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(domainIdList).toString());
			result.setIncomeCount(0);
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据应用领域主键列表，删除上传附件记录，同时级联删除上传附件相关表的记录。");

			int i = gpResourceSplDal.deleteByDomainIdList(domainIdList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETELIST_F.getCode());
			result.setResultMessage(OperResult.DELETELIST_F.getText());
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

	public ResultModel getListByBusinessId(String businessId) {
		return getListByBusinessId(businessId, isLogRead);
	}

	public ResultModel getListByBusinessId(String businessId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(businessId);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLIST.getCode());
			result.setOperTypeText(OperType.GETLIST.getText());
			result.setRemark("");

			List<GpResource> grResourceList = gpResourceSplDal.getListByBusinessId(businessId);

			result.setReturnValue(JSONArray.fromObject(grResourceList).toString());
			result.setData(grResourceList);
			result.setTotalCount(grResourceList.size());
			result.setResultCode(OperResult.GETLISTBYSQL_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (grResourceList.isEmpty()) {
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
				result.setResultMessage(OperResult.GETLISTBYSQL_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			e.printStackTrace();
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

}
