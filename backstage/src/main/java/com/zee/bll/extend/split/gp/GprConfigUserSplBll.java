package com.zee.bll.extend.split.gp;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GprConfigUserGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GprConfigUser;
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
 * @updateDate 2021/1/19 11:24:03
 * @description 用户配置信息。 业务逻辑处理类，扩展自BaseSplBll<GprConfigUser>，可手动更改。
 */
@Service("gprConfigUserSplBll")
public class GprConfigUserSplBll extends GprConfigUserGenSplBll {

	public ResultModel updateByCompositeId(GprConfigUser gprConfigUser) {
		return updateByCompositeId(gprConfigUser, isLogWrite);
	}

	public ResultModel updateByCompositeId(GprConfigUser gprConfigUser, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(gprConfigUser);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setIncomeCount(1);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("");
			gprConfigUser.setUpdateTime(DateUtils.getCurrentTime());

			int i = gprConfigUserSplDal.updateByCompositeId(gprConfigUser);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATE_S.getCode());
			result.setResultMessage(OperResult.UPDATE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

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
