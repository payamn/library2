package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;


import domain.database.DBConnector;
import domain.database.HibernateUtil;
import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;
import domain.model.Auction;
import domain.model.Book;
import domain.model.Library;
import domain.model.Offer;
import domain.model.Person;
import domain.model.Profile;
import domain.model.Quality;

public class App {

	public static void main(String[] args) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException, BookIsExist, ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Profile pr1 = new Profile("Kamran", "Kamrani", new Date(),20,"kamranpic");
		Profile pr2 = new Profile("Shaskool", "Kheng zade", new Date(),12,"shskollpic");
		Profile pr3 = new Profile("Ariaz", "Eghbali", new Date(),30,"ariazpic");

		Book book1 = new Book("jooje", "ordak", 1360, Quality.AWFUL);
		Book book2 = new Book("jooje", "khers", 1362, Quality.BAD);
		Book book3 = new Book("ShazdeKoochooloo", "Ammam", 1389, Quality.NORMAL);

		HashSet<Offer> offersAuction1 = new HashSet<Offer>();
		HashSet<Offer> offersAuction2 = new HashSet<Offer>();

		HashSet<Offer> offersPerson1 = new HashSet<Offer>();
		HashSet<Offer> offersPerson2 = new HashSet<Offer>();
		HashSet<Offer> offersPerson3 = new HashSet<Offer>();

		Offer offer1 = new Offer(100, new Date());
		Offer offer2 = new Offer(200, new Date());
		Offer offer3 = new Offer(300, new Date());
		Offer offer4 = new Offer(400, new Date());

		offersAuction1.add(offer1);
		offersAuction1.add(offer3);
		offersAuction2.add(offer2);
		offersAuction2.add(offer4);

		offersPerson1.add(offer2);
		offersPerson2.add(offer1);
		offersPerson3.add(offer3);
		offersPerson3.add(offer4);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Auction auction1 = new Auction(book1,new Date(), sdf.parse("10-10-2013"),20 , offersAuction1);
		Auction auction2 = new Auction(book2,new Date(), sdf.parse("10-07-2013"),30 , offersAuction2);
		Auction auction3 = new Auction(book3,sdf.parse("10-01-2013"), sdf.parse("10-05-2013"),40 , new HashSet<Offer>());
		
		Person person1 = new Person(pr1, "payam222@gmail.com", "123456", offersPerson1);
		Person person2 = new Person(pr2, "pakdel@gmail.com", "123456", offersPerson2);
		Person person3 = new Person(pr3, "aryaz@gmail.com", "123456", offersPerson3);

		auction1.setPerson(person1);
		auction2.setPerson(person2);
		auction3.setPerson(person1);

		offer1.setPerson(person2);
		offer2.setPerson(person1);
		offer3.setPerson(person3);
		offer4.setPerson(person3);

		session.beginTransaction();

		session.save(person1);
		session.save(person2);
		session.save(person3);

		session.save(auction1);
		session.save(auction2);
		session.save(auction3);

		session.getTransaction().commit();

		// DBConnector Tests

		// Auction getAuction(int auctionID)
		Auction testAuction1 = DBConnector.getAuction(1);
		Auction testAuction2 = DBConnector.getAuction(2);
		Auction testAuction3 = DBConnector.getAuction(3);

		System.out.println("Auction with id = 1 : "+testAuction1);
		System.out.println("Auction with id = 2 : "+testAuction2);
		System.out.println("Auction with id = 3 : "+testAuction3);
		// Person getPerson(int personID)
		Person testPerson1 = DBConnector.getPerson(1);
		Person testPerson2 = DBConnector.getPerson(2);
		Person testPerson3 = DBConnector.getPerson(3);

		System.out.println("Person with id = 1 :"+testPerson1);
		System.out.println("Person with id = 2 :"+testPerson2);
		System.out.println("Person with id = 3 :"+testPerson3);
		// void saveAuction(Auction auction)
		Book testBook1 = new Book("Aya Midanid?", "Jami az nevisandegan", 950, Quality.AWFUL);
		Auction testAuction11 = new Auction(testBook1, new Date(), new Date(), 50, new HashSet<Offer>());
		testAuction11.setPerson(testPerson1);
		DBConnector.saveAuction(testAuction11);

		Auction testAuction22 = DBConnector.getAuction(4);
		System.out.println("Auction with id=4 : "+testAuction22);
		// void savePerson(Person person)
		Person testPerson11 = new Person(new Profile("Naser", "Mamani", new Date(),67,"picnaseri"), "naseri@yahoo.com", "111", new HashSet<Offer>());

		DBConnector.savePerson(testPerson11);
		System.out.println("Person with id=4 : "+testPerson11);
		// void updatePerson(Person person)
		Offer testOffer1 = new Offer(830, new Date());
		testOffer1.setPerson(testPerson11);
		testAuction22.addOffer(testOffer1);
		testPerson11.addOffer(testOffer1);
		DBConnector.updatePerson(testPerson11);
		Person testPerson20 = DBConnector.getPerson(4);
		System.out.println("Person with id=4 : "+testPerson20);
		// List<Auction> findAuctionByBookName(String bookName, int personId)
		List<Auction> testList1 = DBConnector.findAuctionByBookName("jooje", 4);
		List<Auction> testList2 = DBConnector.findAuctionByBookName("jooje", 1);
		List<Auction> testList3 = DBConnector.findAuctionByBookName("ShazdeKoochooloo", 1);

		System.out.println("findAuctionByBookName : 2="+testList1.size());
		System.out.println("findAuctionByBookName : 1="+testList2.size());
		System.out.println("findAuctionByBookName : 0="+testList3.size());
		//List<Auction> findAuctionByWriterName(String bookWriter, int personId)
		List<Auction> testList11 = DBConnector.findAuctionByWriterName("ordak", 1);
		List<Auction> testList12 = DBConnector.findAuctionByWriterName("ordak", 4);
		List<Auction> testList13 = DBConnector.findAuctionByWriterName("Ammam", 2);

		System.out.println("findAuctionByWriterName : 0="+testList11.size());
		System.out.println("findAuctionByWriterName : 1="+testList12.size());
		System.out.println("findAuctionByWriterName : 1="+testList13.size());
		// List<Auction> findAuctionByOwnerName(String sellerFirstName, String sellerLastName, int personId)
		List<Auction> testList21 = DBConnector.findAuctionByOwnerName("Kamran", "Kamrani", 4);
		List<Auction> testList22 = DBConnector.findAuctionByOwnerName("Kamran", "Kamrani", 1);
		List<Auction> testList23 = DBConnector.findAuctionByOwnerName("Shaskool", "Kheng zade", 1);

		System.out.println("findAuctionByOwnerName : 3="+testList21.size());
		System.out.println("findAuctionByOwnerName : 0="+testList22.size());
		System.out.println("findAuctionByOwnerName : 1="+testList23.size());
		// List<Auction> findAuctionByOwnerID(int personId)
		List<Auction> testList31 = DBConnector.findAuctionByOwnerID(1);
		List<Auction> testList32 = DBConnector.findAuctionByOwnerID(2);
		List<Auction> testList33 = DBConnector.findAuctionByOwnerID(3);
		List<Auction> testList34 = DBConnector.findAuctionByOwnerID(4);

		System.out.println("findAuctionByOwnerID : 3="+testList31.size());
		System.out.println("findAuctionByOwnerID : 1="+testList32.size());
		System.out.println("findAuctionByOwnerID : 0="+testList33.size());
		System.out.println("findAuctionByOwnerID : 0="+testList34.size());
		// List<Auction> findAuctionForPerson(int personId)
		List<Auction> testList41 = DBConnector.findAuctionForPerson(1);
		List<Auction> testList42 = DBConnector.findAuctionForPerson(2);
		List<Auction> testList43 = DBConnector.findAuctionForPerson(3);
		List<Auction> testList44 = DBConnector.findAuctionForPerson(4);

		System.out.println("findAuctionForPerson : 1="+testList41.size());
		System.out.println("findAuctionForPerson : 3="+testList42.size());
		System.out.println("findAuctionForPerson : 4="+testList43.size());
		System.out.println("findAuctionForPerson : 4="+testList44.size());
		// List<Auction> findRecentlyAddedAuctions(Date now)
		List<Auction> testList51 = DBConnector.findRecentlyAddedAuctions(new Date());

		System.out.println("findRecentlyAddedAuctions : 4="+testList51.size());
		// Person getPersonByMail(String mail)
		Person testPerson21 = DBConnector.getPersonByMail("payam222@gmail.com");
		Person testPerson22 = DBConnector.getPersonByMail("naseri@yahoo.com");

		System.out.println("testPerson 21 : "+testPerson21);
		System.out.println("testPerson 22 : "+testPerson22);
		// boolean personWithIdHasBook(int sellerId, String bookName, String bookWriter, int publishYear)
		System.out.println("personWithIdHasBook : true="+DBConnector.personWithIdHasBook(1, "jooje", "ordak", 1360));
		// List<Person> getAllPersons()
		System.out.println("getAllPersons : 4="+DBConnector.getAllPersons().size());
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// createAuction
		//Library.createAuction(1, "Zaban Omoomi", "Holliday", 1900, "BAD", new Date(), new Date(), 22);
		
		// joinToAuction
		Library.joinToAuction(4, 1, 100);
		
		// finishAuction
		Library.finishAuction(1, 4, 4);
		
		// finishExpiredAuction
		Library.finishExpiredAuction(3);
		
		// END
		System.out.println("Tests ended successfully .");
	}
}
