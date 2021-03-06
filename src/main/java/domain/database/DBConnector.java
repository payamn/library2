package domain.database;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.model.Auction;
import domain.model.Person;

public class DBConnector {
	//private static Session session=HibernateUtil.getSessionFactory().openSession();


	public static Auction getAuction(int auctionID) throws AuctionNotFoundException {
		Session session=Context.getSession();
		//session = HibernateUtil.getSessionFactory().openSession();
		Auction auction = (Auction) session.get(Auction.class, auctionID);
		
		//session.close();
		if(auction == null)
			throw new AuctionNotFoundException("Auction not found in getAuction method in DBConnector .");
		auction.getOffers().size();
		//Context.closeSession();
		return auction;
		
	}
	public static Person getPerson(int personID) throws PersonNotFoundException {
		Session session=Context.getSession();
		Person person = (Person) session.get(Person.class, personID);
		//session.close();
		//Context.closeSession();
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
		Session session=Context.getSession();
		session.beginTransaction();
		session.save(auction);
		session.getTransaction().commit();
		//Context.closeSession();
		//session.close();
	}
	public static void updateAuction(Auction auction) {
		Session session=Context.getSession();
		session.beginTransaction();
		session.update(auction);
		session.getTransaction().commit();
		//Context.closeSession();
		//session.close();
	}
	public static void savePerson(Person person) {
		Session session=Context.getSession();
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
		//Context.closeSession();
		//session.close();
	}
	public static void updatePerson(Person person) {
		Session session=Context.getSession();
		session.beginTransaction();
		session.update(person);
		session.getTransaction().commit();
		//Context.closeSession();
		//session.close();
	}
	
	public static List<Auction> findAuctionByBookName(String bookName, int personId) {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.name= :myname and aa.person.id<> :myID");	
		query.setParameter("myname", bookName);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		//Context.closeSession();
		return result;
	}
	public static List<Auction> findAuctionByWriterName(String bookWriter, int personId) {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.writerName= :myname and aa.person.id<> :myID");	
		query.setParameter("myname", bookWriter);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		//Context.closeSession();
		return result;
	}
	public static List<Auction> findAuctionByOwnerName(String sellerFirstName, String sellerLastName, int personId) {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" aa.person.profile.firstName= :myname1 and aa.person.profile.lastName= :myname2 and aa.person.id<> :myID");	
		query.setParameter("myname1", sellerFirstName);
		query.setParameter("myname2", sellerLastName);
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		//Context.closeSession();
		return result;
	}
	public static List<Auction> findAuctionByOwnerID(int personId) {
		System.out.println("Hello I am findAuctionByOwner in DBConnector .");
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.person.id= :myID");	
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		for(int i=0;i<result.size();i++)
			result.get(i).getOffers().size();
		//Context.closeSession();
		//session.close();
		return result;
	}
	public static List<Auction> findAuctionForPerson(int personId) {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.person.id<> :myID");	
		query.setParameter("myID", personId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		
		//Context.closeSession();
		return result;
	}
	public static List<Auction> findRecentlyAddedAuctions(Date now) {
		///Session session=Context.getSession();
		Session session=HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("select aa from Auction as aa where" +
				" aa.startDate>= :now ");	
		query.setParameter("now", now);
		System.out.print("now");

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		/*for(int i=0;i<result.size();i++)
			result.get(i).getOffers().size();*/
		//Context.closeSession();
		return result;
	}
	public static Person getPersonByMail(String mail) throws PersonNotFoundException {
		System.out.print("before create session!");
		Session session=Context.getSession();
		System.out.print("after create session!");
		Query query = session.createQuery("select p from Person as p where p.mail = :m");
		query.setParameter("m", mail);
		@SuppressWarnings("unchecked")
		List<Person> result = query.list();
		try{
			if(result.size() == 0) {
				throw new PersonNotFoundException("Person not found in getPersonByMail method in DBConnector .");
			}
			if(result.size() > 1)
			{
				System.out.println("Inconsistency in DB . ");
				throw new PersonNotFoundException("More than 1 person with same mail found .");
			}
		}finally{
			//Context.closeSession();
		}
		System.out.print("after query!!!!!!!!11");
		return result.get(0);
	}
	public static boolean personWithIdHasBook(int sellerId, String bookName, String bookWriter, int publishYear) throws BookIsExist {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Auction as aa inner join aa.book bb where" +
				" bb.name= :myname and bb.writerName= :mywritername and bb.publishYear= :myyear and aa.person.id= :myID");	
		query.setParameter("myname", bookName);
		query.setParameter("mywritername", bookWriter);
		query.setParameter("myyear", publishYear);
		query.setParameter("myID", sellerId);

		@SuppressWarnings("unchecked")
		List<Auction> result = query.list();
		//Context.closeSession();
		if(result.size()>1)
			throw new BookIsExist("more than 1 book found , inconsistency in DB .");
		if(result.size()==1)
			return true;
		return false;
	}
	public static List<Person> getAllPersons() {
		Session session=Context.getSession();
		Query query = session.createQuery("select aa from Person as aa");	
		@SuppressWarnings("unchecked")
		List<Person> result = query.list();
		//Context.closeSession();
		return result;
	}
	/*public static List<String> getAllLastNames() {
		Session session=Context.getSession();
		
		Query query = session.createQuery("select lastName from Profile");
		List<String> res=query.list();
		//Context.closeSession();
		return res;
	}
	public static List<String> getAllFirstNames() {
		Session session=Context.getSession();
		Query query = session.createQuery("select firstName from Profile");
		List<String> res=query.list();
		////Context.closeSession();
		return res;
	}
	public static List<Integer> getAllPersonIds() {
		Session session=Context.getSession();
		Query query = session.createQuery("select id from Person");
		List<Integer> res=query.list();
		////Context.closeSession();
		return res;
	}*/
	public static void updateOffer(int id,int price) {
		Session session=Context.getSession();
		Query query=session.createQuery("update Offer set price= :Price ");
		query.setParameter("Price", price);
		query.executeUpdate();
		Context.closeSession();
		// TODO Auto-generated method stub
		
	}
}