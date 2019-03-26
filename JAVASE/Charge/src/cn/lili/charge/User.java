package cn.lili.charge;

public class User {
	private String username;
	private String password;
	private String email;
	private String name;//’Ê µ–’√˚
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String password2) {
		this.email = password2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public User(String username,String password,String name,String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", name=" + name + "]";
	}
	
	
}
