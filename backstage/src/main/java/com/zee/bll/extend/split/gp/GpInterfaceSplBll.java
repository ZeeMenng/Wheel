package com.zee.bll.extend.split.gp;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpInterfaceGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpInterface;
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
 * @description 系统接口。 业务逻辑处理类，扩展自BaseSplBll<GpInterface>，可手动更改。
 */
@Service("gpInterfaceSplBll")
public class GpInterfaceSplBll extends GpInterfaceGenSplBll {

	public ResultModel getModelByUrl(String url) {
		return getModelByUrl(url, isLogRead);
	}

	public ResultModel getModelByUrl(String url, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(url);
			result.setIncomeCount(0);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			GpInterface gpInterface = gpInterfaceSplDal.getModelByUrl(url);

			result.setResultCode(OperResult.GETMODEL_S.getCode());
			result.setResultMessage(OperResult.GETMODEL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			result.setData(gpInterface);
			result.setTotalCount(new Long(1));
			if (gpInterface == null) {
				result.setTotalCount(0);
			} else {
				result.setObjectId(gpInterface.getId());
				result.setReturnValue(JSONObject.fromObject(gpInterface).toString());
			}

		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText() );
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

	public ResultModel isPermitted(String userId, String url) {
		return isPermitted(userId, url, isLogRead);
	}

	public ResultModel isPermitted(String userId, String url, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(url);
			result.setIncomeCount(0);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			int i = gpInterfaceSplDal.isPermitted(userId, url);
			if (i == 0) {
				result.setResultCode(OperResult.CUSTOM_S.getCode());
				result.setResultMessage(OperResult.CUSTOM_S.getText());
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			} else {
				result.setReturnValue(String.valueOf(i));
				result.setData(i);
				result.setTotalCount(new Long(1));
				result.setResultCode(OperResult.CUSTOM_S.getCode());
				result.setResultMessage(OperResult.CUSTOM_S.getText());
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.CUSTOM_F.getCode());
			result.setResultMessage(OperResult.CUSTOM_F.getText());
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
