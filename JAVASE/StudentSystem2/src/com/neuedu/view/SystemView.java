package com.neuedu.view;

import com.neuedu.exception.loginException;

public interface SystemView {
	void welcome();//��ӭ����
	void login()throws loginException;//��¼����
	void Code();
	void operateLogin();//��¼����
	void operateMenu();//�˵�����
	void checkStu();//��ѯ����
	void checkOfId();//����id��ѯ����
	void checkScore();//����id��ѯ��������
	void appendStu();//��ӽ���
	void addStu();//��Ӳ���
	void deleteStu();//ɾ������
	void deleteOfId();//����idɾ��
	void modifyStu();//�޸Ľ���
	void modifyOfId();
}
