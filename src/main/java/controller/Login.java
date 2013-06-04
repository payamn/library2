package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Library;
import domain.Objectvalue;
import exceptions.PersonNotFoundException;

public class Login{


	public String execute(HttpServletRequest req, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		Objectvalue OV=new Objectvalue();
		OV.setMail(req.getParameter("email"));
		try{
			req.setAttribute("personId", Library.getIdByMail(OV));
			
		
		}catch(PersonNotFoundException e){
			req.setAttribute("personId",-1);

	
		}
		//writer.print("{personId:"+libContainer.getLib().getIdByMail(req.getParameter("email"),req.getParameter("password"))+"}"     );
		
		return "Bookstore.jsp";
		
		
		
	}
	

}
