package com.lili.common;

public enum OperationEnum {
	ADMIN_LOGIN(1,"µÇÂ¼"),
	ADMIN_REGISTER(2,"×¢²á")
	;
	private int operation_type;
	private String operation_desc;
	OperationEnum(int operation_type,String operation_desc) {
		this.operation_type = operation_type;
		this.operation_desc = operation_desc;
	}
	public int getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(int operation_type) {
		this.operation_type = operation_type;
	}
	public String getOperation_desc() {
		return operation_desc;
	}
	public void setOperation_desc(String operation_desc) {
		this.operation_desc = operation_desc;
	}
	
}
