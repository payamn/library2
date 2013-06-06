package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.model.Library;


public class CloseAuction {

	public String execute(HttpServletRequest req, HttpServletResponse response) {
		
		System.out.print("CloseAuction start .");
		int auctionId=Integer.parseInt(req.getParameter("auctionId"));
		System.out.println("111");
		int sellerId=Integer.parseInt(req.getParameter("personId"));
		System.out.println("2222");
		int winnerId=Integer.parseInt(req.getParameter("winnerId"));
		System.out.println("3333");
		req.setAttribute("personId",sellerId);
		
		try{
			Library.finishAuction(sellerId,winnerId,auctionId);
			req.setAttribute("message", "auction has been closed successfully !");
		
		}catch(PersonNotFoundException e){
			req.setAttribute("message", e.toString());
				
		}catch(AuctionNotFoundException e){
			req.setAttribute("message", e.toString());
				
		}catch(closeTimeException e){
			req.setAttribute("message", e.toString());
		}
		return "closeShowAuctions.action";
	}
}
