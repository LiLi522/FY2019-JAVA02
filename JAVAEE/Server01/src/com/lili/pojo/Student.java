package com.lili.pojo;

public class Student {
	private int id;
	private String name;
	private int score;
	private String phone;
	private String password;
	public Student(int id, String name, int score, String phone,String password) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.phone = phone;
		this.password = password;
	}
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", score=" + score + ", phone=" + phone + "]";
	}
	
}
