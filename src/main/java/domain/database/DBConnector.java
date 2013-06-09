package domain.database;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.model.Auction;
import domain.model.Person;

public class DBConnector {

	private static Session session = HibernateUtil.getSessionFactory().openSession();

	public static Auction getAuction(int auctionID) throws AuctionNotFoundException {
		session = HibernateUtil.getSessionFactory().openSession();
		Auction auction = (Auction) session.get(Auction.class, auctionID);
		
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
	/*public static void saveOffer(Offer offer) {
		session.beginTransaction();
		session.save(offer);
		session.getTransaction().commit();
	}*/
	public static void saveAuction(Auction auction) {
		session.beginTransaction();
		session.save(auction);
		session.getTransaction().commit();
	}
	public static void updateAuction(Auction auction) {
		session.beginTransaction();
		session.update(auction);
		session.getTransaction().commit();
	}
	public static void savePerson(Person person) {
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
	}
	public static void updatePerson(Person person) {
		session.beginTransaction();
		session.update(person);
		session.getTransaction().commit();
	}
	
	public static List<Auction> findAuctionByBookName(String bookName, int personId) {
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.name= :myname and aa.person.id<> :myID");	
		query.setParameter("myname", bookName);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static List<Auction> findAuctionByWriterName(String bookWriter, int personId) {
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.writerName= :myname and aa.person.id<> :myID");	
		query.setParameter("myname", bookWriter);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static List<Auction> findAuctionByOwnerName(String sellerFirstName, String sellerLastName, int personId) {
		
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" aa.person.profile.firstName= :myname1 and aa.person.profile.lastName= :myname2 and aa.person.id<> :myID");	
		query.setParameter("myname1", sellerFirstName);
		query.setParameter("myname2", sellerLastName);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static List<Auction> findAuctionByOwnerID(int personId) {
		//System.out.println("Hello I am findAuctionByOwner in DBConnector .");
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.person.id= :myID");	
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static List<Auction> findAuctionForPerson(int personId) {
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.person.id<> :myID");	
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static List<Auction> findRecentlyAddedAuctions(Date now) {
		Date past = new Date(now.getTime()-864000000);
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.startDate> :past and aa.endDate< :now ");	
		query.setParameter("now", now);
		query.setParameter("past", past);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		return result;
	}
	public static Person getPersonByMail(String mail) throws PersonNotFoundException {
		Query query = session.createQuery("select p from Person as p where p.mail = :m");
		query.setParameter("m", mail);
		@SuppressWarnings("unchecked")
		List<Person> result = query.list();
		if(result.size() == 0) {
			throw new PersonNotFoundException("Person not found in getPersonByMail method in DBConnector .");
		}
		if(result.size() > 1)
		{
			System.out.println("Inconsistency in DB . ");
			throw new PersonNotFoundException("More than 1 person with same mail found .");
		}
		return result.get(0);
	}
	public static boolean personWithIdHasBook(int sellerId, String bookName, String bookWriter, int publishYear) throws BookIsExist {
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.name= :myname and bb.writerName= :mywritername and bb.publishYear= :myyear and aa.person.id= :myID");	
		query.setParameter("myname", bookName);
		query.setParameter("mywritername", bookWriter);
		query.setParameter("myyear", publishYear);
		query.setParameter("myID", sellerId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		if(result.size()>1)
			throw new BookIsExist("more than 1 book found , inconsistency in DB .");
		if(result.size()==1)
			return true;
		return false;
	}
	public static List<Person> getAllPersons() {
		Query query = session.createQuery("select aa from Person as aa");	
		@SuppressWarnings("unchecked")
		List<Person> result = query.list();
		return result;
	}
}