package com.lili.service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Admin;

public interface IAdminService {
	public ServerResponse<Admin> login(String username,String password);
	public ServerResponse<Admin> register(String username,String password,String password2,String email);
	public boolean isExistUsername(String username);
	public boolean isNullUsername(String username);
}
