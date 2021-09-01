package com.zee.set.handler;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.ent.custom.ResultModel;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2017年4月17日 上午10:25:37
 * @updateDate 2017年4月17日 上午10:25:37
 * @description 1、在应用中通过ControllerAdvice注册一个全局的Exception Handler来捕获所有的应用异常。
 *              2、在ExceptionHandler中，对捕获到的异常进行处理，把异常转换成约定的格式。
 *              3、为了能使ExceptionHandler区分出服务错误和校验错误，自定义了一个GlobalException类来存储BLL层抛出的所有异常。为了统一捕获所有异常必须要有此处理，为了保证事务处理BLL必须要抛出异常。
 *              4、在业务代码中，只需要专注于自身的业务实现，对于自身或者第三方的服务异常，可以选择捕获后处理或者直接抛出。
 *              5、对于校验错误，只需要构建一个ValidationException对象，存入校验信息后抛出即可。
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {

		// 打印异常
		logger.error(exception.getMessage(), exception);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders headers = new HttpHeaders();

		ResultModel result = new ResultModel();
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);

		if (exception instanceof GlobalException) {
			GlobalException globalException = (GlobalException) exception;
			result = globalException.getResultModel();
			if (result.getOriginException() != null) {
				String causeMessage = result.getOriginException().getCause().getMessage();
				if (result.getOriginException() instanceof MyBatisSystemException) {
					Throwable	throwable = ((MyBatisSystemException) result.getOriginException()).getCause().getCause();
					if(throwable instanceof GlobalException)
						causeMessage=((GlobalException) throwable).getResultModel().getResultMessage();
				}
				// 根据异常中的CauseMessage给出不同的提示信息
				if (StringUtils.isNotEmpty(causeMessage)) {
					if (causeMessage.matches("Incorrect(.*)value(.*)for column(.*)"))
						result.setResultMessage(result.getResultMessage() + "请检查输入类型……");
					else if (causeMessage.contains("Data truncation: Data too long for column "))
						result.setResultMessage(result.getResultMessage() + "请控制输入长度……");
					else if (causeMessage.contains("Cannot add or update a child row"))
						result.setResultMessage(result.getResultMessage() + "请检查引用部分的输入……");
					else
						result.setResultMessage(causeMessage);
				}
			}
		} else {
			result.setResultMessage(exception.getMessage());
		}



		headers.add("Content-Type", "application/json;charset=UTF-8");
		return handleExceptionInternal(exception, result, headers, status, request);
	}

}