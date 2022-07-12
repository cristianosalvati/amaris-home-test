package com.amaris.hometest.domainvalue;

public enum OperationType {

	SYSTEM(0), BALANCE(1), MONEY_TRANSFER(2), CASH_ACCOUNT(3), TRANSACTIONS_LIST(4);
	
	private int id;
	
	private OperationType(int value) {
		id = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
