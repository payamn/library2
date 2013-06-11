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
		System.out.println("front controller   dfsdlfjldsjf");
		System.out.println("*"+request.getServletPath()+"*");
		int index=request.getServletPath().indexOf(".action");
		String className =null;
		try {
			System.out.println("in try statement");
			Class<?> ctrlClass = null;
			if(index<0) {
				System.out.println("in first if");
				if (request.getServletPath().indexOf("Logout")>=0){
					request.getSession().invalidate();
					System.out.println("logout");
					String re="/library2/Bookstore.jsp";
					System.out.println(re);
					response.sendRedirect(re);
				
					System.out.println("after");
				}
				System.out.println("after second if");
				ctrlClass = Class.forName("controller.home");
			}
			else {
				System.out.println("first else");
				className= request.getServletPath().substring(1, index);
				ctrlClass = Class.forName("controller." + className);
			}
			
			Method m = ctrlClass.getMethod("execute", HttpServletRequest.class, HttpServletResponse.class);
			String forward = (String)m.invoke(ctrlClass.newInstance(), request, response);
			
			request.getRequestDispatcher(forward).forward(request, response);
			
		} catch (Exception ex) {
			response.setContentType("text/html");
			response.getOutputStream().println("There is an error in loading the page .\nSee FrontController.java in controller package ." + ex.getMessage());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
