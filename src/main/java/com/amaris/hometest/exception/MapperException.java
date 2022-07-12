package com.amaris.hometest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Some entity cannot be validated ...")
public class MapperException extends Exception {

	private static final long serialVersionUID = 1L;

	public MapperException(Exception e) {
		super(e);
	}

}
