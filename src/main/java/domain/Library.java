package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.AuctionNotFoundException;
import exceptions.BookIsExist;
import exceptions.PersonNotFoundException;
import exceptions.closeTimeException;
import exceptions.priceException;
import scheduler.JobScheduler;
import sql.DBConnector;

public class Library {
	public static Objectvalue getAuctionById(Objectvalue ov) throws AuctionNotFoundException {
		Objectvalue OV=new Objectvalue();
        Auction auction=DBConnector.getAuction(ov.getAuctionId());
        List<Auction> auctions =new ArrayList<Auction> ();
        auctions.add(auction);
        OV.setFindedAuctions(auctions);
        return OV;
	}
	public static void  createAuction(Objectvalue OV) throws BookIsExist, PersonNotFoundException {

		List<Book> books = DBConnector.getBooks(OV.getBookName(), OV.getBookWriter(), OV.getPublishyear());
		for(Book b : books) {
			if(b.getAuction().getPerson().getId() == OV.getSellerId())
				throw new BookIsExist("BookIsExist Exception : Book with this owner is exist in DB .");
		}
		
		Book book = new Book(OV.getBookName(), OV.getBookWriter(), OV.getPublishyear(), OV.getQuality());
		Person person = DBConnector.getPerson(OV.getSellerId());
		Auction auction = new Auction(book, OV.getAuctionStartDate(), OV.getAuctionEndDate(), OV.getPrice());
		

		auction.setPerson(person);
		book.setAuction(auction);
		DBConnector.saveAuction(auction);	
	//	JobScheduler.createNewJob(OV.getAuctionEndDate(), auction.getId());
	}
	public static void joinToAuction(Objectvalue OV) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException {
		Auction auction = DBConnector.getAuction(OV.getAuctionId());
		auction.checkValidTime();
		Person person = DBConnector.getPerson(OV.getPersonId());
		auction.checkValidPrice(OV.getPrice());
		Offer offer = new Offer(OV.getPrice(), new Date());
		offer.setAuction(auction);
		offer.setPerson(person);
		DBConnector.saveOffer(offer);
	}
	public static void finishAuction(Objectvalue OV) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException {
		Auction auction = DBConnector.getAuction(OV.getAuctionId());
		auction.checkValidTime();
		Person seller = DBConnector.getPerson(OV.getSellerId());
		seller.increaseRate();
		Person person = DBConnector.getPerson(OV.getPersonId());
		person.increaseRate();
		auction.finishSuccessfulAuction(person);
		DBConnector.saveAuction(auction);
		DBConnector.savePerson(seller);
		DBConnector.savePerson(person);

	}
	public static void finishExpiredAuction(int auctionId) throws AuctionNotFoundException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.getPerson().increaseRate();
		auction.getPerson().increaseRate();
		auction.finishExpiredAuction();
		DBConnector.saveAuction(auction);
		DBConnector.savePerson(auction.getPerson());
	}
	public static Objectvalue searchAuctionByBookName(Objectvalue ov) {
		ov.setFindedAuctions(DBConnector.findAuctionByBookName(ov.getBookName(), ov.getPersonId()));
		return ov;
	}
	public static Objectvalue searchAuctionByBookWriter(Objectvalue ov) {
		ov.setFindedAuctions(DBConnector.findAuctionByWriterName(ov.getBookWriter(), ov.getPersonId()));
		return ov;
	}
	public static Objectvalue searchAuctionByOwner(Objectvalue ov) {
		ov.setFindedAuctions(DBConnector.findAuctionByBookName(ov.getSellerFirstName(), ov.getSellerLastName(), ov.getPersonId()));
		return ov;
	}
	public static Objectvalue getActiveAuctionByOwner(Objectvalue ov) throws PersonNotFoundException {
		System.out.println("Hello I am getActiveAuctionByOwner in Library .");
		System.out.println("getActiveAuctionByOwner in Library : "+ov.getSellerId());
		Person p = DBConnector.getPerson(+ov.getSellerId());
		ov.setFindedAuctions( DBConnector.findAuctionByOwner(p.getId()));
		return ov;
	}
	public static Objectvalue searchAllAvailableAuctionsOfPerson(Objectvalue ov) throws PersonNotFoundException {
		
		Person p = DBConnector.getPerson(ov.getPersonId());
		
		ov.setFindedAuctions(DBConnector.findAuctionByForPerson(p.getId()));
		return ov;
	}
	public static int getIdByMail(Objectvalue ov) throws PersonNotFoundException {
		return DBConnector.getPersonByMail(ov.getMail()).getId();
	}
}
