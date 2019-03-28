package com.neuedu.service;

import com.neuedu.exception.loginException;

public interface SystemBiz {
	 public void login(String username,String password)throws loginException;
	 void checkAll();
	 void checkOfId();
	 void checkIdForName();
}
