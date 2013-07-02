package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.exceptions.PersonNotFoundException;
import domain.model.Auction;
import domain.model.Library;
import domain.model.Person;


public class Login{
	
	public String execute(HttpServletRequest req, HttpServletResponse response) throws ParseException  {	
		try{
			System.out.println("in login");
			Person person=Library.getPersonByMail(req.getParameter("email"));
			System.out.print("3");
			req.setAttribute("personId",person.getId());
			System.out.print("4");
			req.setAttribute("name", person.getName());
			System.out.print("5");
			
			req.setAttribute("message", "choice your book!!!!!!!! ");
			System.out.print("6");
		}catch(PersonNotFoundException e){
			System.out.print("exception chatched");
			req.setAttribute("personId",-1);
		}
		System.out.print("after try catch!");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//List<Auction> auctions=new ArrayList<Auction> ();
		List<Auction> auctions=Library.getRecentlyAddedAuctions(sdf.parse("01-05-2013"));

		req.setAttribute("auctions", auctions);
		System.out.print("end of login !");
		return "Bookstore.jsp";	
	}
}
