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
		if (username == null || username.equals("")) {//У���û����Ƿ�Ϊ��
			return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_NOT_NULL.getType(),ConstEnum.USERNAME_NOT_NULL.getDesc());
		}else if (isexitusername) {//У���û����Ƿ����
			Admin admin = admins.get(username);
			String _username = admin.getUsername();
			String _password = admin.getPassword();
			if (_username.equals(username)) {
				if (password == null || password.equals("")) {//У�������Ƿ�Ϊ��
					return ServerResponse.creatResponseByFail(ConstEnum.PASSWORD_NOT_NULL.getType(), ConstEnum.PASSWORD_NOT_NULL.getDesc());
				}else if (_password.equals(password)) {//������û�������ȷ����½�ɹ�
					return ServerResponse.creatResponseBySucess(ConstEnum.LOGIN_SUCCES.getDesc(),admin);
				}else {//У���û����Ƿ���ȷ
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
		if (username == null || username.equals("")) {//У���û����Ƿ�Ϊ��
			return ServerResponse.creatResponseByFail(ConstEnum.USERNAME_NOT_NULL.getType(),ConstEnum.USERNAME_NOT_NULL.getDesc());
		}else if (!isexitusername) {//У���û����Ƿ����
					if (password != null && password2 != null && password.equals(password2) && !password.equals("") && !password2.equals("")) {
						if (email == null || email.equals("")) {//У�������Ƿ�Ϊ��
							return ServerResponse.creatResponseByFail(ConstEnum.EMAIL_NOT_NULL.getType(), ConstEnum.EMAIL_NOT_NULL.getDesc());
						}else {//����ȷ��ע��ɹ�
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
	 * �ж��û����Ƿ�Ϊ��
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
	 * �ж��û����Ƿ����
	 * */
	@Override
	public boolean isExistUsername(String username) {
		// TODO Auto-generated method stub
		return admins.containsKey(username);
	}

	

}
