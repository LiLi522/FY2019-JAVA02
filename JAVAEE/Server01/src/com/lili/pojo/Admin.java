package com.lili.pojo;

public class Admin {
	private String username;
	private String password;
	private String password2;
	private String email;
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Admin(String username, String password, String password2, String email) {
		super();
		this.username = username;
		this.password = password;
		this.password2 = password2;
		this.email = email;
	}

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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", password2=" + password2 + ", email="
				+ email + "]";
	}
	
}
