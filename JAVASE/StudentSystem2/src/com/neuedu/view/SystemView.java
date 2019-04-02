package com.neuedu.view;

import com.neuedu.exception.loginException;

public interface SystemView {
	void welcome();//欢迎界面
	void login()throws loginException;//登录界面
	void Code();
	void operateLogin();//登录操作
	void operateMenu();//菜单操作
	void checkStu();//查询界面
	void checkOfId();//根据id查询界面
	void checkScore();//根据id查询姓名界面
	void appendStu();//添加界面
	void addStu();//添加操作
	void deleteStu();//删除界面
	void deleteOfId();//根据id删除
	void modifyStu();//修改界面
	void modifyOfId();
}
