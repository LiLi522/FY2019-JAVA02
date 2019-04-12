package com.lili.common;

public enum CourseOperationEnum {
	COURSE_ADD(1,"课程添加"),
	COURSE_UPDATE(2,"课程修改"),
	COURSE_DEL(3,"课程删除"),
	COURSE_FIND(4,"查看所有课程"),
	COURES_FIND_BYID(5,"根据id查看课程")
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
