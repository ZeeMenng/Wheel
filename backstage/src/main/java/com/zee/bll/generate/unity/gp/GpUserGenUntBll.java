package com.zee.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.gp.IGpUserUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.parameter.gp.GpUserParameter;
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
 * @updateDate 2021/2/2 18:48:18
 * @description 系统用户。 业务逻辑处理类，扩展自BaseUntBll<GpUser>，自动生成。
 */
public class GpUserGenUntBll extends BaseUntBll<GpUser> {

	@Autowired
	protected IGpUserUntDal gpUserUntDal;


	public ResultModel updateList(GpUserParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GpUserParameter.UpdateList updateListParam, boolean isLog) {

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

	public ResultModel getList(GpUserParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GpUserParameter.GetList getListParam, boolean isLog) {
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
			

			GpUserParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GpUserParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GpUserParameter.GetList.Order> orderList = getListParam.getOrderList();

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

			List<GpUser> list = baseUntDal.getList(map);
			PageInfo<GpUser> pageInfo = new PageInfo<GpUser>(list);
			List<GpUser> modelList = pageInfo.getList();

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


	public ResultModel deleteByEmail(String email){
		return deleteByEmail( email, isLogRead);
	}

	public ResultModel deleteByEmail(String email, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(email);
			result.setIncomeCount(1);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETEBYUNIQUE.getCode());
			result.setOperTypeText(OperType.DELETEBYUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.deleteByEmail(email);

            result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    

	public ResultModel getModelByEmail(String email){
		return getModelByEmail( email, isLogRead);
	}

	public ResultModel getModelByEmail(String email, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(email);
			result.setIncomeCount(0);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODELBYUNIQUE.getCode());
			result.setOperTypeText(OperType.GETMODELBYUNIQUE.getText());
			result.setRemark("");

			GpUser t = gpUserUntDal.getModelByEmail(email);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODELBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else{
				result.setObjectId(t.getId());
				result.setReturnValue(JSONObject.fromObject(t).toString());
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODELBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    
   public ResultModel isUniqueEmail(String email){
		return isUniqueEmail(email, isLogRead);
	}

	public ResultModel isUniqueEmail(String email, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(email);
			result.setIncomeCount(0);
			result.setObjectId(null);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ISUNIQUE.getCode());
			result.setOperTypeText(OperType.ISUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.isUniqueEmail(email);

			result.setTotalCount(i);
            result.setReturnValue(String.valueOf(i));
			result.setResultCode(OperResult.ISUNIQUE_S.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i == 0){
                result.setData(true);
            }else{
                result.setData(false);
				
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ISUNIQUE_F.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

	public ResultModel deleteByPhone(String phone){
		return deleteByPhone( phone, isLogRead);
	}

	public ResultModel deleteByPhone(String phone, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(phone);
			result.setIncomeCount(1);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETEBYUNIQUE.getCode());
			result.setOperTypeText(OperType.DELETEBYUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.deleteByPhone(phone);

            result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    

	public ResultModel getModelByPhone(String phone){
		return getModelByPhone( phone, isLogRead);
	}

	public ResultModel getModelByPhone(String phone, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(phone);
			result.setIncomeCount(0);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODELBYUNIQUE.getCode());
			result.setOperTypeText(OperType.GETMODELBYUNIQUE.getText());
			result.setRemark("");

			GpUser t = gpUserUntDal.getModelByPhone(phone);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODELBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else{
				result.setObjectId(t.getId());
				result.setReturnValue(JSONObject.fromObject(t).toString());
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODELBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    
   public ResultModel isUniquePhone(String phone){
		return isUniquePhone(phone, isLogRead);
	}

	public ResultModel isUniquePhone(String phone, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(phone);
			result.setIncomeCount(0);
			result.setObjectId(null);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ISUNIQUE.getCode());
			result.setOperTypeText(OperType.ISUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.isUniquePhone(phone);

			result.setTotalCount(i);
            result.setReturnValue(String.valueOf(i));
			result.setResultCode(OperResult.ISUNIQUE_S.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i == 0){
                result.setData(true);
            }else{
                result.setData(false);
				
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ISUNIQUE_F.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

	public ResultModel deleteByQq(String qq){
		return deleteByQq( qq, isLogRead);
	}

	public ResultModel deleteByQq(String qq, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(qq);
			result.setIncomeCount(1);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETEBYUNIQUE.getCode());
			result.setOperTypeText(OperType.DELETEBYUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.deleteByQq(qq);

            result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    

	public ResultModel getModelByQq(String qq){
		return getModelByQq( qq, isLogRead);
	}

	public ResultModel getModelByQq(String qq, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(qq);
			result.setIncomeCount(0);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODELBYUNIQUE.getCode());
			result.setOperTypeText(OperType.GETMODELBYUNIQUE.getText());
			result.setRemark("");

			GpUser t = gpUserUntDal.getModelByQq(qq);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODELBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else{
				result.setObjectId(t.getId());
				result.setReturnValue(JSONObject.fromObject(t).toString());
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODELBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    
   public ResultModel isUniqueQq(String qq){
		return isUniqueQq(qq, isLogRead);
	}

	public ResultModel isUniqueQq(String qq, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(qq);
			result.setIncomeCount(0);
			result.setObjectId(null);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ISUNIQUE.getCode());
			result.setOperTypeText(OperType.ISUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.isUniqueQq(qq);

			result.setTotalCount(i);
            result.setReturnValue(String.valueOf(i));
			result.setResultCode(OperResult.ISUNIQUE_S.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i == 0){
                result.setData(true);
            }else{
                result.setData(false);
				
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ISUNIQUE_F.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

	public ResultModel deleteByUserName(String userName){
		return deleteByUserName( userName, isLogRead);
	}

	public ResultModel deleteByUserName(String userName, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userName);
			result.setIncomeCount(1);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETEBYUNIQUE.getCode());
			result.setOperTypeText(OperType.DELETEBYUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.deleteByUserName(userName);

            result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.DELETEBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    

	public ResultModel getModelByUserName(String userName){
		return getModelByUserName( userName, isLogRead);
	}

	public ResultModel getModelByUserName(String userName, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userName);
			result.setIncomeCount(0);
			
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODELBYUNIQUE.getCode());
			result.setOperTypeText(OperType.GETMODELBYUNIQUE.getText());
			result.setRemark("");

			GpUser t = gpUserUntDal.getModelByUserName(userName);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODELBYUNIQUE_S.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else{
				result.setObjectId(t.getId());
				result.setReturnValue(JSONObject.fromObject(t).toString());
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODELBYUNIQUE_F.getCode());
			result.setResultMessage(OperResult.GETMODELBYUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
    
   public ResultModel isUniqueUserName(String userName){
		return isUniqueUserName(userName, isLogRead);
	}

	public ResultModel isUniqueUserName(String userName, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userName);
			result.setIncomeCount(0);
			result.setObjectId(null);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ISUNIQUE.getCode());
			result.setOperTypeText(OperType.ISUNIQUE.getText());
			result.setRemark("");

			int i = gpUserUntDal.isUniqueUserName(userName);

			result.setTotalCount(i);
            result.setReturnValue(String.valueOf(i));
			result.setResultCode(OperResult.ISUNIQUE_S.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i == 0){
                result.setData(true);
            }else{
                result.setData(false);
				
            }
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ISUNIQUE_F.getCode());
			result.setResultMessage(OperResult.ISUNIQUE_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}

}





