package domain;

import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;

import exceptions.AuctionNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.closeTimeException;
import exceptions.priceException;

import database.HibernateUtil;

public class App {

	public static void main(String[] args) throws AuctionNotFoundException, PersonNotFoundException, closeTimeException, priceException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Profile pr1 = new Profile("Kamran", "Kamrani", new Date(), "payam222@gmail.com", "123456");
		Profile pr2 = new Profile("Shaskool", "Kheng zade", new Date(), "pakdel@gmail.com", "123456");
		Profile pr3 = new Profile("Ariaz", "Eghbali", new Date(), "aryaz@gmail.com", "123456");
		
		HashSet<Auction> hs1 = new HashSet<Auction>();
		HashSet<Auction> hs2 = new HashSet<Auction>();
		HashSet<Auction> hs3 = new HashSet<Auction>();
		
		Book book1 = new Book("jooje", "ordak", 1360, Quality.AWFUL);
		Book book2 = new Book("jooje", "khers", 1362, Quality.BAD);
		
		HashSet<Offer> offers1 = new HashSet<Offer>();
		HashSet<Offer> offers2 = new HashSet<Offer>();
		
		Offer offer1 = new Offer(100, new Date());
		Offer offer2 = new Offer(200, new Date());
		
		offers1.add(offer1);
		offers2.add(offer2);
		
		Auction auction1 = new Auction(book1,new Date(), new Date(), offers1);
		Auction auction2 = new Auction(book1,new Date(), new Date(), offers2);
		
		book1.setAuction(auction1);
		book2.setAuction(auction2);
		
		hs1.add(auction1);
		hs2.add(auction2);
		
		Person person1 = new Person(pr1, hs1);
		Person person2 = new Person(pr2, hs2);
		Person person3 = new Person(pr3, hs3);
		
		pr1.setPerson(person1);
		pr2.setPerson(person2);
		pr3.setPerson(person3);
		
		auction1.setPerson(person1);
		auction2.setPerson(person2);
		
		offer1.setPerson(person2);
		offer1.setAuction(auction1);
		
		offer2.setPerson(person1);
		offer2.setAuction(auction2);
		
		session.beginTransaction();
		
		session.save(person1);
		session.save(person2);
		session.save(person3);
		
		session.save(auction1);
		session.save(auction2);
		
		session.save(offer1);
		session.save(offer2);
		
		session.getTransaction().commit();
		
		Person pp = (Person) session.get(Person.class, 1);
		System.out.println("Person get test : "+pp);
		/*
		Objectvalue ov = new Objectvalue();
		ov.setAuctionId(1);
		ov.setOfferDate(new Date());
		ov.setPrice(300);
		ov.setPersonId(1);
		Library.joinToAuction(ov);
		*/
		//MailSender.sendAuctionSuccessMailToOwner(person1);
		
		//System.out.println("db test : "+DBConnector.getAuction(1));	
	}
}
