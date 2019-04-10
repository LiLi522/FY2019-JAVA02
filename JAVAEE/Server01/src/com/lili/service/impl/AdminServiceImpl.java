package com.lili.service.impl;


import java.util.HashMap;

import java.util.Map;

import com.lili.common.ConstEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Admin;
import com.lili.service.IAdminService;



public class AdminServiceImpl implements IAdminService {
	private static AdminServiceImpl adminserviceimpl;
	Map<String,Admin> admins = new HashMap<String,Admin>();
	
	private AdminServiceImpl() {
		Admin admin1 = new Admin("admin","admin");
		Admin admin2 = new Admin("xxxx","xxxx");
		admins.put(admin1.getUsername(), admin1);
		admins.put(admin2.getUsername(), admin2);
		
	}
	
	public static synchronized AdminServiceImpl getInstance() {
		if (adminserviceimpl == null) {
			return new AdminServiceImpl();
		}
		return adminserviceimpl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Admin> login(String username, String password) {
		// TODO Auto-generated method stub
		boolean isexitusername = isExistUsername(username);
		if (username == null || username.equals("")) {//校验用户名是否为空
			return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_NOT_NULL.getType(),ConstEnum.USERNAME_NOT_NULL.getDesc());
		}else if (isexitusername) {//校验用户名是否存在
			Admin admin = admins.get(username);
			String _username = admin.getUsername();
			String _password = admin.getPassword();
			if (_username.equals(username)) {
				if (password == null || password.equals("")) {//校验密码是否为空
					return ServerResponse.creatResponseByFail(ConstEnum.PASSWORD_NOT_NULL.getType(), ConstEnum.PASSWORD_NOT_NULL.getDesc());
				}else if (_password.equals(password)) {//密码和用户名都正确，登陆成功
					return ServerResponse.creatResponseBySucess();
				}else {//校验用户名是否正确
					return ServerResponse.creatResponseByFail(ConstEnum.PASSWORD_NOT_TRUE.getType(), ConstEnum.PASSWORD_NOT_TRUE.getDesc());
				}
			}
		}else {
			return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_NOT_EXIST.getType(), ConstEnum.USERNAME_NOT_EXIST.getDesc());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Admin> register(String username, String password, String password2, String email) {
		// TODO Auto-generated method stub
		boolean isexitusername = isExistUsername(username);
		if (username == null || username.equals("")) {//校验用户名是否为空
			return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_NOT_NULL.getType(),ConstEnum.USERNAME_NOT_NULL.getDesc());
		}else if (!isexitusername) {//校验用户名是否存在
					if (password != null && password2 != null && password.equals(password2) && !password.equals("") && !password2.equals("")) {
						if (email == null || email.equals("")) {//校验邮箱是否为空
							return ServerResponse.creatResponseByFail(ConstEnum.EMAIL_NOT_NULL.getType(), ConstEnum.EMAIL_NOT_NULL.getDesc());
						}else {//都正确，注册成功
							admins.put(username, new Admin(username, password));
							return ServerResponse.creatResponseBySucess();
						}
					}else if (!password.equals(password2)) {
						return ServerResponse.creatResponseByFail(ConstEnum.PASSWORD_NOT_SAME.getType(), ConstEnum.PASSWORD_NOT_SAME.getDesc());
					}else {
						return ServerResponse.creatResponseByFail(ConstEnum.PASSWORD_NOT_NULL.getType(), ConstEnum.PASSWORD_NOT_NULL.getDesc());
					}
				}else {
					return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_EXIST.getType(), ConstEnum.USERNAME_EXIST.getDesc());
				}
	}

	/**
	 * 判断用户名是否为空
	 * */
	@Override
	public boolean isNullUsername(String username) {
		// TODO Auto-generated method stub
		Admin admin = admins.get(username);
		String _username = admin.getUsername();
		if (_username == null || _username.equals("")) {
			return true;
		}
		return false;
	}
	/**
	 * 判断用户名是否存在
	 * */
	@Override
	public boolean isExistUsername(String username) {
		// TODO Auto-generated method stub
		return admins.containsKey(username);
	}

	

}
