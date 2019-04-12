package com.lili.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lili.common.ConstEnum;
import com.lili.common.CourseOperationEnum;
import com.lili.common.OperationEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Course;
import com.lili.service.ICourseService;
import com.lili.service.impl.CourseServiceImpl;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICourseService courseservice = CourseServiceImpl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		//operation 1.��� 2.�޸� 3.ɾ�� 4.�鿴
		String operation = request.getParameter("operation");
		if (operation == null || operation.equals("")) {
			//���ؿͻ��ˣ�operation�������봫��
			@SuppressWarnings("unchecked")
			ServerResponse<Course> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_NOT_NULL.getType(), ConstEnum.OPERATION_NOT_NULL.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
		
		try {
			int _op = Integer.parseInt(operation);
			if (_op == CourseOperationEnum.COURSE_ADD.getType()) {//��ӿγ�
				String cid = request.getParameter("cid");
				try{
					Integer _cid = Integer.parseInt(cid); 
					String cname = request.getParameter("cname");
					ServerResponse<Course> serverresponse = courseservice.addCourse(_cid, cname);
					pw.write(serverresponse.objToJson());
					pw.close();
				}catch (NumberFormatException e) {
					ServerResponse<Course> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_IS_NUM.getType(), ConstEnum.OPERATION_IS_NUM.getDesc());
					pw.write(serverresponse.objToJson());
					pw.flush();
					pw.close();
				}
			}else if (_op == CourseOperationEnum.COURSE_UPDATE.getType()) {//�޸Ŀγ�
				String cid = request.getParameter("cid");
				Integer _cid = Integer.parseInt(cid); 
				String cname = request.getParameter("cname");
				ServerResponse<Course> serverresponse = courseservice.updateCoures(_cid, cname);
				pw.write(serverresponse.objToJson());
				pw.close();
			}else if (_op == CourseOperationEnum.COURSE_DEL.getType()) {//ɾ���γ�
				String cid = request.getParameter("cid");
				Integer _cid = Integer.parseInt(cid); 
				ServerResponse<Course> serverresponse = courseservice.delCourse(_cid);
				pw.write(serverresponse.objToJson());
				pw.close();
			}else if (_op == CourseOperationEnum.COURSE_FIND.getType()) {//�鿴�γ�
				ServerResponse<Course> serverresponse = courseservice.findCourse();
				pw.write(serverresponse.objToJson());
				pw.close();
				
			}else if (_op == CourseOperationEnum.COURES_FIND_BYID.getType()) {//�鿴�γ�
				String cid = request.getParameter("cid");
				Integer _cid = Integer.parseInt(cid); 
				ServerResponse<Course> serverresponse = courseservice.findCourseById(_cid);
				pw.write(serverresponse.objToJson());
				pw.close();
			}
		}catch (NumberFormatException e) {
			ServerResponse<Course> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_IS_NUM.getType(), ConstEnum.OPERATION_IS_NUM.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
