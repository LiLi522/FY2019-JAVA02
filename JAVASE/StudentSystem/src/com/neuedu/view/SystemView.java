package com.neuedu.view;

import com.neuedu.exception.loginException;

public interface SystemView {
	void welcome();//欢迎界面
	void login()throws loginException;//登录界面
	void operate();
	void operate1();//二次操作
	void checkStu();//查询界面
	void appendStu();//插入界面
	void deleteStu();//删除界面
	void modifyStu();//修改界面
}
