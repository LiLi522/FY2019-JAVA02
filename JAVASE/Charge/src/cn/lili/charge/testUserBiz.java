package cn.lili.charge;

public class testUserBiz implements userBiz {
	
	public static void main(String[] args){
		User[] u = new User[2];
		u[0] = new User("admin","admin","Administrator","admin@123.com");
		u[1] = new User("tom","cat","tomcat","tomcat@cat.com");
		for(int i = 0; i < u.length; ++i) {
			System.out.println(u[i].toString());
		}
		
	}
	
	
	@Override
	public void register(String username, String password, String password2, String name, String email)
			throws registerException {
		// TODO Auto-generated method stub
		User[] u = new User[2];
		u[0] = new User("admin","admin","Administrator","admin@123.com");
		u[1] = new User("tom","cat","tomcat","tomcat@cat.com");
		if (u[0].getUsername().equals(username) || u[1].getUsername().equals(username)) {
			throw new registerException("您输入的用户名已存在！！！");
		}else if(!password.equals(password2)){
			throw new registerException("您输入的密码不正确！！！");
		}else {
			System.out.println("注册成功！！！");
		}
	}

	@Override
	public void login(String username, String password) throws loginException {
		// TODO Auto-generated method stub
		User[] u = new User[2];
		u[0] = new User("admin","admin","Administrator","admin@123.com");
		u[1] = new User("tom","cat","tomcat","tomcat@cat.com");
		if (!u[0].getUsername().equals(username)) {
			throw new loginException("您输入的用户名不存在！！！");
		}else if (!u[0].getPassword().equals(password)) {
			throw new loginException("您输入的密码不正确！！！");
		}else {
			System.out.println("登录成功！！！");
		}
	}

}
