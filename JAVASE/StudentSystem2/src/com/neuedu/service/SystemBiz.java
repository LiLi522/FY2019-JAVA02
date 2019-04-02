package com.neuedu.service;

import com.neuedu.exception.noIdException;
import com.neuedu.pojo.Student;
import com.neuedu.exception.codeException;
import com.neuedu.exception.idExistExpection;
import com.neuedu.exception.loginException;

public interface SystemBiz {
	 //public boolean searchId(int id); 
	 public void login(String username,String password)throws loginException;//登录异常处理
	 public void Code(String code,String codeP) throws codeException;//处理验证码输入错误异常
	 public String getCode(int size);//获取验证码
	 public void checkAll();
	 public void checkOfId(int id)throws noIdException;
	 public void appendStu(int id) throws idExistExpection;//id已存在异常处理
	 public void add(int id, String name,String sex,int age,int score,String _class,String phone,String r_data,String u_data,int _classNum);//添加学生信息方法
	 public void deleteStu(int id) throws noIdException;
	 public void del(int id);//删除学生信息方法
	 public void modifyStu(int id) throws noIdException;
	 public void modifyById(int id,String str);
	 public String isGander(int choice);
	 public String isGrade(int choice);
	 public String getDate();
	 public void Positive();
	 public void Inverted();
	 public void doubleRow();
}
