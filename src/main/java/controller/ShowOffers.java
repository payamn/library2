package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Auction;

import domain.Library;

import exceptions.AuctionNotFoundException;

public class ShowOffers {

	public String execute(HttpServletRequest req, HttpServletResponse response)  {
		int id=Integer.parseInt(req.getParameter("auctionId"));
		//req.setAttribute("personId", arg1)
		req.setAttribute("personId",req.getParameter("personId"));
		
		try{
			Auction auc= Library.getAuctionById(id);
			req.setAttribute("auction",auc);
			if(auc.getOffers().size()==0){
				//return "CloseAuctionList.jsp";
				req.setAttribute("message", "there is no offering!");
				req.setAttribute("auctions", req.getAttribute("auctions"));
				return "CloseAuctionList.jsp";
			}
		}catch(AuctionNotFoundException e){
			req.setAttribute("message", "this auction not found!");
		}
		
		return "CloseAuction.jsp";
		
		
	}
}
