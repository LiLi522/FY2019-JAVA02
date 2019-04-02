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
 * ������û��������ռ��û�������Ϣ
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
		System.out.println("----------------��ӭ��¼ѧ����Ϣ����ϵͳ----------------");
		System.out.println("1.��¼      2.�˳�");
		System.out.println("--------------------------------------------------------");
		
	}
	@Override
	public void operateLogin() {
		// TODO Auto-generated method stub
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.print("�����룺");
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
				System.out.println("������Ĳ��������ڣ�����");
				operateLogin();
			}
			
		}	
	}
	@Override
	public void login() throws loginException {
		//SystemBiz systembiz = new SystemBizImpl();
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		System.out.println("��ӭ��¼��");
		Scanner scan = new Scanner(System.in);
		System.out.print("�������û�����");
		String username = scan.nextLine();
		System.out.print("���������룺");
		String password = scan.nextLine();
		systembiz.login(username, password);
		Code();
		System.out.println("��¼�ɹ���\r" + "��ӭ����" + username + "\r");
	}
	
	@Override
	public void Code() {
		// TODO Auto-generated method stub
		while (true) {
			Scanner scan = new Scanner(System.in);
			String codeP = systembiz.getCode(4);
			System.out.println("��֤��Ϊ��" + codeP);
			
			System.out.print("��������֤�룺");
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
		
		System.out.println("***************************************��ѡ��Ҫ��������Ϣ��Ӧ����***************************************");
		System.out.println("* 1.�鿴ѧ����Ϣ        2.���ѧ����Ϣ        3.ɾ��ѧ����Ϣ        4.�޸�ѧ����Ϣ        5.�˳�       *");
		System.out.println("********************************************************************************************************");
		Scanner scan = new Scanner(System.in);
		System.out.print("��ѡ��");
		int choise = scan.nextInt();
		System.out.println("\r");
		if (choise == 5) {
			System.out.println("\rϵͳ���˳���");
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
		System.out.println("******************************************���ѧ����Ϣ**************************************************");
		while(true) {
			addStu();
			System.out.println("���ݱ���ɹ���ϵͳ���Զ������ϲ�Ŀ¼......\r\r");
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
				System.out.print("������ѧ��id:");
				int id = scan.nextInt();
				scan.nextLine();
				systembiz.appendStu(id);
				System.out.print("������ѧ��������");
				String name = scan.nextLine();
				System.out.print("��ѡ��ѧ���Ա�(1.�� 2.Ů)��");
				int choise = scan.nextInt();
				String sex = systembiz.isGander(choise);
				System.out.print("������ѧ�����䣨ֻ��1-120֮������֣���");
				int age = scan.nextInt();
				System.out.print("������ѧ���ɼ���");
				int score = scan.nextInt();
				System.out.print("��ѡ��ѧ�������༶��1.һ�� 2.���� 3.���ࣩ��");
				int choise_class = scan.nextInt();
				scan.nextLine();
				String _class = systembiz.isGrade(choise_class);
				System.out.print("������ѧ����ϵ��ʽ��");
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
			/*System.out.print("������ѧ��id:");
			int id = scan.nextInt();
			scan.nextLine();*/
		}
		
		
	}
	@Override
	public void deleteStu() {
		// TODO Auto-generated method stub
		System.out.println("******************************************ɾ��ѧ����Ϣ**************************************************");
		while(true) {
			deleteOfId();
			System.out.println("����ɾ���ɹ���ϵͳ���Զ������ϲ�Ŀ¼......\r\r");
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
				System.out.println("������Ҫɾ����ѧ��id��");
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
		System.out.println("******************************************�޸�ѧ����Ϣ**************************************************");
		while(true) {
			modifyOfId();
			System.out.println("�����޸ĳɹ���ϵͳ���Զ������ϲ�Ŀ¼......\r\r");
			operateMenu();
		}
	}
	
	@Override
	public void modifyOfId() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Scanner scan = new Scanner(System.in);
				System.out.println("������Ҫ�޸ĵ�ѧ��id��");
				int id = scan.nextInt();
				systembiz.modifyStu(id);
				System.out.println("***************************************��ѡ��Ҫ�޸ĵ���Ϣ��Ӧ����***************************************");
				System.out.println("* 1.����            2.�Ա�            3.����              4.����           5.�༶         6.�绰       *");
				System.out.println("********************************************************************************************************");
				System.out.println("��ѡ��");
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
			System.out.println("+ 1.�鿴����ѧ����Ϣ        2.����id��ѯѧ����Ϣ          3.�鿴ѧ���ɼ�            4.������һ��       +");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			Scanner scan = new Scanner(System.in);
			System.out.print("��ѡ��˵���");
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
			System.out.println("���ݲ�ѯ��ϣ�ϵͳ���Զ�����Ŀ¼......\r\r");
			checkStu();
			break;
		}	
	}
	
	@Override
	public void checkOfId(){
		// TODO Auto-generated method stub
		//SystemBiz systembiz = new SystemBizImpl();
		Scanner scan = new Scanner(System.in);
		System.out.print("������Ҫ��ѯ��id��");
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
		System.out.println("******************************************�鿴ѧ���ɼ�**************************************************");
		System.out.println("* 1.ȫУ�ɼ���С����            2.ȫУ�ɼ��ɴ�С                        3.���༶�����ɵ͵���         *");
		System.out.println("********************************************************************************************************");
		System.out.println("��ѡ��");
		int choice = scan.nextInt();
		switch (choice) {
		case 1 : systembiz.Positive(); break;
		case 2 : systembiz.Inverted(); break;
		case 3 : systembiz.doubleRow(); break;
		}
		
	}
	
	

	
	
	
	

	

}
