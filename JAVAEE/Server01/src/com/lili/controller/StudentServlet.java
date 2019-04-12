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
			//���ؿͻ��ˣ����봫�����
			ServerResponse<Admin> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_NOT_NULL.getType(), ConstEnum.OPERATION_NOT_NULL.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
		try {
			int _operation = Integer.parseInt(operation);
			if (_operation == OperStudentEnum.ADD.getOper_type()) {//����ѧ����Ϣ
				//��ȡid id����Ҫ��д ����ͻ��׳��쳣
				Integer id = Integer.parseInt(request.getParameter("id"));
				//��ȡ����
				String name = request.getParameter("name");
				//��ȡ����
				int score = Integer.parseInt(request.getParameter("score"));
				//��ȡ�绰
				String phone = request.getParameter("phone");
				String password = request.getParameter("password");
				ServerResponse<Student> serverresponse = iss.add(id, name, score, phone,password);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.FINDALL.getOper_type()) {//�鿴ȫ��ѧ����Ϣ
				ServerResponse<Student> serverresponse = iss.findAll();
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.FINDBYID.getOper_type()) {//����id�鿴ȫ����Ϣ
				//��ȡid
				int id = Integer.parseInt(request.getParameter("id"));
				ServerResponse<Student> serverresponse = iss.findById(id);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
				
			}else if (_operation == OperStudentEnum.UPDATEBYID.getOper_type()) {//����id�޸�ѧ����Ϣ
				//��ȡid
				int id = Integer.parseInt(request.getParameter("id"));
				//��ȡҪ�޸ĵ�����
				String choise = request.getParameter("choise");
				//��ȡ�޸ĺ��ֵ
				String value = request.getParameter("value");
				ServerResponse<Student> serverresponse = iss.updateById(id, choise, value);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_operation == OperStudentEnum.DELBYID.getOper_type()) {//����idɾ��ѧ����Ϣ
				//��ȡid
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
