package com.zee.bll.extend.split.gp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpModuleGenSplBll;
import com.zee.dao.split.gp.IGpMenuSplDal;
import com.zee.dao.split.gp.IGprModulePageSplDal;
import com.zee.dao.split.gp.IGprRoleModuleSplDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpModule;
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
 * @description 功能模块。 业务逻辑处理类，扩展自BaseSplBll<GpModule>，可手动更改。
 */
@Service("gpModuleSplBll")
public class GpModuleSplBll extends GpModuleGenSplBll {

	@Autowired
	protected IGprModulePageSplDal gprModulePageSplDal;

	@Autowired
	protected IGprRoleModuleSplDal gprRoleModuleSplDal;

	@Autowired
	protected IGpMenuSplDal gpMenuSplDal;

	public ResultModel getListByRoleId(String roleId) {
		return getListByRoleId(roleId, isLogRead);
	}

	public ResultModel getListByRoleId(String roleId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(roleId);
			result.setObjectId(roleId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据角色ID，查询相应功能模块。");

			List<GpModule> modelList = gpModuleSplDal.getListByRoleId(roleId);

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

	public ResultModel updateDomainModules(ArrayList<GpModule> gpModuleList) {
		return updateDomainModules(gpModuleList, isLogWrite);
	}

	public ResultModel updateDomainModules(ArrayList<GpModule> gpModuleList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setId(Tools.getUUID());
			result.setAddTime(DateUtils.getCurrentTime());
			result.setIncomeValue(JSONArray.fromObject(gpModuleList).toString());
			result.setIncomeCount(gpModuleList.size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELIST.getCode());
			result.setOperTypeText(OperType.UPDATELIST.getText());
			result.setRemark("修改某个应用领域下的所有功能模块。");

			// 给功能模块的添加时间都赋值为当前时间，此处尚有问题
			for (GpModule gpModule : gpModuleList) {
				if (gpModule.getAddTime() == null)
					gpModule.setAddTime(DateUtils.getCurrentTime());
				if (gpModule.getUpdateTime() == null)
					gpModule.setUpdateTime(DateUtils.getCurrentTime());
			}

			int i = 0;
			int j = 0;

			if (gpModuleList.size() > 0) {
				j = gpModuleSplDal.deleteInvalidDomainModules(gpModuleList.get(0).getDomainId(), gpModuleList);
				i = gpModuleSplDal.updateDomainModules(gpModuleList);
			}
			result.setReturnValue(String.valueOf(i + j));
			result.setData(i + j);
			result.setTotalCount(new Long(i + j));
			result.setResultCode(OperResult.UPDATELIST_S.getCode());
			result.setResultMessage(OperResult.UPDATELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELIST_F.getCode());
			result.setResultMessage(OperResult.UPDATELIST_F.getText());
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
