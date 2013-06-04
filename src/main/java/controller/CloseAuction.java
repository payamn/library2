package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Library;

import exceptions.AuctionNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.closeTimeException;

public class CloseAuction {


	// ma midoonim kodoomo darim close mikonim!
	public String execute(HttpServletRequest req, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.print("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		int auctionId=Integer.parseInt(req.getParameter("auctionId"));
		System.out.println("111");
		int sellerId=Integer.parseInt(req.getParameter("personId"));
		System.out.println("2222");
		int winnerId=Integer.parseInt(req.getParameter("winnerId"));
		System.out.println("3333");
		req.setAttribute("personId",sellerId);
		
		//int sellerId,int personId,int auctionId)
		
		/*obj.setAuctionId(auctionId);
		obj.setPersonId(winnerId);
		obj.setSellerId(sellerId);*/
		try{
			System.out.print("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
			Library.finishAuction(sellerId,winnerId,auctionId);
		
			System.out.print("Oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
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
