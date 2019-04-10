package com.lili.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lili.common.ConstEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Student;
import com.lili.service.IStudentServer;

public class StudentServerImpl implements IStudentServer {
	private static StudentServerImpl studentserverimpl;
	Map<Integer,Student> stus = new HashMap<Integer,Student>();
	
	private StudentServerImpl() {
		stus.put(1, new Student(1,"三张",90,"111111"));
		stus.put(2, new Student(2,"四李",90,"111111"));
		stus.put(3, new Student(3,"五王",90,"111111"));
	}
	public static synchronized StudentServerImpl getInstance() {
		if (studentserverimpl == null) {
			return new StudentServerImpl();
		}
		return studentserverimpl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Student> add(int id, String name, int score, String phone) {
		// TODO Auto-generated method stub
		String _id = String.valueOf(id);
		String _score = String.valueOf(score);
		if (_id.equals("")) {//id不能为空
			return ServerResponse.creatResponseByFail(ConstEnum.ID_NOT_NULL.getType(), ConstEnum.ID_NOT_NULL.getDesc());
		}else if (name.equals("")) {
			return ServerResponse.creatResponseByFail(ConstEnum.NAME_NOT_NULL.getType(), ConstEnum.NAME_NOT_NULL.getDesc());
		}else if (_score.equals("")){
			return ServerResponse.creatResponseByFail(ConstEnum.SCORE_NOT_NULL.getType(), ConstEnum.SCORE_NOT_NULL.getDesc());
		}else if (phone.equals("")) {
			return ServerResponse.creatResponseByFail(ConstEnum.PHONE_NOT_NULL.getType(), ConstEnum.PHONE_NOT_NULL.getDesc());
		}else{
			if (isIdExist(id)) {//存在Id
				return ServerResponse.creatResponseByFail(ConstEnum.ID_EXIST.getType(), ConstEnum.ID_EXIST.getDesc());
			}else {
				stus.put(id, new Student(id, name, score, phone));
				return ServerResponse.creatResponseBySucess();
			}
		}
	}
	@Override
	public boolean isIdExist(int id) {
		// TODO Auto-generated method stub
		return stus.containsKey(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Student> findAll() {
		// TODO Auto-generated method stub
		return ServerResponse.creatResponseBySucess("查询成功", stus);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Student> findById(int id) {
		// TODO Auto-generated method stub
		if (isIdExist(id)) {
			return ServerResponse.creatResponseBySucess("查询成功", stus.get(id));
		}else {
			return ServerResponse.creatResponseByFail(ConstEnum.ID_NOT_EXIST.getType(), ConstEnum.ID_NOT_EXIST.getDesc());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Student> updateById(int id,String choise,String value) {
		// TODO Auto-generated method stub
		if (isIdExist(id)) {
			Student stu = stus.get(id);
			if (choise.equals("name")) {
				stu.setName(value);
				return ServerResponse.creatResponseBySucess("修改成功", stus.get(id));
			}else if (choise.equals("score")) {
				stu.setScore(Integer.parseInt(value));
				return ServerResponse.creatResponseBySucess("修改成功", stus.get(id));
			}else if (choise.equals("phone")) {
				stu.setPhone(value);
				return ServerResponse.creatResponseBySucess("修改成功", stus.get(id));
			}else {
				return ServerResponse.creatResponseByFail(90, "输入的属性不存在");
			}
			
		}else {
			return ServerResponse.creatResponseByFail(ConstEnum.ID_NOT_EXIST.getType(), ConstEnum.ID_NOT_EXIST.getDesc());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Student> delById(int id) {
		// TODO Auto-generated method stub
		if (isIdExist(id)) {
			stus.remove(id);
			return ServerResponse.creatResponseBySucess("查询成功", stus);
		}else {
			return ServerResponse.creatResponseByFail(ConstEnum.ID_NOT_EXIST.getType(), ConstEnum.ID_NOT_EXIST.getDesc());
		}
	}
	
	

}
