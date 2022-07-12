package com.amaris.hometest.domainvalue;

public enum OperationStatus {

	VALIDATED(1), REFUSED(2), DELETED(3);
	
	private int id;
	
	private OperationStatus(int value) {
		id = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
