package com.zee.bll.extend.split.gp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpUserGenSplBll;
import com.zee.dao.unity.gp.IGpUserUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.Tools;

import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2017/12/15 23:41:48
 * @description 系统用户。 业务逻辑处理类，扩展自BaseSplBll<GpUser>，可手动更改。
 */
@Service("gpUserSplBll")
public class GpUserSplBll extends GpUserGenSplBll {

	@Autowired
	protected IGpUserUntDal gpUserUntDal;

	public ResultModel getModelByUserName(String userName) {
		return getModelByUserName(userName, isLogRead);
	}

	public ResultModel getModelByUserName(String userName, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userName);
			result.setIncomeCount(1);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			GpUser user = gpUserSplDal.getModelByUserName(userName);

			result.setData(user);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODEL_S.getCode());
			result.setResultMessage(OperResult.GETMODEL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (user == null)
				result.setTotalCount(0);
			else
				result.setReturnValue(JSONObject.fromObject(user).toString());
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText());
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

	public ResultModel resetPassword(String userId) {
		return resetPassword(userId, isLogRead);
	}

	public ResultModel resetPassword(String userId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
			result.setObjectId(userId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("重置用户密码。");
			GpUser user = new GpUser();
			user.setId(userId);
			user.setUpdateTime(DateUtils.getCurrentTime());
			user.setPassword(CustomSymbolic.INITIAL_PASSWORD);
			int i = gpUserUntDal.update(user);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATE_S.getCode());
			result.setResultMessage(OperResult.UPDATE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setResultCode(OperResult.UPDATE_F.getCode());
				result.setResultMessage(OperResult.UPDATE_F.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATE_F.getCode());
			result.setResultMessage(OperResult.UPDATE_F.getText());
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

	public ResultModel disableUser(String userId) {
		return disableUser(userId, isLogRead);
	}

	public ResultModel disableUser(String userId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
			result.setObjectId(userId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("禁止用户登录。");
			GpUser user = new GpUser();
			user.setId(userId);
			user.setUpdateTime(DateUtils.getCurrentTime());
			user.setIsDisabledCode(CustomSymbolic.DCODE_BOOLEAN_T);
			int i = gpUserUntDal.update(user);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATE_S.getCode());
			result.setResultMessage(OperResult.UPDATE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setResultCode(OperResult.UPDATE_F.getCode());
				result.setResultMessage(OperResult.UPDATE_F.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATE_F.getCode());
			result.setResultMessage(OperResult.UPDATE_F.getText());
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
