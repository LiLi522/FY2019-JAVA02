package com.lili.service;

import java.util.HashMap;
import java.util.Map;

import com.lili.common.ServerResponse;
import com.lili.pojo.Course;

public interface ICourseService {
	Map<String,Course> course = new HashMap<String,Course>();
	public ServerResponse<Course> addCourse(int cid,String cname);
	public ServerResponse<Course> updateCoures(int cid,String cname);
	public ServerResponse<Course> delCourse(int cid);
	public ServerResponse<Course> findCourse();
	public ServerResponse<Course> findCourseById(int cid);
	public boolean isExistId(int cid);
	public boolean isExistCname(String cname);
}
