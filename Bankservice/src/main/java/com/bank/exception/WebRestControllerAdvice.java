package com.bank.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.request.ResponseMsg;

@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(value=RecordNotFoundException.class)
	public ResponseMsg handleNotFoundException(RecordNotFoundException ex) {
		ResponseMsg responsemsg=new ResponseMsg("error-001",ex.getMessage());
		return responsemsg;
	}
	@ExceptionHandler(value=AlreadyExistException.class)
	public ResponseMsg handleAlreadyExistException(AlreadyExistException ex) {
		ResponseMsg responsemsg=new ResponseMsg("error-002",ex.getMessage());
		return responsemsg;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
	     
	    return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getConstraintViolations().forEach(cv -> {
	        errors.put("message", cv.getMessage());
	        errors.put("path", (cv.getPropertyPath()).toString());
	    }); 
	 
	    return errors;
	}

	
	
}
