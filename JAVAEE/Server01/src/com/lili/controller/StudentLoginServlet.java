package com.lili.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lili.common.ChoiseCourseEnum;
import com.lili.common.ConstEnum;
import com.lili.common.OperStudentEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Course;
import com.lili.pojo.Student;
import com.lili.service.IStudentServer;
import com.lili.service.impl.StudentServerImpl;

/**
 * Servlet implementation class StudentLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    IStudentServer iss = StudentServerImpl.getInstance(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		//operation 1.登录 2.退出
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
			if (_op == OperStudentEnum.LOGIN.getOper_type()) {//学生登录
				String name = request.getParameter("name");
				String password = request.getParameter("password");
		
				ServerResponse<Student> serverresponse = iss.login(name, password);
				if (serverresponse.isSucess()) {
					session.setAttribute("student", serverresponse.getData());
					session.setAttribute("cart", new ArrayList<Course>());
				}
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_op == ChoiseCourseEnum.FIND_COURSE.getType()) {//退出
				
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
