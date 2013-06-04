package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Auction;
import domain.AuctionView;
import domain.Library;
import domain.Objectvalue;
import exceptions.PersonNotFoundException;

public class joinShowAuctions {
	
	public String execute(HttpServletRequest req, HttpServletResponse response){
		System.out.println("show list for join");
		int personId=Integer.parseInt(req.getParameter("personId") );
		Objectvalue ov=new Objectvalue();
		ov.setPersonId(personId);
		req.setAttribute("personId",personId);
		
		List<AuctionView> auctions=null;
		try {
			System.out.println("befor Library.searchAllAvailableAuctionsOfPerson(ov).getFindedAuctions();");
			auctions = Library.searchAllAvailableAuctionsOfPerson(ov).getFindedAuctions();
			System.out.println("after Library.searchAllAvailableAuctionsOfPerson(ov).getFindedAuctions();");
			
		} catch (PersonNotFoundException e) {
			System.out.print("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			
			e.printStackTrace();
		}
		for(AuctionView au : auctions){
			if(au==null)
				System.out.print("nULLLLLLLLLLLLLLLLLLLL");
		}
		System.out.print("after joiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiin"+auctions.size());
		req.setAttribute("auctions",auctions );
		req.setAttribute("message", "please select your choice!");
		System.out.println("end show list "+auctions.size()
				
				);
		return "joinAuctionList.jsp";
		
	}

}
