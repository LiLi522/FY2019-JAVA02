package cn.lili.charge;

public interface userBiz {
	//�û�ע��
	public void register(String username, 
						  String password, 
						  String password2,
						  String name, 
						  String email) throws registerException;
	//�û���¼
	public void login(String username, String password) throws loginException;
	
}
