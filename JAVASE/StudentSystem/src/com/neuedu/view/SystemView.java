package com.neuedu.view;

import com.neuedu.exception.loginException;

public interface SystemView {
	void welcome();//��ӭ����
	void login()throws loginException;//��¼����
	void operate();
	void operate1();//���β���
	void checkStu();//��ѯ����
	void appendStu();//�������
	void deleteStu();//ɾ������
	void modifyStu();//�޸Ľ���
}
