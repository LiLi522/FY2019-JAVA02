package cn.lili.charge;

public interface userBiz {
	//用户注册
	public void register(String username, 
						  String password, 
						  String password2,
						  String name, 
						  String email) throws registerException;
	//用户登录
	public void login(String username, String password) throws loginException;
	
}
