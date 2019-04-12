package com.lili.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.lili.common.ServerResponse;
import com.lili.pojo.Student;

public interface IStudentServer {
	public boolean isIdExist(int id);
	public ServerResponse<Student> add(int id,String name,int score,String phone,String password);
	public ServerResponse<Student> findAll();
	public ServerResponse<Student> findById(int id);
	public ServerResponse<Student> updateById(int id,String choise,String value);
	public ServerResponse<Student> delById(int id);
	public ServerResponse<Student> login(String name,String password);
	Map<Integer,Student> stus = new HashMap<Integer,Student>();
	Collection<Student> st = stus.values();
}
