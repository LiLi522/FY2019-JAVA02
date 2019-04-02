package com.neuedu.service;

import com.neuedu.exception.noIdException;
import com.neuedu.pojo.Student;
import com.neuedu.exception.codeException;
import com.neuedu.exception.idExistExpection;
import com.neuedu.exception.loginException;

public interface SystemBiz {
	 //public boolean searchId(int id); 
	 public void login(String username,String password)throws loginException;//��¼�쳣����
	 public void Code(String code,String codeP) throws codeException;//������֤����������쳣
	 public String getCode(int size);//��ȡ��֤��
	 public void checkAll();
	 public void checkOfId(int id)throws noIdException;
	 public void appendStu(int id) throws idExistExpection;//id�Ѵ����쳣����
	 public void add(int id, String name,String sex,int age,int score,String _class,String phone,String r_data,String u_data,int _classNum);//���ѧ����Ϣ����
	 public void deleteStu(int id) throws noIdException;
	 public void del(int id);//ɾ��ѧ����Ϣ����
	 public void modifyStu(int id) throws noIdException;
	 public void modifyById(int id,String str);
	 public String isGander(int choice);
	 public String isGrade(int choice);
	 public String getDate();
	 public void Positive();
	 public void Inverted();
	 public void doubleRow();
}
