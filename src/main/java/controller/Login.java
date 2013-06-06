package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.exceptions.PersonNotFoundException;
import domain.model.Library;


public class Login{
	
	public String execute(HttpServletRequest req, HttpServletResponse response) throws ParseException  {	
		try{
			req.setAttribute("personId", Library.getIdByMail(req.getParameter("email")));
		}catch(PersonNotFoundException e){
			req.setAttribute("personId",-1);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		req.setAttribute("auctions", Library.getRecentlyAddedAuctions(sdf.parse("01-01-2013")));
		return "Bookstore.jsp";	
	}
}
