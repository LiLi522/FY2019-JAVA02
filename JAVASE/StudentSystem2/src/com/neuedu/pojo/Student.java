package com.neuedu.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Student implements Comparable<Student> {
	private int id;
	private String name;
	private String sex;
	private int age;
	private String _class;
	private int score;
	private String phone;
	private String r_date;//注册时间
	private String u_date;//更新时间
	private int _classNum;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public String getU_date() {
		return u_date;
	}
	public void setU_date(String u_date) {
		this.u_date = u_date;
	}
	
	public int get_classNum() {
		return _classNum;
	}
	public void set_classNum(int _classNum) {
		this._classNum = _classNum;
	}
	//构造方法 初始化学生对象
	public Student(int id,String name,String sex,int age,int score,String _class,String phone,String r_date,String u_date,int _classNum){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this._class = _class;
		this.score = score;
		this.phone = phone;
		this.r_date = r_date;
		this.u_date = u_date;
		this._classNum = _classNum;
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		/*int i = this.score - o.score;
		return i;*/
		int i = this._classNum - o._classNum;
		if (i == 0) {
			return this.score - o.score;
		}
		return i;
	}

	
	
}
