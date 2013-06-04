package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Library;

import exceptions.PersonNotFoundException;

public class Login{


	public String execute(HttpServletRequest req, HttpServletResponse response)  {
	
		
		try{
			req.setAttribute("personId", Library.getIdByMail(req.getParameter("email")));
			
		
		}catch(PersonNotFoundException e){
			req.setAttribute("personId",-1);

	
		}
		//writer.print("{personId:"+libContainer.getLib().getIdByMail(req.getParameter("email"),req.getParameter("password"))+"}"     );
		
		return "Bookstore.jsp";
		
		
		
	}
	

}
