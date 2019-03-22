package Student;
import java.util.Scanner;

public class Student {
	
	
	
	
	public static void main(String[] args){
		welcome();
		Admin[] admins = new Admin[5];
		operate(admins);
	}
	
	
	
	
	
	
	/**
	 *欢迎界面函数
	 */
	public static void welcome(){
		System.out.println("**************欢迎 登录学生信息管理系统**************");
		System.out.println("1.登录   2.退出");
		System.out.println("*****************************************************");
	}
	
	public static void operate(Admin[] admins){
		System.out.print("请选择：");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		if (choice == 1) {
			login(admins);
		}else if (choice == 2) {
			System.exit(0);
		}else {
			System.out.println("不存在此操作，请重新输入...");
		}
		operate(admins);
		
	}
	public static void login(Admin[] admins){
		Admin admin = new Admin("admin","admin");
		Scanner scanner = new Scanner(System.in);
		System.out.println("欢迎登录！");
		System.out.print("请输入用户名：");
		String name = scanner.nextLine();
		System.out.print("请输入密码：");
		String word = scanner.nextLine();
		if (admin.username.equals(name) && admin.password.equals(word)) {
			System.out.println("登录成功");
			System.out.println("欢迎您，" + admin.username);
		}else {
			System.out.println("登录失败！");
		}
	}
	
}


class Stu {
	public int id;
	public String name;
	public String sex;
	public int age;
	public String _class;
	public String address;
	public String phone;
	public String email;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

class Admin{
	public String username;
	public String password;
	
	public Admin(String username,String password){
		this.username = username;
		this.password = password;
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
	
	
}


