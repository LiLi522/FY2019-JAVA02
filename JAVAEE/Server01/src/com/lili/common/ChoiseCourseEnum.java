package com.lili.common;

public enum ChoiseCourseEnum {
	CHOISE_COURSE(1,"ѧ��ѡ��"),
	FIND_COURSE(2,"ѧ���鿴��ѡ�γ�"),
	DEL_COURSE(3,"ѧ��ɾ����ѡ�γ�"),
	FIND_ALL_COURSE(4,"ѧ���鿴���п�ѡ�γ�")
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
