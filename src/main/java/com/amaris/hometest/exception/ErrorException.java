package com.amaris.hometest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Generic Application Error")
public class ErrorException extends Exception{

	/**
	 *  ERROR HANDLED BY THE APPLICATION
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

	public ErrorException(String code, String descriptino) {
		super(descriptino);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
