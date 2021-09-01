package com.zee.ent.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.set.symbolic.CustomSymbolic;

@JsonIgnoreProperties({ "addTime", "domainId", "id", "isSuccessValue", "incomeValue", "operTypeCode", "operTypeText", "remark", "returnValue", "tableName","originException" })
public class ResultModel extends GpOperLog {

	// 查询操作是否成功
	private boolean isSuccess = false;

	private Object data;

	// 原始异常信息——如果出现异常的话
	private Exception originException;

	public Exception getOriginException() {
		return originException;
	}

	public void setOriginException(Exception originException) {
		this.originException = originException;
	}

	public boolean getIsSuccess() {

		isSuccess = this.getIsSuccessCode() == null ? false : this.getIsSuccessCode() == CustomSymbolic.DCODE_BOOLEAN_T ? true : false;
		return isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
