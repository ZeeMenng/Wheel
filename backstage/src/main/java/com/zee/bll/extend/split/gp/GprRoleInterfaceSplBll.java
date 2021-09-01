package com.zee.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GprRoleInterfaceGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GprRoleInterface;
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
 * @description 角色拥有的接口权限。 业务逻辑处理类，扩展自BaseSplBll<GprRoleInterface>，可手动更改。
 */
@Service("gprRoleInterfaceSplBll")
public class GprRoleInterfaceSplBll extends GprRoleInterfaceGenSplBll {


	public ResultModel deleteByCompositeIdList(ArrayList<GprRoleInterface> gprRoleInterfaceList) {
		return deleteByCompositeIdList(gprRoleInterfaceList, isLogWrite);
	}

	public ResultModel deleteByCompositeIdList(ArrayList<GprRoleInterface> gprRoleInterfaceList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (gprRoleInterfaceList == null || gprRoleInterfaceList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gprRoleInterfaceList).toString());
			result.setAddTime(DateUtils.getCurrentTime());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = gprRoleInterfaceSplDal.deleteByCompositeIdList(gprRoleInterfaceList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i != gprRoleInterfaceList.size()) {
				result.setResultMessage(OperResult.DELETELIST_S.getText() + "要删除的记录中有些已被删除。");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETELIST_F.getCode());
			result.setResultMessage(OperResult.DELETELIST_F.getText() );
			result.setReturnValue(e.getMessage());result.setOriginException(e);
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





