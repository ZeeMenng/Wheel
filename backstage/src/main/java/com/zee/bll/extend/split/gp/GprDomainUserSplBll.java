package com.zee.bll.extend.split.gp;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GprDomainUserGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.Tools;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2017/12/15 23:41:48
 * @description 应用领域拥有的用户。 业务逻辑处理类，扩展自BaseSplBll<GprDomainUser>，可手动更改。
 */
@Service("gprDomainUserSplBll")
public class GprDomainUserSplBll extends GprDomainUserGenSplBll {

	public ResultModel isPermitted(String userId, String clientId) {
		return isPermitted(userId, clientId, isLogRead);
	}

	public ResultModel isPermitted(String userId, String clientId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(clientId);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("根据用户ID和应用领域ID，查询此用户用无此应用领域的访问权限");

			int i = gprDomainUserSplDal.isPermitted(userId, clientId);
			if (i == 0) {
				result.setResultCode(OperResult.CUSTOM_S.getCode());
				result.setResultMessage(OperResult.CUSTOM_S.getText() + "：没有此系统的访问权限！");
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
