package domain.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.PersonNotFoundException;
import domain.model.Auction;
import domain.model.Book;
import domain.model.Offer;
import domain.model.Person;
import domain.model.Profile;
import domain.model.Quality;

public class DBConnector {

	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static Auction getAuction(int auctionID) throws AuctionNotFoundException {
		session = HibernateUtil.getSessionFactory().openSession();
		Auction auction = (Auction) session.get(Auction.class, auctionID);
		System.out.print("auctionID:"+auctionID);
		
		if(auction == null)
			throw new AuctionNotFoundException("Auction not found in getAuction method in DBConnector .");
		return auction;
	}
	public static Person getPerson(int personID) throws PersonNotFoundException {
		Person person = (Person) session.get(Person.class, personID);
		if(person == null)
			throw new PersonNotFoundException("Person not found in getPerson method in DBConnector .");
		return person;
	}
	public static void saveOffer(Offer offer) {
		session.beginTransaction();
		session.save(offer);
		session.getTransaction().commit();
	}
	public static void saveAuction(Auction auction) {
		session.beginTransaction();
		session.save(auction);
		session.getTransaction().commit();
	}
	public static void savePerson(Person person) {
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
	}
	
	
	public static List<Book> getBooks(String name, String writer, int year) {
		Query query = session.createQuery("From Book where name= :myname and writerName= :mywriter and publishYear= :myyear");	
		query.setParameter("myname", name);
		query.setParameter("mywriter", writer);
		query.setParameter("myyear", year);
		System.out.println(name+" "+writer+" "+year);
		
		@SuppressWarnings("unchecked")
		List<Book> resultList = query.list();
		return resultList;
	}
	public static List<Auction> findAuctionByBookName(String bookName, int personId) {

		Query query = session.createQuery("From Book where name= :myname");	
		query.setParameter("myname", bookName);

		@SuppressWarnings("unchecked")
		List<Book> resultList = query.list();
		List<Auction> result = new ArrayList();
		for(Book b : resultList) {
			if(b.getAuction().getPerson().getId() != personId && !b.getAuction().isFinished()) {
				result.add(b.getAuction());
			}
		}
		return result;
	}
	public static List<Auction> findAuctionByWriterName(String bookWriter, int personId) {
		Query query = session.createQuery("From Book where writerName= :myname");	
		query.setParameter("myname", bookWriter);

		@SuppressWarnings("unchecked")
		List<Book> resultList = query.list();
		List<Auction> result = new ArrayList();
		for(Book b : resultList) {
			if(b.getAuction().getPerson().getId() != personId && !b.getAuction().isFinished()) {
				result.add(b.getAuction());
			}
		}
		return result;
	}
	public static List<Auction> findAuctionByBookName(String sellerFirstName, String sellerLastName, int personId) {
		//salam mehrankharef
		Query query = session.createQuery("From Auction");
		@SuppressWarnings("unchecked")
		List<Auction> resultList = query.list();
		List<Auction> result = new ArrayList();
		for(Auction a : resultList) {
			if(a.getPerson().getProfile().getFirstName().equals(sellerFirstName) &&
					a.getPerson().getProfile().getLastName().equals(sellerLastName) && 
					a.getPerson().getId() != personId && !a.isFinished()) {
				result.add(a);
			}
		}
		return result;
	}
	public static List<Auction> findAuctionByOwner(int personId) {
		System.out.println("Hello I am findAuctionByOwner in DBConnector .");
		Query query = session.createQuery("From Auction");
		@SuppressWarnings("unchecked")
		List<Auction> resultList = query.list();
		List<Auction> result = new ArrayList();
		for(Auction a : resultList) {
			if(a.getPerson().getId() == personId && !a.isFinished()) {
				System.out.println("finded person in  findAuctionByOwner in DBConnector : "+a.getPerson());
				result.add(a);
			}
		}
		return result;
	}
	public static List<Auction> findAuctionByForPerson(int personId) {
		//leila
		session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("From Auction");
		@SuppressWarnings("unchecked")
		List<Auction> resultList = query.list();
		List<Auction> result = new ArrayList();
		for(Auction a : resultList) {
			if(a.getPerson().getId() != personId && !a.isFinished() && a.getId()!=0) {
				result.add(a);
			}
		}
		return result;
	}
	public static Person getPersonByMail(String mail) throws PersonNotFoundException {
		Query query = session.createQuery("From Profile");
		@SuppressWarnings("unchecked")
		List<Profile> resultList = query.list();
		List<Person> result = new ArrayList();
		for(Profile p : resultList) {
			if(p.getMail().equals(mail)) {
				System.out.println("profile : "+p);
				System.out.println("person : "+p.getPerson());
				result.add(p.getPerson());
			}
		}
		if(result.size() == 0) {
			System.out.println("Exception khak bar saremoon .");
			throw new PersonNotFoundException("Person not found in getPersonByMail method in DBConnector .");
		}
		if(result.size() > 1)
			System.out.println("Inconsistency in DB . ");
		return result.get(0);
	}
	public static List<Auction> findRecentlyAddedAuctions(Date date) {
		// TODO by mehran
		return null;
	}
}