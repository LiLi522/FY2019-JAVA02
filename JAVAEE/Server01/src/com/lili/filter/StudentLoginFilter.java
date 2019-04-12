package com.lili.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lili.common.ConstEnum;
import com.lili.common.ServerResponse;
import com.lili.pojo.Student;

/**
 * Servlet Filter implementation class StudentLoginFilter
 */
@WebFilter("/ChoiseCourseServlet")
public class StudentLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StudentLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpSession session = _request.getSession();
		Student student = (Student)session.getAttribute("student");
		if (student == null) {
			PrintWriter pw = response.getWriter();
			String responseText = ServerResponse.creatResponseByFail(ConstEnum.NO_LOGIN.getType(), ConstEnum.NO_LOGIN.getDesc()).objToJson();
			pw.write(responseText);
			pw.close();
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
