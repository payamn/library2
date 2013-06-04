package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Auction;
import domain.AuctionView;
import domain.Library;

import domain.Objectvalue;

public class searchAuction {


	public String execute(HttpServletRequest req, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		
		String searchType=req.getParameter("searchType");
		List <AuctionView> auctions;
		Objectvalue outputOV = null;
		Objectvalue ov = new Objectvalue();

		ov.setPersonId(Integer.parseInt(req.getParameter("personId")));
		req.setAttribute("personId",req.getParameter("personId"));
		if(searchType.equals("bookName")){
			String searchedBook=req.getParameter("bookName");
			ov.setBookName(searchedBook);
			outputOV=Library.searchAuctionByBookName(ov);
		}
		else if (searchType.equals("bookWriterName")){
			String Name=req.getParameter("writerName");
			String surName=req.getParameter("writerSurname");
			ov.setBookWriter(Name+" "+surName);

			outputOV=Library.searchAuctionByBookWriter(ov);

		}
		else if(searchType.equals("sellerName")){
			String Name=req.getParameter("sellerName");
			String surName=req.getParameter("selleSurname");
			ov.setSellerFirstName(Name);
			ov.setSellerLastName(surName);
			outputOV=Library.searchAuctionByOwner(ov);
		}
		
		auctions= ov.getFindedAuctions();	
		System.out.print("sssssssssssssssssssssssssssssssssssssssssize"+auctions.size());
		System.out.println("NUM of auctions "+auctions.size());
		req.setAttribute("auctions",auctions );
		req.setAttribute("from", "search");
		return "joinAuctionList.jsp";	
	}
}
