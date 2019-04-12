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
		//����cid��ѯ�γ�
		ServerResponse<Course> serverresponse = courseservice.findCourseById(cid);
		if (serverresponse.isSucess()){
			//��ȡ��ѯ�Ŀγ�
			Course c=serverresponse.getData();
			//���γ���ӵ����ﳵ
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
		return ServerResponse.creatResponseBySucess("���ҳɹ�", cs);
	}

	@Override
	public ServerResponse findChoiseCourse(List<Course> course) {
		// TODO Auto-generated method stub
		if (course.isEmpty()) {
			return ServerResponse.creatResponseByFail(ConstEnum.CART_IS_NULL.getType(), ConstEnum.CART_IS_NULL.getDesc());
		}else {
			return ServerResponse.creatResponseBySucess("��ѯ�ɹ�", course);
		}
		
	}

}
