package com.lili.common;

public enum OperStudentEnum {
	ADD(1,"����ѧ����Ϣ"),
	FINDALL(2,"����ȫ��ѧ����Ϣ"),
	FINDBYID(3,"����ID����ѧ����Ϣ"),
	UPDATEBYID(4,"�޸�ѧ����Ϣ"),
	DELBYID(5,"ɾ��ѧ����Ϣ")
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
