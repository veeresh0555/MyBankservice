package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String errmsg) {
		super(errmsg);
	}
	public RecordNotFoundException(String errmsg,Throwable t) {
		super(errmsg,t);
	}
	
}
