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
import javax.servlet.http.HttpSession;

import com.lili.common.ConstEnum;
import com.lili.common.OperationEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Admin;
import com.lili.service.IAdminService;
import com.lili.service.impl.AdminServiceImpl;

/**
 * ʵ�ֶԹ���Ա�ĵ�¼��ע�����
 */
@WebServlet("/AdminServlet.do")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAdminService admin = AdminServiceImpl.getInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * ��Ӧ�����ݸ�ʽJSON
	 * JSONͳһ��ʽ��int status ״̬
	 *               String mag ����
	 *               data ����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���ñ����ʽ
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		 
		PrintWriter pw = response.getWriter();
		//operation 1.��¼ 2.ע��
		String operation = request.getParameter("operation");
		if (operation == null || operation.equals("")) {
			//���ؿͻ��ˣ�operation�������봫��
			@SuppressWarnings("unchecked")
			ServerResponse<Admin> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_NOT_NULL.getType(), ConstEnum.OPERATION_NOT_NULL.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
		
		try {
			int _op = Integer.parseInt(operation);
			if (_op == OperationEnum.ADMIN_LOGIN.getOperation_type()) {//��¼����
				//��ȡ�û���
				String username = request.getParameter("username");
				//��ȡ����
				String password = request.getParameter("password");
				ServerResponse<Admin> serverresponse = admin.login(username, password);
				
				if (serverresponse.isSucess()) {
					//��¼�ɹ����Ự�������һ��key=user
					session.setAttribute("user", serverresponse.getData());
					Cookie username_cookie = new Cookie("username",username);
					Cookie password_cookie = new Cookie("password",password);
					username_cookie.setMaxAge(7*24*3600);
					password_cookie.setMaxAge(7*24*3600);
					response.addCookie(username_cookie);
					response.addCookie(password_cookie);
				}
				
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
			}else if (_op == OperationEnum.ADMIN_REGISTER.getOperation_type()) {//ע�����
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String password2 = request.getParameter("password2");
				String email = request.getParameter("email");
				ServerResponse<Admin> serverresponse = admin.register(username, password, password2, email);
				pw.write(serverresponse.objToJson());
				pw.flush();
				pw.close();
				
			}else if (_op == OperationEnum.ADMIN_EXIT.getOperation_type()) {//�˳�����
				session.removeAttribute("user");
				pw.write("�˳��ɹ�");
			}else if (_op == OperationEnum.ADMIN_LOGIN_NUM.getOperation_type()) {
				ServletContext servletcontext = session.getServletContext();
				Integer count = (Integer)servletcontext.getAttribute("total");
				pw.write("����������" + count);
				
			}
		}catch (NumberFormatException e) {
			ServerResponse<Admin> serverresponse = ServerResponse.creatResponseByFail(ConstEnum.OPERATION_IS_NUM.getType(), ConstEnum.OPERATION_IS_NUM.getDesc());
			pw.write(serverresponse.objToJson());
			pw.flush();
			pw.close();
		}
	}

}
