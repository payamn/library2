package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.exceptions.PersonNotFoundException;
import domain.model.Auction;
import domain.model.Library;


public class joinShowAuctions {
	
	public String execute(HttpServletRequest req, HttpServletResponse response){
		System.out.println("show list for join");
		int personId=Integer.parseInt(req.getParameter("personId") );
		req.setAttribute("personId",personId);
		List<Auction> auctions=null;
		try {
			System.out.println("befor Library.searchAllAvailableAuctionsOfPerson(ov).getFindedAuctions();");
			auctions = Library.searchAllAvailableAuctionsOfPerson(personId);
			System.out.println("after Library.searchAllAvailableAuctionsOfPerson(ov).getFindedAuctions();");	
		} catch (PersonNotFoundException e) {
			System.out.print("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");	
			e.printStackTrace();
		}
		for(Auction au : auctions){
			if(au==null)
				System.out.print("nULLLLLLLLLLLLLLLLLLLL");
		}
		System.out.print("after joiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiin"+auctions.size());
		req.setAttribute("auctions",auctions );
		if(req.getAttribute("message")==null)
		req.setAttribute("message", "please select your choice!");
		System.out.println("end show list "+auctions.size());
		
		return "joinAuctionList.jsp";
	}
}
