package com.lili.service.impl;

import java.util.Collection;
import java.util.List;

import com.lili.common.ConstEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Course;
import com.lili.service.ICourseService;
import com.lili.service.IStudentChoiseCourseService;

public class StudentChoiseCourseServiceImpl implements IStudentChoiseCourseService {
	ICourseService courseservice = CourseServiceImpl.getInstance();
	@Override
	public ServerResponse<Course> choiseCourse(int cid,List<Course> course) {
		// TODO Auto-generated method stub
		//根据cid查询课程
		ServerResponse<Course> serverresponse = courseservice.findCourseById(cid);
		if (serverresponse.isSucess()){
			//获取查询的课程
			Course c=serverresponse.getData();
			//将课程添加到购物车
			course.add(c);
			
			return ServerResponse.creatResponseBySucess();
		}
		return ServerResponse.creatResponseByFail(ConstEnum.CNAME_NOT.getType(), ConstEnum.CNAME_NOT.getDesc());
	}

	@Override
	public ServerResponse delCourse(int cid,List<Course> course) {
		// TODO Auto-generated method stub
		if (course.isEmpty()) {
			return ServerResponse.creatResponseByFail(ConstEnum.CART_IS_NULL.getType(), ConstEnum.CART_IS_NULL.getDesc());
		}
		for (int i = 0; i < course.size(); ++i) {
			if (course.get(i).getCid() == cid) {
				course.remove(i);
				return ServerResponse.creatResponseBySucess();
			}
		}
		return 	ServerResponse.creatResponseByFail(ConstEnum.CNAME_NOT.getType(), ConstEnum.CNAME_NOT.getDesc());
	}
	public ServerResponse findAll() {
		ServerResponse<Course> serverresponse = courseservice.findCourse();
		Collection<Course> cs = (Collection<Course>) serverresponse.getData();
		return ServerResponse.creatResponseBySucess("查找成功", cs);
	}

	@Override
	public ServerResponse findChoiseCourse(List<Course> course) {
		// TODO Auto-generated method stub
		if (course.isEmpty()) {
			return ServerResponse.creatResponseByFail(ConstEnum.CART_IS_NULL.getType(), ConstEnum.CART_IS_NULL.getDesc());
		}else {
			return ServerResponse.creatResponseBySucess("查询成功", course);
		}
		
	}

}
