package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Library;

import exceptions.AuctionNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.closeTimeException;
import exceptions.priceException;

public class JoinAuction {
	public String execute(HttpServletRequest req, HttpServletResponse response) {
		int personId=Integer.parseInt(req.getParameter("personId") );
		int auctionId=Integer.parseInt(req.getParameter("auctionId"));
		int price=Integer.parseInt(req.getParameter("price"));
		req.setAttribute("personId",personId);
		
		//(int personId,int auctionId,int price)
		
		
		
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
			req.setAttribute("message", "erorr : your suggested price is below of minimom price! ");
		
		}catch (closeTimeException e){
			req.setAttribute("message", "erorr : This auction has been closed before! ");
		}
		System.out.print("JOINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNEd");
		
		return  "joinShowAuctions.action";
		
		//return "Bookstore.jsp";
		
		
		
	}

}
