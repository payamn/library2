package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Auction;

import domain.Library;

import exceptions.PersonNotFoundException;

public class closeShowAuctions  {

	public String execute(HttpServletRequest req, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		int ownerId=Integer.parseInt(req.getParameter("personId"));
		req.setAttribute("personId", ownerId);
		/*Objectvalue ov=new Objectvalue();
		ov.setSellerId(ownerId);//******/
		String msg=(String) req.getAttribute("message");
		List<Auction> auctions=null;
		
		try {
			auctions = Library.getActiveAuctionByOwner(ownerId);
			if(!(msg==null||msg==""||msg.length()==0))
				req.setAttribute("message", "please select your choice!");
			req.setAttribute("auctions", auctions);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			req.setAttribute("message", e.toString());
			
		} 

		
		
		return "CloseAuctionList.jsp";

	}
}
