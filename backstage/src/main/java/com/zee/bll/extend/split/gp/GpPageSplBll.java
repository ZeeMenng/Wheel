package com.zee.bll.extend.split.gp;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpPageGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpPage;
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
 * @description 系统页面。 业务逻辑处理类，扩展自BaseSplBll<GpPage>，可手动更改。
 */
@Service("gpPageSplBll")
public class GpPageSplBll extends GpPageGenSplBll {

	public ResultModel getModelByPageUrl(String domainId, String pageUrl) {
		return getModelByPageUrl(domainId, pageUrl, isLogRead);
	}

	public ResultModel getModelByPageUrl(String domainId, String pageUrl, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(pageUrl);
			result.setIncomeCount(1);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			GpPage page = gpPageSplDal.getModelByPageUrl(domainId, pageUrl);

			result.setData(page);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODEL_S.getCode());
			result.setResultMessage(OperResult.GETMODEL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (page == null)
				result.setTotalCount(0);
			else {
				result.setObjectId(page.getId());
				result.setReturnValue(JSONObject.fromObject(page).toString());
			}
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

}
