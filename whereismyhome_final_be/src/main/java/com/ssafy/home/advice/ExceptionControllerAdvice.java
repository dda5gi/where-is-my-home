package com.ssafy.home.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ssafy.home.response.ResponseDto;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{

	private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleException(Exception ex) {
		logger.error("Exception 발생 : {}", ex.getMessage());
		ResponseDto responseDto = new ResponseDto();
		responseDto.addPayload("err", ex.getMessage());
		responseDto.setMsg("error");
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}