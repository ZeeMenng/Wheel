package com.zee.bll.extend.split.gp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpLoginLogGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpLoginLog;
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
 * @updateDate 2017/12/15 23:41:48
 * @description 登录日志。 业务逻辑处理类，扩展自BaseSplBll<GpLoginLog>，可手动更改。
 */
@Service("gpLoginLogSplBll")
public class GpLoginLogSplBll extends GpLoginLogGenSplBll {

	public ResultModel getListOnLine(String userName, String domainId) {
		return getListOnLine(userName, domainId, isLogRead);
	}

	public ResultModel getListOnLine(String userName, String domainId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userName);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			List<GpLoginLog> modelList = gpLoginLogSplDal.getListOnLine(userName, domainId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLIST_F.getCode());
			result.setResultMessage(OperResult.GETLIST_F.getText());
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
