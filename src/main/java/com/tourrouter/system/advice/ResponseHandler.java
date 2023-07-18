package com.tourrouter.system.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ResponseHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		log.error("response", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{'ss' : 'sss'}");
	}
}
