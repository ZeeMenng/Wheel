package com.zee.set.exception;

import com.zee.ent.custom.ResultModel;

/**
 * @author Zee
 * @createDate 2017年4月17日 下午6:28:16
 * @updateDate 2017年4月17日 下午6:28:16
 * @description 全局异常封装,在GlobalExceptionHandler配置中调用。
 */
public class GlobalException extends RuntimeException {

	private ResultModel resultModel;

	public GlobalException() {
		super();
	}

	public GlobalException(Integer resultCode, String message) {
		super();
		this.resultModel = new ResultModel();
		this.resultModel.setResultMessage(message);
		this.resultModel.setResultCode(resultCode);
	}

	public GlobalException(String message) {
		super();
		this.resultModel = new ResultModel();
		this.resultModel.setResultMessage(message);
	}

	public GlobalException(ResultModel resultModel) {
		super();
		this.resultModel = resultModel;
	}

	public ResultModel getResultModel() {
		return resultModel;
	}

	public void setResultModel(ResultModel resultModel) {
		this.resultModel = resultModel;
	}

}
