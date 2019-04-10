package com.lili.common;

public enum OperStudentEnum {
	ADD(1,"增加学生信息"),
	FINDALL(2,"查找全部学生信息"),
	FINDBYID(3,"根据ID查找学生信息"),
	UPDATEBYID(4,"修改学生信息"),
	DELBYID(5,"删除学生信息")
	;
	private int oper_type;
	private String oper_desc;
	OperStudentEnum(int oper_type, String oper_desc) {
		this.oper_type = oper_type;
		this.oper_desc = oper_desc;
	}
	public int getOper_type() {
		return oper_type;
	}
	public void setOper_type(int oper_type) {
		this.oper_type = oper_type;
	}
	public String getOper_desc() {
		return oper_desc;
	}
	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}
	
}
