package com.neuedu.pojo;

public enum Grade {
	SENIOR("����",3),
	MIDDLE("����",2),
	PRIMARY("һ��",1)
	;
	private String grade;
	private int status;
	Grade(String grade,int status) {
		this.grade = grade;
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
