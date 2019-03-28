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
		System.out.println("----------------��ӭ��¼ѧ����Ϣ����ϵͳ----------------");
		System.out.println("1.��¼      2.�˳�");
		System.out.println("--------------------------------------------------------");
		
	}
	@Override
	public void operate() {
		// TODO Auto-generated method stub
		SystemView teacher = new SystemViewImpl();
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("�����룺");
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
				System.out.println("������Ĳ��������ڣ�����");
				operate();
			}
			
		}	
	}
	@Override
	public void login() throws loginException {
		// TODO Auto-generated method stub
		System.out.println("��ӭ��¼��");
		Scanner scan = new Scanner(System.in);
		System.out.print("�������û�����");
		String username = scan.nextLine();
		System.out.print("���������룺");
		String password = scan.nextLine();
		SystemBiz systembiz = new SystemBizImpl();
		systembiz.login(username, password);
		System.out.println("��ӭ����" + username);
	}
	
	@Override
	public void operate1() {
		// TODO Auto-generated method stub
		SystemView teacher = new SystemViewImpl();
		System.out.println("***************************************��ѡ��Ҫ��������Ϣ��Ӧ����***************************************");
		System.out.println("* 1.�鿴ѧ����Ϣ        2.���ѧ����Ϣ        3.ɾ��ѧ����Ϣ        4.�޸�ѧ����Ϣ        5.�˳�       *");
		System.out.println("********************************************************************************************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("��ѡ��");
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
			System.out.println("+ 1.�鿴����ѧ����Ϣ        2.����idѧ����Ϣ        3.����id��ѯѧ������        4.������һ��           +");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Scanner scan = new Scanner(System.in);
			System.out.print("��ѡ��˵���");
			int choise = scan.nextInt();
			if (choise == 4) {
				checkStu();
				
			}
			switch(choise){
			case 1 :checkstu.checkAll(); break;
			case 2 :checkstu.checkOfId(); break;
			case 3 :checkstu.checkIdForName(); break;
			}
			System.out.println("���ݲ�ѯ��ϣ�ϵͳ���Զ�����Ŀ¼......");
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
