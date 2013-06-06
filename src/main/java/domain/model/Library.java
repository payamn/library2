package domain.model;

import java.util.Date;
import java.util.List;

import domain.database.DBConnector;
import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;

public class Library {
	public static Auction getAuctionById(int  auctionId) throws AuctionNotFoundException {
		return DBConnector.getAuction(auctionId);  
	}
	public static void  createAuction(int sellerId,String bookName,String bookWriter
			,int publishYear,String qualityStr,Date startDate,Date endDate,int price )
					throws BookIsExist, PersonNotFoundException {
		List<Book> books = DBConnector.getBooks(bookName,bookWriter, publishYear);
		for(Book b : books) {
			if(b.getAuction().getPerson().getId() == sellerId)
				throw new BookIsExist("BookIsExist Exception : Book with this owner is exist in DB .");
		}	
		Quality quality=Quality.GOOD ;
		if(qualityStr.equalsIgnoreCase("BAD"))
			quality=Quality.BAD;
		else if (qualityStr.equalsIgnoreCase("GOOD"))
			quality=Quality.GOOD;
		else if (qualityStr.equalsIgnoreCase("EXCELLENT"))
			quality=Quality.EXCELLENT;
		else if (qualityStr.equalsIgnoreCase("AWFUL"))
			quality=Quality.AWFUL;
		else if (qualityStr.equalsIgnoreCase("NORMAL"))
			quality=Quality.NORMAL;
		Book book = new Book(bookName, bookWriter, publishYear, quality);
		Person person = DBConnector.getPerson(sellerId);
		Auction auction = new Auction(book, startDate, endDate, price);
		auction.setPerson(person);
		book.setAuction(auction);
		DBConnector.saveAuction(auction);	
		//	JobScheduler.createNewJob(OV.getAuctionEndDate(), auction.getId());
	}
	public static void joinToAuction(int personId,int auctionId,int price) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.checkValidTime();
		Person person = DBConnector.getPerson(personId);
		auction.checkValidPrice(price);
		Offer offer = new Offer(price, new Date());
		offer.setAuction(auction);
		offer.setPerson(person);
		DBConnector.saveOffer(offer);
	}
	public static void finishAuction(int sellerId,int personId,int auctionId) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.checkValidTime();
		Person seller = DBConnector.getPerson(sellerId);
		seller.increaseRate();
		Person person = DBConnector.getPerson(personId);
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
	public static List<Auction> searchAuctionByBookName(int personId,String bookName) {
		return DBConnector.findAuctionByBookName(bookName, personId);

	}
	public static List<Auction> searchAuctionByBookWriter(int personId,String bookWriter) {
		return DBConnector.findAuctionByWriterName(bookWriter, personId);

	}
	public static List<Auction> searchAuctionByOwner(int personId,String SellerFirstName,String SellerLastName) {
		return(DBConnector.findAuctionByBookName(SellerFirstName, SellerLastName, personId));

	}
	public static  List<Auction> getActiveAuctionByOwner(int sellerId) throws PersonNotFoundException {
		System.out.println("Hello I am getActiveAuctionByOwner in Library .");
		System.out.println("getActiveAuctionByOwner in Library : "+sellerId);

		return ( DBConnector.findAuctionByOwner(sellerId));

	}
	public static List<Auction>  searchAllAvailableAuctionsOfPerson(int personId) throws PersonNotFoundException {



		return (DBConnector.findAuctionByForPerson(personId));

	}
	public static int getIdByMail(String mail) throws PersonNotFoundException {
		return DBConnector.getPersonByMail(mail).getId();
	}
	public static List<Auction> getRecentlyAddedAuctions(Date date) {
		return DBConnector.findRecentlyAddedAuctions(date);

	}
	public static List<Person> getProfiles() {
		return null;
		//return DBConnector.getPersons();
	}
}
