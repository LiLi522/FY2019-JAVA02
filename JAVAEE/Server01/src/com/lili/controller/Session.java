package com.lili.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lili.common.ServerResponse;
import com.lili.service.IAdminService;
import com.lili.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = null;
		String password = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; ++i) {
				Cookie cookie = cookies[i];
				String cookie_name = cookie.getName();
				String cookie_value = cookie.getValue();
				if (cookie_name.equals("username")) {
					username = cookie_value;
				}
				if (cookie_name.equals("password")) {
					password = cookie_value;
				}
			}
		}
		if (username != null && password != null) {
			IAdminService service = AdminServiceImpl.getInstance();
			ServerResponse serverresponse = service.login(username, password);
			response.getWriter().write(serverresponse.objToJson());
			response.getWriter().close();
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
