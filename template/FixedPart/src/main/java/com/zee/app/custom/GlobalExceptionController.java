package com.zee.app.custom;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 
 * @updateDate 
 * @description 
 */

@RestController
public class GlobalExceptionController extends BasicErrorController {
	public GlobalExceptionController() {
		super(new DefaultErrorAttributes(), new ErrorProperties());
	}

	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));

		if (!Strings.isNullOrEmpty((String) body.get("exception")) && body.get("exception").equals(GlobalException.class.getName())) {
			GlobalException globalException = (GlobalException) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

			@SuppressWarnings("unchecked")
			Map<String, Object> resultModelMap = new ObjectMapper().convertValue(globalException.getResultModel(), Map.class);
			body.putAll(resultModelMap);

		}
		return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);
	}

	@Override
	public String getErrorPath() {
		return CustomSymbolic.ERROR_PAGE;
	}

}
