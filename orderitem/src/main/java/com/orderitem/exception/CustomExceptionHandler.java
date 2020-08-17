package com.orderitem.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderitem.dto.ErrorMessage;

@PropertySource("classpath:error.properties")
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Autowired
	private Environment env;

	public String getConfigValue(String configKey) {
		return env.getProperty(configKey);
	}

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ErrorMessage> handleCustomException(CustomException ex, WebRequest request) {
		ErrorMessage errorDetails = null;
		Integer errorCode = null;
		List<String> errorList = new ArrayList<>(1);
		try {
			String errorMsg = getErrorDescription(getConfigValue(ex.getMessage()));
			log.info("errorMsg {}",errorMsg);
			errorCode = getErrorcode(getConfigValue(ex.getMessage()));			
			errorList.add(null != errorMsg ? errorMsg : "Internal Server Error");
			errorDetails = new ErrorMessage(Integer.parseInt(ex.getMessage()), errorList);			
		} catch (Exception e) {
			log.error("Unable to process error message {} {}", e,ex);
			errorList.add("Internal Server Error");
			errorDetails = new ErrorMessage(5000, errorList);
		}
		return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(null != errorCode ? errorCode : 500));
	}

	public String getErrorDescription(String errorRow) {
		try {
			return new ObjectMapper().readTree(errorRow).get("errorDescription").asText();
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public Integer getErrorcode(String errorRow) {
		try {
			return new ObjectMapper().readTree(errorRow).get("statuscode").asInt();
		} catch (NumberFormatException | JsonProcessingException e) {
			return null;
		}
	}
}
