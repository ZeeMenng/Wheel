package com.zee.bll.generate.unity.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.base.BaseBll;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.ClassUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.PageHelperUtil;
import com.zee.utl.SnowFlakeSerialNoWorkerUtl;
import com.zee.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseUntBll<T extends Serializable> extends BaseBll {

	@Autowired
	protected IBaseUntDal<T> baseUntDal;

	SnowFlakeSerialNoWorkerUtl snowFlake=new SnowFlakeSerialNoWorkerUtl(CustomSymbolic.SNOWFLAKE_SERIAL_NO_DATACENTER_ID, CustomSymbolic.SNOWFLAKE_SERIAL_NO_WORKDER_ID);
	// @Autowired
	// private RedisUtil redisUtil;

	public ResultModel add(T t) {
		return add(t, isLogWrite);
	}

	public ResultModel add(T t, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(t);
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setIncomeCount(1);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADD.getCode());
			result.setOperTypeText(OperType.ADD.getText());
			result.setRemark("");

			// 给主键统一赋值
			Class<?> cla = t.getClass().getSuperclass();
			String tId = Tools.getUUID();
			Field idField = cla.getDeclaredField(CustomSymbolic.TABLE_ID);
			idField.setAccessible(true);
			Object idObject = idField.get(t);
			if (idObject == null || StringUtils.isEmpty(idObject.toString())) {
				idField.set(t, tId);
				result.setObjectId(tId);
			} else {
				result.setObjectId(idObject.toString());
			}

			// 给编号统一赋值
			if (ClassUtil.isExistFieldName(cla, CustomSymbolic.TABLE_SERIAL_NO)) {
				Field serialNoField = cla.getDeclaredField(CustomSymbolic.TABLE_SERIAL_NO);
				serialNoField.setAccessible(true);
				Object serialNoObject = serialNoField.get(t);
				if (serialNoObject == null || StringUtils.isEmpty(serialNoObject.toString())) {
					String tSerialNo = String.valueOf(snowFlake.nextId());
					serialNoField.set(t, tSerialNo);
				}
			}

			// 给新增时间统一赋值
			if (ClassUtil.isExistFieldName(cla, CustomSymbolic.ADD_TIME)) {
				Field addTimeField = cla.getDeclaredField(CustomSymbolic.ADD_TIME);
				addTimeField.setAccessible(true);
				addTimeField.set(t, DateUtils.getCurrentTime());
			}
			// 给修改时间统一赋值
			if (ClassUtil.isExistFieldName(cla, CustomSymbolic.UPDATE_TIME)) {
				Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
				updateTimeField.setAccessible(true);
				updateTimeField.set(t, DateUtils.getCurrentTime());
			}

			int i = baseUntDal.add(t);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.ADD_S.getCode());
			result.setResultMessage(OperResult.ADD_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel add(ArrayList<T> tList) {
		return add(tList, isLogWrite);
	}

	public ResultModel add(ArrayList<T> tList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setIncomeCount(JSONArray.fromObject(tList).size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADDLIST.getCode());
			result.setOperTypeText(OperType.ADDLIST.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();

				// 给主键统一赋值
				String tId = Tools.getUUID();
				Field idField = cla.getDeclaredField(CustomSymbolic.TABLE_ID);
				idField.setAccessible(true);
				Object idObject = idField.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString()))
					idField.set(t, tId);

				// 给编号统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.TABLE_SERIAL_NO)) {
					Field serialNoField = cla.getDeclaredField(CustomSymbolic.TABLE_SERIAL_NO);
					serialNoField.setAccessible(true);
					Object serialNoObject = serialNoField.get(t);
					if (serialNoObject == null || StringUtils.isEmpty(serialNoObject.toString())) {
						String tSerialNo = String.valueOf(snowFlake.nextId());
						serialNoField.set(t, tSerialNo);
					}
				}
				// 给新增时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.ADD_TIME)) {
					Field addTimeField = cla.getDeclaredField(CustomSymbolic.ADD_TIME);
					addTimeField.setAccessible(true);
					addTimeField.set(t, DateUtils.getCurrentTime());
				}
				// 给修改时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.UPDATE_TIME)) {
					Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
					updateTimeField.setAccessible(true);
					updateTimeField.set(t, DateUtils.getCurrentTime());
				}

			}
			int i = baseUntDal.addList(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.ADDLIST_S.getCode());
			result.setResultMessage(OperResult.ADDLIST_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel addListWithDffOrAdd(ArrayList<T> tList) {
		return addListWithDffOrAdd(tList, isLogWrite);
	}

	public ResultModel addListWithDffOrAdd(ArrayList<T> tList, boolean isLog) {

		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {

			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setIncomeCount(JSONArray.fromObject(tList).size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADDLISTWIDTHDFFORADD.getCode());
			result.setOperTypeText(OperType.ADDLISTWIDTHDFFORADD.getText());
			result.setRemark("");
			
			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				String tId = Tools.getUUID();
				Field field = cla.getDeclaredField(CustomSymbolic.TABLE_ID);
				field.setAccessible(true);
				Object idObject = field.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString()))
					field.set(t, tId);
				// 给编号统一赋值（如果之前值为空 的话）
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.TABLE_SERIAL_NO)) {
					Field serialNoField = cla.getDeclaredField(CustomSymbolic.TABLE_SERIAL_NO);
					serialNoField.setAccessible(true);
					Object serialNoObject = serialNoField.get(t);
					if (serialNoObject == null || StringUtils.isEmpty(serialNoObject.toString())) {
						String tSerialNo = String.valueOf(snowFlake.nextId());
						serialNoField.set(t, tSerialNo);
					}
				}
				// 给修改时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.UPDATE_TIME)) {
					Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
					updateTimeField.setAccessible(true);
					updateTimeField.set(t, DateUtils.getCurrentTime());
				}
			}

			int i = baseUntDal.addListWithDffOrAdd(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.ADDLISTWIDTHDFFORADD_S.getCode());
			result.setResultMessage(OperResult.ADDLISTWIDTHDFFORADD_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.ADDLISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.ADDLISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.ADDLISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel delete(String id) {
		return delete(id, isLogWrite);
	}

	public ResultModel delete(String id, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(id);
			result.setIncomeCount(1);
			result.setObjectId(id);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("");

			int i = baseUntDal.delete(id);

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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel deleteByIdList(ArrayList<String> idList) {
		return deleteByIdList(idList, isLogWrite);
	}

	public ResultModel deleteByIdList(ArrayList<String> idList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (idList == null || idList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(idList).toString());
			result.setIncomeCount(JSONArray.fromObject(idList).size());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = baseUntDal.deleteByIdList(idList);

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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel update(T t) {
		return update(t, isLogWrite);
	}

	public ResultModel update(T t, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(t);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setIncomeCount(1);
			if (jsonObject.containsKey("id"))
				result.setObjectId(jsonObject.getString("id"));
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("");

			Class<?> cla = t.getClass().getSuperclass();

			// 给修改时间统一赋值
			if (ClassUtil.isExistFieldName(cla, CustomSymbolic.UPDATE_TIME)) {
				Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
				updateTimeField.setAccessible(true);
				updateTimeField.set(t, DateUtils.getCurrentTime());
			}

			int i = baseUntDal.update(t);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel updateListWithDff(ArrayList<T> tList) {
		return updateListWithDff(tList, isLogWrite);
	}

	public ResultModel updateListWithDff(ArrayList<T> tList, boolean isLog) {

		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setIncomeCount(JSONArray.fromObject(tList).size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELISTWIDTHDFF.getCode());
			result.setOperTypeText(OperType.UPDATELISTWIDTHDFF.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				Field field = cla.getDeclaredField(CustomSymbolic.TABLE_ID);
				field.setAccessible(true);
				Object idObject = field.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString())) {
					GlobalException globalException = new GlobalException("主键不能为空！");
					globalException.setResultModel(result);
					throw globalException;
				}

				// 给修改时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.UPDATE_TIME)) {
					Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
					updateTimeField.setAccessible(true);
					updateTimeField.set(t, DateUtils.getCurrentTime());
				}

			}

			int i = baseUntDal.updateListWithDff(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_S.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel updateListWithDffOrAdd(ArrayList<T> tList) {
		return updateListWithDffOrAdd(tList, isLogWrite);
	}

	public ResultModel updateListWithDffOrAdd(ArrayList<T> tList, boolean isLog) {

		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setIncomeCount(JSONArray.fromObject(tList).size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELISTWIDTHDFFORADD.getCode());
			result.setOperTypeText(OperType.UPDATELISTWIDTHDFFORADD.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				String tId = Tools.getUUID();
				Field field = cla.getDeclaredField(CustomSymbolic.TABLE_ID);
				field.setAccessible(true);
				Object idObject = field.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString()))
					field.set(t, tId);
				// 给新增时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.ADD_TIME)) {
					Field addTimeField = cla.getDeclaredField(CustomSymbolic.ADD_TIME);
					addTimeField.setAccessible(true);
					addTimeField.set(t, DateUtils.getCurrentTime());
				}
				// 给修改时间统一赋值
				if (ClassUtil.isExistFieldName(cla, CustomSymbolic.TABLE_SERIAL_NO)) {
					Field updateTimeField = cla.getDeclaredField(CustomSymbolic.UPDATE_TIME);
					updateTimeField.setAccessible(true);
					updateTimeField.set(t, DateUtils.getCurrentTime());
				}

			}

			int i = baseUntDal.updateListWithDffOrAdd(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(String.valueOf(i));
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_S.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel getModel(String id) {
		return getModel(id, isLogRead);
	}

	public ResultModel getModel(String id, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(id);
			result.setIncomeCount(0);
			result.setObjectId(id);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			T t = baseUntDal.getModel(id);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODEL_S.getCode());
			result.setResultMessage(OperResult.GETMODEL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (t == null)
				result.setTotalCount(0);
			else
				result.setReturnValue(JSONObject.fromObject(t).toString());
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);

			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText());
			throw globalException;

		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}
		return result;
	}

	public ResultModel getListBySQL(Map<String, Object> map) {
		return getListBySQL(map, isLogRead);
	}

	public ResultModel getListBySQL(Map<String, Object> map, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			JSONObject jsonObject = JSONObject.fromObject(map);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setIncomeCount(0);
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYSQL.getCode());
			result.setOperTypeText(OperType.GETLISTBYSQL.getText());
			result.setRemark("");

			String sql = (String) map.get("Sql");
			PageHelper.startPage(PageHelperUtil.getPageNum(map), PageHelperUtil.getPageSize(map));

			List<Map<String, Object>> list = baseUntDal.getListBySQL(sql);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
			List<Map<String, Object>> modelList = pageInfo.getList();

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(pageInfo.getTotal());
			result.setResultCode(OperResult.GETLISTBYSQL_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_F.getText());
			result.setReturnValue(e.getMessage());
			result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);

			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
			insertRedis(result);
		}
		return result;
	}

	private void insertRedis(ResultModel result) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Field[] fields = result.getClass().getSuperclass().getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(result));
			}
			String gp_oper_log_id = "gp_oper_log:id:" + result.getId();
			// 暂时注掉，以后设计日志插入redis
			// redisUtil.lSet("gp_oper_log:add_time:"+
			// DateUtils.getCurrentDateStr(), gp_oper_log_id);
			// redisUtil.hmset(gp_oper_log_id, map);
		} catch (Exception e) {

		}
	}

}
