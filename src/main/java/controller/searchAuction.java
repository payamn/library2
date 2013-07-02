package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.model.Auction;
import domain.model.Library;

public class SearchAuction {

	public String execute(HttpServletRequest req, HttpServletResponse response)
			throws IOException {
		System.out.println("in search auction !!!!!!!!!!!!!!");
		String searchType=req.getParameter("searchType");
		List <Auction> auctions = null;
		int personId=(Integer.parseInt(req.getParameter("personId")));
		req.setAttribute("personId",req.getParameter("personId"));
		if(searchType.equals("bookName")){
			String searchedBook=req.getParameter("bookName");
			String bookName=(searchedBook);
			
			//int personId,String bookName
			auctions=Library.searchAuctionByBookName(personId,bookName);
		}
		else if (searchType.equals("bookWriterName")){
			String Name=req.getParameter("writerName");
			String surName=req.getParameter("writerSurname");
			String bookWriter=(Name+" "+surName);

			//int personId,String bookWriter
			auctions=Library.searchAuctionByBookWriter(personId,bookWriter);
		}
		else if(searchType.equals("sellerName")){
			String Name=req.getParameter("sellerName");
			String surName=req.getParameter("selleSurname");
		
			///int personId,String SellerFirstName,String SellerLastName
			auctions=Library.searchAuctionByOwner(personId,Name,surName);
		}	
		System.out.print("sssssssssssssssssssssssssssssssssssssssssize"+auctions.size());
		System.out.println("NUM of auctions "+auctions.size());
		req.setAttribute("auctions",auctions );
		req.setAttribute("message", "please enter your price and press button.");
		req.setAttribute("from", "search");
		return "joinAuctionList.jsp";	
	}
}
