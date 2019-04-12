package com.lili.service;


import java.util.List;

import com.lili.common.ServerResponse;
import com.lili.pojo.Course;


public interface IStudentChoiseCourseService {
	public ServerResponse<Course> choiseCourse(int cid,List<Course> course);
	public ServerResponse delCourse(int cid,List<Course> course);
	public ServerResponse findChoiseCourse(List<Course> course);
	public ServerResponse findAll();
}
