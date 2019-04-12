package com.lili.common;

public enum ChoiseCourseEnum {
	CHOISE_COURSE(1,"学生选课"),
	FIND_COURSE(2,"学生查看所选课程"),
	DEL_COURSE(3,"学生删除所选课程"),
	FIND_ALL_COURSE(4,"学生查看所有可选课程")
	;
	private int type;
	private String desc;
	
	ChoiseCourseEnum(int type,String desc){
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
