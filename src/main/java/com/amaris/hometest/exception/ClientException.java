package com.amaris.hometest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Received an invalid response from the server ...")
public class ClientException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClientException(Exception e) {
		super(e);
	}
}
