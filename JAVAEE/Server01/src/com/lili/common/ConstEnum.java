package com.lili.common;

public enum ConstEnum {
	//登录模块
	USERNAME_NOT_NULL(10,"用户名不能为空"),
	PASSWORD_NOT_NULL(11,"密码不能为空"),
	USERNAME_NOT_EXIST(12,"用户名不存在"),
	PASSWORD_NOT_TRUE(13,"密码不正确"),
	LOGIN_SUCCES(14,"登录成功"),
	//注册模块
	USERNAME_EXIST(15,"用户名已存在"),
	PASSWORD_NOT_SAME(16,"两次输入的密码不相同"),
	EMAIL_NOT_NULL(17,"邮箱不能为空"),
	REGISTER_SUCCES(18,"注册成功"),
	//增加学生信息模块
	ID_EXIST(19,"学生id已存在"),
	ID_NOT_NULL(20,"id不能为空"),
	NAME_NOT_NULL(21,"姓名不能为空"),
	SCORE_NOT_NULL(22,"分数不能为空"),
	PHONE_NOT_NULL(23,"电话号码不能为空"),
	ID_NOT_EXIST(24,"id不存在"),
	OPERATION_NOT_NULL(3,"操作数不能为空"),
	OPERATION_IS_NUM(4,"操作数必须是数字")
	;
	private int type;
	private String desc;
	private 
	ConstEnum(int type,String desc) {
		// TODO Auto-generated constructor stub
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
