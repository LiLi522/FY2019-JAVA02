package com.lili.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lili.common.ChoiseCourseEnum;
import com.lili.common.ConstEnum;
import com.lili.common.OperStudentEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Admin;
import com.lili.pojo.Student;
import com.lili.service.IStudentServer;
import com.lili.service.impl.StudentServerImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStudentServer iss = StudentServerImpl.getInstance(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		String operation = request.getParameter("operation");
		if (operation == null || operation.equals("")) {
			//返回客户端，必须传入参数
			ServerResponse<Admin> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_NOT_NULL.getType(), ConstEnum.OPERATION_NOT_NULL.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
		try {
			int _operation = Integer.parseInt(operation);
			if (_operation == OperStudentEnum.ADD.getOper_type()) {//增加学生信息
				//获取id id必须要填写 否则就会抛出异常
				Integer id = Integer.parseInt(request.getParameter("id"));
				//获取姓名
				String name = request.getParameter("name");
				//获取分数
				int score = Integer.parseInt(request.getParameter("score"));
				//获取电话
				String phone = request.getParameter("phone");
				String password = request.getParameter("password");
				ServerResponse<Student> serverresponse = iss.add(id, name, score, phone,password);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.FINDALL.getOper_type()) {//查看全部学生信息
				ServerResponse<Student> serverresponse = iss.findAll();
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.FINDBYID.getOper_type()) {//根据id查看全部信息
				//获取id
				int id = Integer.parseInt(request.getParameter("id"));
				ServerResponse<Student> serverresponse = iss.findById(id);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
				
			}else if (_operation == OperStudentEnum.UPDATEBYID.getOper_type()) {//根据id修改学生信息
				//获取id
				int id = Integer.parseInt(request.getParameter("id"));
				//获取要修改的属性
				String choise = request.getParameter("choise");
				//获取修改后的值
				String value = request.getParameter("value");
				ServerResponse<Student> serverresponse = iss.updateById(id, choise, value);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.DELBYID.getOper_type()) {//根据id删除学生信息
				//获取id
				int id = Integer.parseInt(request.getParameter("id"));
				ServerResponse<Student> serverresponse = iss.delById(id);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}
		}catch (NumberFormatException e) {
			ServerResponse<Admin> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_IS_NUM.getType(), ConstEnum.OPERATION_IS_NUM.getDesc());
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
