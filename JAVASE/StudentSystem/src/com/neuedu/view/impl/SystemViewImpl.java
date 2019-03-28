package com.neuedu.view.impl;

import java.util.Scanner;

import com.neuedu.exception.loginException;
import com.neuedu.service.SystemBiz;
import com.neuedu.service.Impl.SystemBizImpl;
import com.neuedu.view.SystemView;

public class SystemViewImpl implements SystemView {
	
	@Override
	public void welcome() {
		// TODO Auto-generated method stub
		System.out.println("----------------欢迎登录学生信息管理系统----------------");
		System.out.println("1.登录      2.退出");
		System.out.println("--------------------------------------------------------");
		
	}
	@Override
	public void operate() {
		// TODO Auto-generated method stub
		SystemView teacher = new SystemViewImpl();
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("请输入：");
			int choise = scan.nextInt();
			if (choise ==1 ) {
				try {
					teacher.login();
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
				operate();
			}
			
		}	
	}
	@Override
	public void login() throws loginException {
		// TODO Auto-generated method stub
		System.out.println("欢迎登录！");
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String username = scan.nextLine();
		System.out.print("请输入密码：");
		String password = scan.nextLine();
		SystemBiz systembiz = new SystemBizImpl();
		systembiz.login(username, password);
		System.out.println("欢迎您：" + username);
	}
	
	@Override
	public void operate1() {
		// TODO Auto-generated method stub
		SystemView teacher = new SystemViewImpl();
		System.out.println("***************************************请选择要操作的信息对应数字***************************************");
		System.out.println("* 1.查看学生信息        2.添加学生信息        3.删除学生信息        4.修改学生信息        5.退出       *");
		System.out.println("********************************************************************************************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("请选择：");
		int choise = scan.nextInt();
		if (choise == 5) {
			System.exit(0);
		}
		switch(choise){
		case 1 :teacher.checkStu(); break;
		case 2 :teacher.appendStu(); break;
		case 3 :teacher.deleteStu(); break;
		case 4 :teacher.modifyStu(); break;
		}
		
	}

	@Override
	public void checkStu() {
		// TODO Auto-generated method stub
		while(true){
			SystemBiz checkstu = new SystemBizImpl();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+ 1.查看所以学生信息        2.根据id学生信息        3.根据id查询学生姓名        4.返回上一层           +");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Scanner scan = new Scanner(System.in);
			System.out.print("请选择菜单：");
			int choise = scan.nextInt();
			if (choise == 4) {
				checkStu();
				
			}
			switch(choise){
			case 1 :checkstu.checkAll(); break;
			case 2 :checkstu.checkOfId(); break;
			case 3 :checkstu.checkIdForName(); break;
			}
			System.out.println("数据查询完毕，系统将自动返回目录......");
			checkStu();
			break;
		}
		
		
	}

	@Override
	public void appendStu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyStu() {
		// TODO Auto-generated method stub
		
	}
	

	

}
