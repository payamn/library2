package test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;


import domain.database.DBConnector;
import domain.database.HibernateUtil;
import domain.exceptions.AuctionNotFoundException;
import domain.exceptions.PersonNotFoundException;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;
import domain.model.Auction;
import domain.model.Book;
import domain.model.Offer;
import domain.model.Person;
import domain.model.Profile;
import domain.model.Quality;

public class App {

	public static void main(String[] args) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Profile pr1 = new Profile("Kamran", "Kamrani", new Date());
		Profile pr2 = new Profile("Shaskool", "Kheng zade", new Date());
		Profile pr3 = new Profile("Ariaz", "Eghbali", new Date());
		
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
		
		Auction auction1 = new Auction(book1,new Date(), new Date(),20 , offersAuction1);
		Auction auction2 = new Auction(book2,new Date(), new Date(),30 , offersAuction2);
		Auction auction3 = new Auction(book3,new Date(), new Date(),40 , new HashSet<Offer>());
		
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
		Person testPerson11 = new Person(new Profile("Naser", "Mamani", new Date()), "naseri@yahoo.com", "111", new HashSet<Offer>());
		
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
		
		System.out.println("findByBookName : 2="+testList1.size());
		System.out.println("findByBookName : 1="+testList2.size());
		System.out.println("findByBookName : 0="+testList3.size());
		// END
		System.out.println("Tests ended successfully .");
	}
}
