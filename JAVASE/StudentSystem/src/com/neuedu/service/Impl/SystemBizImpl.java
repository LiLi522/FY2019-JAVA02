package com.neuedu.service.Impl;

import java.util.Scanner;

import com.neuedu.exception.loginException;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.Teacher;
import com.neuedu.service.SystemBiz;

public class SystemBizImpl implements SystemBiz {
	
	private Teacher[] teas = new Teacher[2];
	private Student[] stus = new Student[32];
	
	public SystemBizImpl() {
		Teacher teas1 = new Teacher("admin","admin");
		Teacher teas2 = new Teacher("xxxx","xxxx");
		this.teas[0] = teas1;
		this.teas[1] = teas2;
		Student stus1 = new Student(1, "����", "��", 20, "�м�", "xxxxʡxxx��", "11111111111", "1@qq.com");
		Student stus2 = new Student(2, "����2", "��", 20, "�м�", "xxxx2ʡxxx2��", "11111111112", "2@qq.com");
		Student stus3 = new Student(3, "����3", "��", 20, "�м�", "xxxx3ʡxxx3��", "11111111113", "3@qq.com");
		this.stus[0] = stus1;
		this.stus[1] = stus2;
		this.stus[2] = stus3;
	}
	
	@Override
	public void login(String username, String password) throws loginException {
		// TODO Auto-generated method stub
		
		boolean usernameFlag = true;//Ĭ���û�������
		boolean passwordFlag = true;//Ĭ��������ȷ
		//step1:����tea����
		for (int i = 0; i < teas.length; ++i) {
			Teacher tea = teas[i];
			if (tea == null) {
				continue;
			}
			if (!tea.getUsername().equals(username)) {
				usernameFlag = false;
				continue;
			}else if (!tea.getPassword().equals(password)) {
				passwordFlag = false;
				break;
			}else {
				usernameFlag = true;
				passwordFlag = true;
			}
		}
		if (!usernameFlag) {
			throw new loginException("������Ĺ���Ա�����ڣ�����");
		}else if(!passwordFlag) {
			throw new loginException("����������벻��ȷ������");
		}else {
			System.out.println("��½�ɹ�");
		}
		
	}

	@Override
	public void checkAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < stus.length; ++i) {
			if (stus[i] == null) {
				continue;
			}
			System.out.println("---------------------------------------------����ѧ����Ϣ---------------------------------------------");
			System.out.println("|ѧ��   |����   |����   |�Ա�   |�꼶   |�绰             |EMAIL              |��ַ                   ");
			System.out.println("|"+ stus[i].getId() + "      "+
					            "|"+ stus[i].getName() + "   "+   
					            "|"+ stus[i].getAge() + "     "+
					            "|"+ stus[i].getSex() + "      "+
					            "|"+ stus[i].get_class() + "   "+
					            "|"+ stus[i].getPhone() + "         "+
					            "|"+ stus[i].getEmail()  + "            "+
					            "|"+ stus[i].getAddress() + "               "                   
					            );
		}
	}

	@Override
	public void checkOfId() {
		// TODO Auto-generated method stub
		boolean Idflag = true;
		Scanner scan = new Scanner(System.in);
		System.out.print("������Ҫ��ѯ��id��");
		int id = scan.nextInt();
		for (int i = 0; i < stus.length; ++i) {
			if (stus[i] == null) {
				continue;
			}
			if (stus[i].getId() == id) {
				System.out.println("---------------------------------------------����ѧ����Ϣ---------------------------------------------");
				System.out.println("|ѧ��   |����   |����   |�Ա�   |�꼶   |�绰             |EMAIL              |��ַ                   ");
				System.out.println("|"+ stus[i].getId() + "      "+
						            "|"+ stus[i].getName() + "   "+   
						            "|"+ stus[i].getAge() + "     "+
						            "|"+ stus[i].getSex() + "      "+
						            "|"+ stus[i].get_class() + "   "+
						            "|"+ stus[i].getPhone() + "         "+
						            "|"+ stus[i].getEmail()  + "            "+
						            "|"+ stus[i].getAddress() + "               "                   
						            );	
			}else {
				Idflag = false;
				break;
			}
		}
		if (!Idflag) {
			System.out.println("�������id������!!!");
		}
	}

	@Override
	public void checkIdForName() {
		// TODO Auto-generated method stub
		
	}

}
