package com.neuedu.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.neuedu.exception.codeException;
import com.neuedu.exception.idExistExpection;
import com.neuedu.exception.loginException;
import com.neuedu.pojo.Gander;
import com.neuedu.pojo.Grade;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.Teacher;
import com.neuedu.service.SystemBiz;
import com.neuedu.exception.noIdException;



/**
 * ������ҵ���߼�
 *
 */
public class SystemBizImpl implements SystemBiz {
	
	private static SystemBizImpl systemBizImpl;
	private Teacher[] teas = new Teacher[2];
	//static Student[] stus = new Student[32];
	List<Student> stus = new ArrayList<Student>();
	
	private SystemBizImpl() {
		//��ʼ������Ա
		Teacher teas1 = new Teacher("ad","ad");
		Teacher teas2 = new Teacher("xxxx","xxxx");
		this.teas[0] = teas1;
		this.teas[1] = teas2;
		//��ʼ��ѧ��
		stus.add(new Student(1, "����1", "��", 20, 70, "����", "11111111111", getDate(), "null", 3));
		stus.add(new Student(2, "����2", "��", 21, 60, "һ��", "11111111112", getDate(), "null", 1));
		stus.add(new Student(3, "����3", "Ů", 20, 50, "����", "11111111113", getDate(), "null", 3));
		stus.add(new Student(4, "����4", "��", 22, 90, "һ��", "11111111114", getDate(), "null", 1));
		stus.add(new Student(5, "����5", "Ů", 20, 40, "����", "11111111115", getDate(), "null", 2));
		stus.add(new Student(6, "����6", "��", 25, 20, "����", "11111111116", getDate(), "null", 2));
		stus.add(new Student(7, "ww", "��", 20, 72, "����", "11111111111", getDate(), "null", 3));
		stus.add(new Student(8, "qq", "��", 21, 65, "һ��", "11111111112", getDate(), "null", 1));
		stus.add(new Student(9, "rr", "Ů", 20, 59, "����", "11111111113", getDate(), "null", 3));
		stus.add(new Student(10, "ttt", "��", 22, 94, "һ��", "11111111114", getDate(), "null", 1));
		stus.add(new Student(11, "yyy", "Ů", 20, 42, "����", "11111111115", getDate(), "null", 2));
		stus.add(new Student(12, "ss", "��", 25, 25, "����", "11111111116", getDate(), "null", 2));
		stus.add(new Student(13, "tt", "��", 20, 79, "����", "11111111111", getDate(), "null", 3));
		stus.add(new Student(14, "ii", "��", 21, 68, "һ��", "11111111112", getDate(), "null", 1));
		stus.add(new Student(15, "oo", "Ů", 20, 57, "����", "11111111113", getDate(), "null", 3));
		stus.add(new Student(16, "oo", "��", 22, 94, "һ��", "11111111114", getDate(), "null", 1));
		stus.add(new Student(17, "yy5", "Ů", 20, 43, "����", "11111111115", getDate(), "null", 2));
		stus.add(new Student(18, "ii6", "��", 25, 22, "����", "11111111116", getDate(), "null", 2));
	}
	//����ģʽ
	public static SystemBizImpl getInstance() {
		if (systemBizImpl == null) {
			systemBizImpl = new SystemBizImpl();
		}
		return new  SystemBizImpl();
	}
	
	@Override
	public void login(String username, String password) throws loginException {
		// TODO Auto-generated method stub
		
		boolean usernameFlag = false;//Ĭ���û�������
		boolean passwordFlag = false;//Ĭ��������ȷ
		//step1:����tea����
		for (int i = 0; i < teas.length; ++i) {
			Teacher tea = teas[i];
			if (tea == null) {
				continue;
			}
			if (tea.getUsername().equalsIgnoreCase(username)) {
				usernameFlag = true;
				if (tea.getPassword().equalsIgnoreCase(password)) {
					passwordFlag = true;
					break;
				}else {
					usernameFlag = false;
					passwordFlag = false;
				}
			}	
		} 
		if (!usernameFlag) {
			throw new loginException("����������Ĺ���Ա�����ڣ�����");
		}else if (!passwordFlag){
			throw new loginException("��������������벻��ȷ������");
		}
		
	}
	
	
	@Override
	public String getCode(int size) {
		// TODO Auto-generated method stub
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String code = "";
		Random rand = new Random();
		for (int i = 0; i < size; ++i) {
			code = code + str.charAt(rand.nextInt(str.length()));
		}
		return code;
		
	}
	@Override
	public void Code(String code,String codeP) throws codeException {
		// TODO Auto-generated method stub
		boolean codeFlag = false;
		if (code.equalsIgnoreCase(codeP)) {
			//System.out.println(code.equalsIgnoreCase(codeP));
			codeFlag = true;
		}
		if (!codeFlag) {
			throw new codeException("���������֤�벻��ȷ������");
		}
	}
	@Override
	public void appendStu(int id) throws idExistExpection {
		// TODO Auto-generated method stub
		int idexist = 0;
		boolean Idflag = false;//Ĭ�ϲ�ѯid������
		int len = stus.size();//ѧ������ĳ���
		for (int i = 0; i < len; ++i) {
			//�ж������Ƿ�Ϊ��
			if (stus == null) {
				continue;
			}
			if (stus.get(i).getId() == id) {
				idexist = i;
				Idflag = true;
				break;
			}
		}
		if (Idflag) {
			throw new idExistExpection("�������id" + stus.get(idexist).getId() +"���Ѵ�������������");
		}
	}

	@Override
	public void add(int id,String name,String sex,int age,int score,String _class,String phone,String r_date,String u_date,int _classNum) {
		
		// TODO Auto-generated method stub
		u_date = getDate();
		stus.add(new Student(id, name, sex, age, score, _class, phone, r_date, u_date,_classNum));
				
	}
	
	@Override
	public void deleteStu(int id) throws noIdException {
		// TODO Auto-generated method stub
		boolean idFlag = false;//Ĭ�ϸ�id�����ڣ�
		int len = stus.size();//ѧ�����鳤��
		for(int i = 0; i < len; ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			if (id == stus.get(i).getId()) {
				idFlag = true;
				break;
			}
		}
		if (!idFlag) {
			throw new noIdException("��Ҫɾ����id�����ڣ�����");
		}
	}

	@Override
	public void del(int id) {
		// TODO Auto-generated method stub	
		int len = stus.size();
		for (int i = 0; i < len; ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			if (id == stus.get(i).getId()) {
				stus.remove(i);
				--len;
			}
		}
	}
	
	@Override
	public void modifyStu(int id) throws noIdException {
		// TODO Auto-generated method stub
		boolean idFlag = false;//Ĭ�ϸ�id�����ڣ�
		int len = stus.size();//ѧ�����鳤��
		for(int i = 0; i < len; ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			if (id == stus.get(i).getId()) {
				idFlag = true;
				break;
			}
		}
		if (!idFlag) {
			throw new noIdException("��Ҫ�޸ĵ�id�����ڣ�����");
		}
	}
	
	@Override
	public void modifyById(int id,String str) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int len = stus.size();
		for (int i = 0; i < len; ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			if (id == stus.get(i).getId()){
				if (str.equals("name")) {
					System.out.print("������������");
					String name = scan.nextLine();
					stus.get(i).setName(name);
					//System.out.println(stus.get(i).toString());
					stus.get(i).setU_date(getDate());
					break;
				}
				if (str.equals("sex")) {
					System.out.println("��ѡ���Ա�");
					System.out.println("1.�� 2.Ů");
					int choice = scan.nextInt();
					String sex = isGander(choice);
					stus.get(i).setSex(sex);
					stus.get(i).setU_date(getDate());
					break;
				}
				if (str.equals("age")) {
					System.out.print("���������䣺");
					int age = scan.nextInt();
					stus.get(i).setAge(age);
					stus.get(i).setU_date(getDate());
					break;
				}
				if (str.equals("score")) {
					System.out.print("�����������");
					int score = scan.nextInt();
					stus.get(i).setScore(score);
					stus.get(i).setU_date(getDate());
					break;
				}
				if (str.equals("_class")) {
					System.out.println("��ѡ��༶��");
					System.out.println("1.һ�� 2.���� 3.����");
					int choice = scan.nextInt();
					String _class = isGrade(choice);
					stus.get(i).set_class(_class);
					stus.get(i).setU_date(getDate());
					break;
				}
				if (str.equals("phone")) {
					System.out.print("������绰��");
					String phone = scan.nextLine();
					stus.get(i).setPhone(phone);
					stus.get(i).setU_date(getDate());
					break;
				}
				//String name,String sex,int age,int score,String _class,String phone,String r_date,String u_date
			}
			
		}
	}
	
	@Override
	public void checkAll() {
		// TODO Auto-generated method stub
		System.out.println("\r" + "����ѧ����Ϣ���£�");
		for (int i = 0; i < stus.size(); ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			System.out.println("-------------------------------------------------ѧ����Ϣ---------------------------------------------");
			System.out.println("|ѧ��\t|����\t|�Ա�\t|����\t|����\t|�༶\t|�绰\t\t|ע��ʱ��\t\t\t|�޸�ʱ��\t\t\t");
			System.out.println("|"+ stus.get(i).getId() + "\t"+
					            "|"+ stus.get(i).getName() + "\t"+   
					            "|"+ stus.get(i).getSex() + "\t"+
					            "|"+ stus.get(i).getAge() + "\t"+
					            "|"+ stus.get(i).getScore() + "\t"+
					            "|"+ stus.get(i).get_class() + "\t" + 
					            "|"+ stus.get(i).getPhone()  + "\t" + 
					            "|"+ stus.get(i).getR_date() + "\t\t" +   
					            "|"+ stus.get(i).getU_date() + "\t\t\t" 
					            );
		}
	}

	@Override
	public void checkOfId(int id) throws noIdException {
		// TODO Auto-generated method stub
		boolean Idflag = false;
		int len = stus.size();
		System.out.println(len);
		/*for (int i = 0; i < stus.size(); ++i) {
			if (stus.get(i) == null) {
				continue;
			}
			if (stus.get(i).getId() == id) {
				//String name,String sex,int age,int score,String _class,String phone,String r_date,String u_date
				System.out.println("-------------------------------------------------ѧ����Ϣ---------------------------------------------");
				System.out.println("|ѧ��\t|����\t|�Ա�\t|����\t|����\t|�༶\t|�绰\t\t\t|ע��ʱ��\t\t\t\t|�޸�ʱ��\t\t\t");
				System.out.println("|"+ stus.get(i).getId() + "\t"+
						            "|"+ stus.get(i).getName() + "\t"+   
						            "|"+ stus.get(i).getSex() + "\t"+
						            "|"+ stus.get(i).getAge() + "\t"+
						            "|"+ stus.get(i).getScore() + "\t"+
						            "|"+ stus.get(i).get_class() + "\t" + 
						            "|"+ stus.get(i).getPhone()  + "\t\t" + 
						            "|"+ stus.get(i).getR_date() + "\t\t\t" +   
						            "|"+ stus.get(i).getU_date() + "\t\t\t" 
						            );
				Idflag = true;
				break;
			}
		}*/
		int low = 0;
		int high = len;
		while (low <= high) {
			int mid = low + ((high - low) >> 1);//���������
			if (stus.get(mid).getId() == id) {
				System.out.println("----------------------------------------------------------ѧ����Ϣ------------------------------------------------------");
				System.out.println("|ѧ��\t|����\t|�Ա�\t|����\t|����\t|�༶\t|�绰\t\t|ע��ʱ��\t\t\t|�޸�ʱ��\t\t\t");
				System.out.println("|"+ stus.get(mid).getId() + "\t"+
						            "|"+ stus.get(mid).getName() + "\t"+   
						            "|"+ stus.get(mid).getSex() + "\t"+
						            "|"+ stus.get(mid).getAge() + "\t"+
						            "|"+ stus.get(mid).getScore() + "\t"+
						            "|"+ stus.get(mid).get_class() + "\t" + 
						            "|"+ stus.get(mid).getPhone()  + "\t" + 
						            "|"+ stus.get(mid).getR_date() + "\t\t" +   
						            "|"+ stus.get(mid).getU_date() + "\t\t" + "|"
						            );
				Idflag = true;
				break;
			}else if (stus.get(mid).getId() < id) {
				low = mid + 1;
			}else {
				high = mid - 1;
			}
		}
		if (!Idflag) {
			throw new noIdException("���� �������id�����ڣ�����");
		}
	}
	
	@Override
	public String isGander(int choice) {
		// TODO Auto-generated method stub
		if (choice == 1) {
			return Gander.MAN.getGander();
		}
		if (choice == 2) {
			return Gander.WOMAN.getGander();
		}
		return null;
	}
	@Override
	public String isGrade(int choice) {
		// TODO Auto-generated method stub
		if (choice == 1) {
			return Grade.PRIMARY.getGrade();
		}
		if (choice == 2) {
			return Grade.MIDDLE.getGrade();
		}
		if (choice == 3) {
			return Grade.SENIOR.getGrade();
		}
		return null;
	}
	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		Date date = new Date();
		String _date;
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		_date = format.format(date);
		return _date;
	}
	@Override
	public void Positive() {
		// TODO Auto-generated method stub
		int len = stus.size();
		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < len - i - 1; ++j) {
				if (stus.get(j).getScore() > stus.get(j + 1).getScore()) {
					Collections.swap(stus, j, j + 1);
				}
			}
		}
		checkAll();
	}
	@Override
	public void Inverted() {
		// TODO Auto-generated method stub
		int len = stus.size();
		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < len - i - 1; ++j) {
				if (stus.get(j).getScore() > stus.get(j + 1).getScore()) {
					//�����������ݵ�λ��
					Collections.swap(stus, j, j + 1);
				}
			}
		}
		checkAll();
	}
	@Override
	public void doubleRow() {
		// TODO Auto-generated method stub
		Collections.sort(stus);
		checkAll();
	}
	

	

	
	

	
	
	
	/*@Override
	public boolean searchId(int id) {
		// TODO Auto-generated method stub
		boolean idFlag = false;//Ĭ��id������
		int len = stus.size();
		for (int i = 0; i < len; ++i) {
			Student stu = stus.get(i);
			if (stu == null) {
				continue;
			}
			if (stu.getId() == id) {
				idFlag = true;
				break;
			}
		}
		return idFlag;
	}*/
	

}
