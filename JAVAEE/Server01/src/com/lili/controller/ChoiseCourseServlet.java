package com.lili.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lili.common.ChoiseCourseEnum;
import com.lili.common.ConstEnum;
import com.lili.common.CourseOperationEnum;
import com.lili.common.OperStudentEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Course;
import com.lili.pojo.Student;
import com.lili.service.IStudentChoiseCourseService;
import com.lili.service.impl.StudentChoiseCourseServiceImpl;

/**
 * Servlet implementation class ChoiseCourseServlet
 */
@WebServlet("/ChoiseCourseServlet")
public class ChoiseCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoiseCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		List<Course> courses = (List<Course>)session.getAttribute("cart");
		Student student = (Student)session.getAttribute("student");
		IStudentChoiseCourseService service=new  StudentChoiseCourseServiceImpl();
		PrintWriter pw = response.getWriter();
		//operation 1.选课 2.查看所选课程
		String operation = request.getParameter("operation");
		if (operation == null || operation.equals("")) {
			//返回客户端，operation参数必须传递
			@SuppressWarnings("unchecked")
			ServerResponse<Course> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_NOT_NULL.getType(), ConstEnum.OPERATION_NOT_NULL.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
		
		try {
			int _op = Integer.parseInt(operation);
			if (_op == ChoiseCourseEnum.CHOISE_COURSE.getType()) {//学生选课
				//学生选课
			 	 
			 	String cid=request.getParameter("cid");
				ServerResponse serverResponse= service.choiseCourse(Integer.parseInt(cid), courses);
				pw.write(serverResponse.objToJson());
				pw.close();
			}else if (_op == ChoiseCourseEnum.FIND_COURSE.getType()) {//学生查看选择的课程
				ServerResponse serverResponse= service.findChoiseCourse(courses);
				pw.write(serverResponse.objToJson());
				pw.close();
			}else if (_op == ChoiseCourseEnum.DEL_COURSE.getType()) {
				//学生删除所选课程
			 	String cid=request.getParameter("cid");
				ServerResponse serverResponse= service.delCourse(Integer.parseInt(cid), courses);
				pw.write(serverResponse.objToJson());
				pw.close();
			}else if (_op == ChoiseCourseEnum.FIND_ALL_COURSE.getType()) {
				ServerResponse serverResponse= service.findAll();
				pw.write(serverResponse.objToJson());
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
