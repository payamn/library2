package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;
import domain.model.Library;


public class JoinAuction {
	public String execute(HttpServletRequest req, HttpServletResponse response) throws ParseException {
		
		int personId=Integer.parseInt(req.getParameter("personId") );
		int auctionId=Integer.parseInt(req.getParameter("auctionId"));
		int price=Integer.parseInt(req.getParameter("price"));
		String sourceOfReq=req.getParameter("sourceOfReq");
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyour price "+price);
		req.setAttribute("personId",personId);

		try {
			System.out.print("joinshYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYsssssssssssss");
			Library.joinToAuction(personId,auctionId,price);
			req.setAttribute("message", "your have been joined to auction successfully! ");
			System.out.print("joinshssssssssssssssssssssssssssssssssssssssssssssss");
		} catch (AuctionNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( priceException e){
			req.setAttribute("message", e.toString());
		
		}catch (closeTimeException e){
			req.setAttribute("message", "erorr : This auction has been closed before! ");
		}
		System.out.print("JOINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEd");
		if(!sourceOfReq.equalsIgnoreCase("Bookstore.jsp")){
			System.out.print("BBBBBBBBNNNNNNNNNNNNNNNNNNNNNNNBBBBBBBBBBBBBBBBack to BookStor.jsp");
			return  "joinShowAuctions.action";	
		}
		else{
			
			System.out.print("BBBBBBBBBBBBBBBBBBBBBBBBack to BookStor.jsp");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			req.setAttribute("auctions", Library.getRecentlyAddedAuctions(sdf.parse("01-05-2013")));
			return "Bookstore.jsp";
					
		}
	}
}
