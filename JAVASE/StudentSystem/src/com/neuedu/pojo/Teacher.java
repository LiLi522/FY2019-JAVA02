package com.neuedu.pojo;

public class Teacher {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Teacher(String username,String password){
		this.username = username;
		this.password = password;
	}
}
