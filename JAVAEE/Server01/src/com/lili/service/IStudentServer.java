package com.lili.service;

import com.lili.common.ServerResponse;
import com.lili.pojo.Student;

public interface IStudentServer {
	public boolean isIdExist(int id);
	public ServerResponse<Student> add(int id,String name,int score,String phone);
	public ServerResponse<Student> findAll();
	public ServerResponse<Student> findById(int id);
	public ServerResponse<Student> updateById(int id,String choise,String value);
	public ServerResponse<Student> delById(int id);
}
