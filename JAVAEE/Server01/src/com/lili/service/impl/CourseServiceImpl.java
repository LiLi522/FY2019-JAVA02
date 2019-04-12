package com.lili.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lili.common.ConstEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Course;
import com.lili.service.ICourseService;

public class CourseServiceImpl implements ICourseService {
	
	private static CourseServiceImpl instance;

	Collection<Course> cs = course.values();
	
	private CourseServiceImpl() {
		Course course1 = new Course(1, "java");
		Course course2 = new Course(2, "phthon");
		Course course3 = new Course(3, "C++");
		Course course4 = new Course(4, "GO");
		
		course.put(course1.getCname(), course1);
		course.put(course2.getCname(), course2);
		course.put(course3.getCname(), course3);
		course.put(course4.getCname(), course4);
	}
	
	public static synchronized CourseServiceImpl getInstance() {
		if (instance == null) {
			return new CourseServiceImpl();
		}
		return instance;
	}
	
	@Override
	public ServerResponse<Course> addCourse(int cid, String cname) {
		// TODO Auto-generated method stub
		if (isExistId(cid)) {
			return ServerResponse.creatResponseByFail(ConstEnum.CID_EXIST.getType(), ConstEnum.CID_EXIST.getDesc());
		}else if (cname == null || cname.equals("")) {
			return ServerResponse.creatResponseByFail(ConstEnum.CNAME_NOT_NULL.getType(), ConstEnum.CNAME_NOT_NULL.getDesc());
		}else if (isExistCname(cname)) {
			return ServerResponse.creatResponseByFail(ConstEnum.CANME_IS_EXIST.getType(), ConstEnum.CANME_IS_EXIST.getDesc());
		}else{
			course.put(cname, new Course(cid,cname));
			return ServerResponse.creatResponseBySucess();
		}
	}

	@Override
	public ServerResponse<Course> updateCoures(int cid, String cname) {
		// TODO Auto-generated method stub
		Iterator<Course> iterator = cs.iterator();
		while(iterator.hasNext()) {
			Course c = iterator.next();
			if (c.getCid() == cid) {
				c.setCname(cname);
				return ServerResponse.creatResponseBySucess();
			}
		}
		return ServerResponse.creatResponseByFail(ConstEnum.CID_NOT_EXIST.getType(), ConstEnum.CID_NOT_EXIST.getDesc());
		
	}

	@Override
	public ServerResponse<Course> delCourse(int cid) {
		// TODO Auto-generated method stub
		Iterator<Course> iterator = cs.iterator();
		while(iterator.hasNext()) {
			Course c = iterator.next();
			if (c.getCid() == cid) {
				String cname = c.getCname();
				course.remove(cname);
				return ServerResponse.creatResponseBySucess();
			}
		}
		return ServerResponse.creatResponseByFail(ConstEnum.CID_NOT_EXIST.getType(), ConstEnum.CID_NOT_EXIST.getDesc());
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Course> findCourse() {
		// TODO Auto-generated method stub
		return ServerResponse.creatResponseBySucess("查询成功", cs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServerResponse<Course> findCourseById(int cid) {
		// TODO Auto-generated method stub
		Iterator<Course> iterator = cs.iterator();
		while (iterator.hasNext()) {
			Course c = iterator.next();
			if (c.getCid() == cid) {
				
				return ServerResponse.creatResponseBySucess("查询成功", c);
			}
		}
		return ServerResponse.creatResponseByFail(ConstEnum.CID_NOT_EXIST.getType(), ConstEnum.CID_NOT_EXIST.getDesc());
	}

	@Override
	public boolean isExistId(int cid) {
		// TODO Auto-generated method stub
		boolean idFlag = false;
		Iterator<Course> iterator = cs.iterator();
		while (iterator.hasNext()) {
			Course c = iterator.next();
			if (c.getCid() == cid) {
				idFlag = true;
				break;
			}
		}
		return idFlag;
	}
	@Override
	public boolean isExistCname(String cname) {
		// TODO Auto-generated method stub
		return course.containsKey(cname);
	}
}
