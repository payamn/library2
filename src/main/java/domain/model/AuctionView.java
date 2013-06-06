package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;
import domain.mail.MailSender;


public class AuctionView {
	
	private int id;

	private BookView book;

	private PersonView person;

	private List<OfferView> offers;
	private int minPrice;
	private Date startDate;
	private Date endDate;
	private boolean isFinished;
	public int getMaxOfferPrice() {
		int max=-1;
		for (OfferView of : offers){
			if (of.getPrice()>max)
				max=of.getPrice();	
		}
		if (max==-1)
			return 0;
		return max;
	}


	public AuctionView(int idd,BookView book, Date startDate, Date endDate, List<OfferView> offers,PersonView p) {
		System.out.println("in auctionview");
		id=idd;
		System.out.println(book.getName()+" "+startDate.toString()+" "+endDate.toString()+" "+offers.size()+" "+p.getName());
		this.book = book;
		System.out.println("1");
		this.startDate = startDate;
		System.out.println("1");
		this.endDate = endDate;
		System.out.println("1");
		this.offers = offers;
		System.out.println("1");
		this.person = p;
		System.out.println("1");
		
	}

	

	public PersonView getPerson() {
		return this.person;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public BookView getBook(){
		return book;
	
	}
	public String getEndDate(){
		
		return endDate.toString();
	}
	public int getMinPrice() {
		return minPrice;
	}

	public int getId() {
		return id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public List<OfferView> getOffers(){
		return offers;
		
	}
}
