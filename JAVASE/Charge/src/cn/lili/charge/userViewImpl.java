package cn.lili.charge;
import java.util.Scanner;

public class userViewImpl implements userView {

	@Override
	public void login() {
		// TODO Auto-generated method stub
		testUserBiz user = new testUserBiz();
		Scanner scnn = new Scanner(System.in);
		
		System.out.print("�������û�����");
		String username = scnn.nextLine();
		
		System.out.print("���������룺");
		String password = scnn.nextLine();
		
		user.login(username,password);
		
		
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args){
		userViewImpl u = new userViewImpl();
		u.login();
	}
	

}
