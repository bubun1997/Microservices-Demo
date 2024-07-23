package com.soumya.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<String> handleRuntimeException(MyException ex){
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMssg());
	}

}
