package com.neuedu;
import com.neuedu.view.SystemView;
import com.neuedu.view.impl.SystemViewImpl;

public class Application {
	public static void main(String[] args){
	SystemView student = new SystemViewImpl();
	student.welcome();//��ӭ��½����
	student.operateLogin();//��½����
	student.operateMenu();//��ѯ����
	//student.appendStu();//���
	//student.deleteStu();//ɾ��
	//student.modifyStu();//�޸�
	}
}
