package com.amaris.hometest.datatransferobject;

public class ErrorDTO extends AbstractDto{
	
	public ErrorDTO(String code, String description) {
		super.setMessage(description);
		super.setStatus(code);
	}
	
	public String getCode() {
		return super.getStatus();
	}
	
	public String getDescription() {
		return super.getMessage();
	}
}
