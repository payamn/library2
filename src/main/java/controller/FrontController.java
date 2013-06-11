package controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dfd");
		System.out.println("front controllerdfsdlfjldsjf");
	/*	System.out.println("*"+request.getServletPath()+"*");
		int index=request.getServletPath().indexOf(".action");
		String className =null;
		try {
			Class<?> ctrlClass = null;
			if(index<0) {
				if (request.getServletPath().indexOf("Logout")>=0){
					request.getSession().invalidate();
					response.sendRedirect("/library2/login.jsp");
				}
					
				ctrlClass = Class.forName("controller.home");
			}
			else {
				className= request.getServletPath().substring(1, index);
				ctrlClass = Class.forName("controller." + className);
			}
			
			Method m = ctrlClass.getMethod("execute", HttpServletRequest.class, HttpServletResponse.class);
			String forward = (String)m.invoke(ctrlClass.newInstance(), request, response);
			
			request.getRequestDispatcher(forward).forward(request, response);
			
		} catch (Exception ex) {
			response.setContentType("text/html");
			response.getOutputStream().println("There is an error in loading the page .\nSee FrontController.java in controller package ." + ex.getMessage());
		}*/
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
