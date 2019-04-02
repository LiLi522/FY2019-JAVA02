package com.neuedu.view.impl;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.neuedu.exception.noIdException;
import com.neuedu.pojo.Gander;
import com.neuedu.pojo.Grade;
import com.neuedu.pojo.Student;
import com.neuedu.exception.codeException;
import com.neuedu.exception.idExistExpection;
import com.neuedu.exception.loginException;
import com.neuedu.service.SystemBiz;
import com.neuedu.service.Impl.SystemBizImpl;
import com.neuedu.view.SystemView;

/**
 * 负责跟用户交互，收集用户输入信息
 *
 */
public class SystemViewImpl implements SystemView {
	SystemBiz systembiz = SystemBizImpl.getInstance();
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String r_data = format.format(date);
	@Override
	public void welcome() {
		// TODO Auto-generated method stub
		System.out.println("----------------欢迎登录学生信息管理系统----------------");
		System.out.println("1.登录      2.退出");
		System.out.println("--------------------------------------------------------");
		
	}
	@Override
	public void operateLogin() {
		// TODO Auto-generated method stub
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("请输入：");
			int choise = scan.nextInt();
			if (choise ==1 ) {
				try {
					login();
					break;
				} catch (loginException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}else if (choise == 2) {
				System.exit(0);
			}else {
				System.out.println("您输入的操作不存在！！！");
				operateLogin();
			}
			
		}	
	}
	@Override
	public void login() throws loginException {
		//SystemBiz systembiz = new SystemBizImpl();
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		System.out.println("欢迎登录！");
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String username = scan.nextLine();
		System.out.print("请输入密码：");
		String password = scan.nextLine();
		systembiz.login(username, password);
		Code();
		System.out.println("登录成功！\r" + "欢迎您：" + username + "\r");
	}
	
	@Override
	public void Code() {
		// TODO Auto-generated method stub
		while (true) {
			Scanner scan = new Scanner(System.in);
			String codeP = systembiz.getCode(4);
			System.out.println("验证码为：" + codeP);
			
			System.out.print("请输入验证码：");
			String code = scan.nextLine();
			try {
				systembiz.Code(code,codeP);
				break;
			} catch (codeException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		
		
	}
	
	@Override
	public void operateMenu() {
		// TODO Auto-generated method stub
		
		System.out.println("***************************************请选择要操作的信息对应数字***************************************");
		System.out.println("* 1.查看学生信息        2.添加学生信息        3.删除学生信息        4.修改学生信息        5.退出       *");
		System.out.println("********************************************************************************************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("请选择：");
		int choise = scan.nextInt();
		System.out.println("\r");
		if (choise == 5) {
			System.out.println("\r系统已退出。");
			System.exit(0);
		}
		switch(choise){
		case 1 : checkStu(); break;
		case 2 : appendStu(); break;
		case 3 : deleteStu(); break;
		case 4 : modifyStu(); break;
		}
		
	}
	
	@Override
	public void appendStu() {
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		System.out.println("******************************************添加学生信息**************************************************");
		while(true) {
			addStu();
			System.out.println("数据保存成功，系统将自动返回上层目录......\r\r");
			operateMenu();
		}
		
	}
	
	@Override
	public void addStu() {
		// TODO Auto-generated method stub
		//String name,String sex,int age,int score,String _class,String phone,String r_data,String u_data
		while(true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.print("请输入学生id:");
				int id = scan.nextInt();
				scan.nextLine();
				systembiz.appendStu(id);
				System.out.print("请输入学生姓名：");
				String name = scan.nextLine();
				System.out.print("请选择学生性别(1.男 2.女)：");
				int choise = scan.nextInt();
				String sex = systembiz.isGander(choise);
				System.out.print("请输入学生年龄（只能1-120之间的数字）：");
				int age = scan.nextInt();
				System.out.print("请输入学生成绩：");
				int score = scan.nextInt();
				System.out.print("请选择学生所属班级（1.一班 2.二班 3.三班）：");
				int choise_class = scan.nextInt();
				scan.nextLine();
				String _class = systembiz.isGrade(choise_class);
				System.out.print("请输入学生联系方式：");
				String phone = scan.nextLine();
				String r_data = systembiz.getDate();
				String u_date = null;
				systembiz.add(id, name, sex, age, score, _class, phone, r_data, u_date,choise_class);
				//int id,String name,String sex,int age,int score,String _class,String phone,String r_data,String u_data
				break;
			} catch (idExistExpection e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			/*System.out.print("请输入学生id:");
			int id = scan.nextInt();
			scan.nextLine();*/
		}
		
		
	}
	@Override
	public void deleteStu() {
		// TODO Auto-generated method stub
		System.out.println("******************************************删除学生信息**************************************************");
		while(true) {
			deleteOfId();
			System.out.println("数据删除成功，系统将自动返回上层目录......\r\r");
			operateMenu();
		}
		
	}
	
	@Override
	public void deleteOfId() {
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		while(true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("请输入要删除的学生id：");
				int id = scan.nextInt();
				systembiz.deleteStu(id);
				systembiz.del(id);
				break;
			} catch (noIdException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}	
	}
	
	@Override
	public void modifyStu() {
		// TODO Auto-generated method stub
		System.out.println("******************************************修改学生信息**************************************************");
		while(true) {
			modifyOfId();
			System.out.println("数据修改成功，系统将自动返回上层目录......\r\r");
			operateMenu();
		}
	}
	
	@Override
	public void modifyOfId() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("请输入要修改的学生id：");
				int id = scan.nextInt();
				systembiz.modifyStu(id);
				System.out.println("***************************************请选择要修改的信息对应数字***************************************");
				System.out.println("* 1.姓名            2.性别            3.年龄              4.分数           5.班级         6.电话       *");
				System.out.println("********************************************************************************************************");
				System.out.println("请选择：");
				int choise = scan.nextInt();
				scan.nextLine();
				switch(choise) {
				case 1 : systembiz.modifyById(id, "name");break;
				case 2 : systembiz.modifyById(id, "sex");break;
				case 3 : systembiz.modifyById(id, "age");break;
				case 4 : systembiz.modifyById(id, "score");break;
				case 5 : systembiz.modifyById(id, "_class");break;
				case 6 : systembiz.modifyById(id, "phone");break;
				//String name,String sex,int age,int score,String _class,String phone	    
				}
				break;
			} catch (noIdException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public void checkStu() {
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		while(true){
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+ 1.查看所有学生信息        2.根据id查询学生信息          3.查看学生成绩            4.返回上一层       +");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Scanner scan = new Scanner(System.in);
			System.out.print("请选择菜单：");
			int choise = scan.nextInt();
			//System.out.println("\r");
			if (choise == 4) {
				operateMenu();
				
			}
			switch(choise){
			case 1 :systembiz.checkAll(); break;
			case 2 : checkOfId(); break;
			case 3 : checkScore(); break;
			}
			System.out.println("数据查询完毕，系统将自动返回目录......\r\r");
			checkStu();
			break;
		}	
	}
	
	@Override
	public void checkOfId(){
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入要查询的id：");
		int id = scan.nextInt();
		try {
			systembiz.checkOfId(id);
		} catch (noIdException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
	}
	
	@Override
	public void checkScore(){
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		Scanner scan = new Scanner(System.in);
		System.out.println("******************************************查看学生成绩**************************************************");
		System.out.println("* 1.全校成绩由小到大            2.全校成绩由大到小                        3.按班级分数由低到高         *");
		System.out.println("********************************************************************************************************");
		System.out.println("请选择：");
		int choice = scan.nextInt();
		switch (choice) {
		case 1 : systembiz.Positive(); break;
		case 2 : systembiz.Inverted(); break;
		case 3 : systembiz.doubleRow(); break;
		}
		
	}
	
	

	
	
	
	

	

}
