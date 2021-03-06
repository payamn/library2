package domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import domain.scheduler.*;
import domain.database.Context;
import domain.database.DBConnector;
import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;

public class Library {

	public static void createAuction(int sellerId,String bookName,String bookWriter
			,int publishYear,String qualityStr,Date startDate,Date endDate,int price)
					throws BookIsExist, PersonNotFoundException {
		
		if(DBConnector.personWithIdHasBook(sellerId, bookName, bookWriter, publishYear))
			throw new BookIsExist("BookIsExist Exception : Book with this owner is exist in DB .");
		
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
		Auction auction = new Auction(book, startDate, endDate, price, new HashSet<Offer>());
		auction.setPerson(person);

		DBConnector.saveAuction(auction);	
		Date warnDate=new Date();
		warnDate.setTime(endDate.getTime()-172800000);
		JobScheduler.createNewJobEnd(endDate,auction.getId(),bookName,person.getName());
		JobScheduler.createNewJobWarning(warnDate,auction.getId(),bookName,person.getName(), person.getMail());
		
	}
	public static void joinToAuction(int personId,int auctionId,int price) 
			throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.checkValidTime();
		Person person = DBConnector.getPerson(personId);
		auction.checkValidPrice(price);
		Offer offer = new Offer(price, new Date());
		auction.addOffer(offer);
	/*	offer.setPerson(person);
		person.addOffer(offer);
		DBConnector.updatePerson(person);*/
		//DBConnector.saveOffer(offer);
	
	}
	public static void finishAuction(int sellerId,int personId,int auctionId)
			throws AuctionNotFoundException, PersonNotFoundException, closeTimeException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.checkValidTime();
		Person seller = DBConnector.getPerson(sellerId);
		seller.increaseRate();
		Person person = DBConnector.getPerson(personId);
		person.increaseRate();
		auction.finishSuccessfulAuction(person);
		DBConnector.updateAuction(auction);
		DBConnector.updatePerson(seller);
		DBConnector.updatePerson(person);
	
	}
	public static void finishExpiredAuction(int auctionId) throws AuctionNotFoundException {
		Auction auction = DBConnector.getAuction(auctionId);
		auction.getPerson().deacreaseRate();
		auction.finishExpiredAuction();
		DBConnector.updateAuction(auction);
		DBConnector.updatePerson(auction.getPerson());
	}
	
	public static Auction getAuctionById(int auctionId) throws AuctionNotFoundException {
		return DBConnector.getAuction(auctionId);  
	}
	public static List<Auction> searchAuctionByBookName(int personId,String bookName) {
		return DBConnector.findAuctionByBookName(bookName, personId);
	}
	public static List<Auction> searchAuctionByBookWriter(int personId,String bookWriter) {
		return DBConnector.findAuctionByWriterName(bookWriter, personId);
	}
	public static List<Auction> searchAuctionByOwner(int personId,String SellerFirstName,String SellerLastName) {
		return DBConnector.findAuctionByOwnerName(SellerFirstName, SellerLastName, personId);
	}
	public static List<Auction> getActiveAuctionByOwner(int sellerId) throws PersonNotFoundException {
		//System.out.println("Hello I am getActiveAuctionByOwner in Library .");
		//System.out.println("getActiveAuctionByOwner in Library : "+sellerId);
		return DBConnector.findAuctionByOwnerID(sellerId);
	}
	public static List<Auction>  searchAllAvailableAuctionsOfPerson(int personId) throws PersonNotFoundException {
		return DBConnector.findAuctionForPerson(personId);
	}
	public static int getIdByMail(String mail) throws PersonNotFoundException {
		return DBConnector.getPersonByMail(mail).getId();
	}
	public static Person getPersonByMail(String mail) throws PersonNotFoundException {
		return DBConnector.getPersonByMail(mail);
	}
	
	public static List<Auction> getRecentlyAddedAuctions(Date date) {
		return DBConnector.findRecentlyAddedAuctions(date);
		
	}
	/*public static List<String> getAllNames() {
		List <String> names=new ArrayList <String>();
		List<String> fNames= DBConnector.getAllFirstNames();
		List<String> lNames=DBConnector.getAllLastNames();
		for(int i=0;i<fNames.size();i++){
			names.add(fNames.get(i)+"  "+lNames.get(i));
		}
		return names;
			
	}*/
	public static List<Person> getAllPersons() {
		return DBConnector.getAllPersons();
	}

}
