package com.lili.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.lili.pojo.Admin;

/**
 * Application Lifecycle Listener implementation class MemberListener
 *
 */
@WebListener
public class MemberListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MemberListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	HttpSession session = event.getSession();
    	ServletContext servletcontext = session.getServletContext();
    	if (event.getName().equals("user")) {
    		//ÓÐÓÃ»§µÇÂ¼
    		Integer total = (Integer)servletcontext.getAttribute("total");
    		if (total == null) {
    			total = new Integer(1);
    			servletcontext.setAttribute("total", total);
    		}else {
    			total++;
    			servletcontext.setAttribute("total", total);
    		}
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	HttpSession session = event.getSession();
    	ServletContext servletcontext = session.getServletContext();
    	Integer total = (Integer)servletcontext.getAttribute("total");
		if (total == null || total < 0) {
			total = 0;
			servletcontext.setAttribute("total", total);
		}else {
			total--;
			servletcontext.setAttribute("total", total);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
