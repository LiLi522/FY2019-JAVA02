package com.lili.common;

public enum CourseOperationEnum {
	COURSE_ADD(1,"�γ����"),
	COURSE_UPDATE(2,"�γ��޸�"),
	COURSE_DEL(3,"�γ�ɾ��"),
	COURSE_FIND(4,"�鿴���пγ�"),
	COURES_FIND_BYID(5,"����id�鿴�γ�")
	;
	private int type;
	private String desc;
	CourseOperationEnum(int type,String desc) {
		this.type = type;
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
