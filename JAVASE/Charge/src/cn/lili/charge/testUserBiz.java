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
			throw new registerException("��������û����Ѵ��ڣ�����");
		}else if(!password.equals(password2)){
			throw new registerException("����������벻��ȷ������");
		}else {
			System.out.println("ע��ɹ�������");
		}
	}

	@Override
	public void login(String username, String password) throws loginException {
		// TODO Auto-generated method stub
		User[] u = new User[2];
		u[0] = new User("admin","admin","Administrator","admin@123.com");
		u[1] = new User("tom","cat","tomcat","tomcat@cat.com");
		if (!u[0].getUsername().equals(username)) {
			throw new loginException("��������û��������ڣ�����");
		}else if (!u[0].getPassword().equals(password)) {
			throw new loginException("����������벻��ȷ������");
		}else {
			System.out.println("��¼�ɹ�������");
		}
	}

}
