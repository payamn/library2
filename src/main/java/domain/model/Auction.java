package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import domain.database.DBConnector;
import domain.exceptions.closeTimeException;
import domain.exceptions.priceException;
import domain.mail.MailSender;

@Entity
@Table
public class Auction {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	@OneToOne( cascade=CascadeType.ALL)
	private Book book;
	@ManyToOne
	private Person person;
	@OneToMany( fetch=FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name="auction_id")
	private Set<Offer> offers;
	private int minPrice;
	private Date startDate;
	private Date endDate;
	private boolean isFinished;
	
	public Auction() {
		this.offers = new HashSet<Offer>();
	}
	/*public Auction(Book book, Date startDate, Date endDate, int minPrice) {
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offers = new HashSet<Offer>();
		this.isFinished = false;
		this.minPrice = minPrice;
	}*/
	public Auction(Book book, Date startDate, Date endDate, int minPrice, Set<Offer> offers) {
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offers = offers;
		this.isFinished = false;
		this.minPrice = minPrice;
	}
	public int getMaxOfferPrice() {
		int max=-1;
		for (Offer of : offers){
			if (of.getPrice()>max)
				max=of.getPrice();	
		}
		if (max==-1)
			return 0;
		return max;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void finishSuccessfulAuction(Person winner) {
		/// expire nashode bashe ghablan !
		this.isFinished = true;	
		for(Offer of : offers) {
			if(of.getPerson().getId() == winner.getId()) {
				MailSender.sendAuctionSuccessMailToWinner(of.getPerson(),this.getPerson().getMail(),of.getPerson().getName());
			}
			else {
				MailSender.sendFinishedAuctionMail(of.getPerson(),of.getPerson().getName());
				
			}
		}
		MailSender.sendAuctionSuccessMailToOwner(this.person,this.person.getName());
	}
	public void finishExpiredAuction() {
		this.isFinished = true;
		for(Offer of : offers) {
			MailSender.sendFinishedAuctionMail(of.getPerson(),of.getPerson().getName());		
		}
		MailSender.sendAuctionExpiredMailToOwner(this.person, this.getPerson().getName());
	}
	public Person getPerson() {
		return this.person;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public Book getBook(){
		return book;
	}
	public String getEndDate(){
		
		return endDate.toString();
	}
	public void checkValidTime() throws closeTimeException {
		if (isFinished())
			throw new closeTimeException("Auction has been expired before.");	
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public  void addOffer(Person person, int price) throws priceException{
		System.out.print("assssssshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		for(Offer of : offers) {
			if(of.getPerson().getId()==person.getId()){
				System.out.print("EEEEEEEEEEEEEEEEEEQQQQQQQQQQQQQQQQUUUUUUUUUUUUUUAAAAAAAAAAAAAAAAAAllllllllllllllll");
				if( price<=of.getPrice() )
					
					throw new priceException("you are not allowed to decrease your bid , please suggest price more than your previous bid : "+of.getPrice());
				//Offer offer=new Offer(price, new Date());
					DBConnector.updateOffer(of.getId(),price);
					return;
				
			}
			
		}
		checkValidPrice(price);
		Offer offer=new Offer(price, new Date());
		offers.add(offer);
		offer.setPerson(person);
		person.addOffer(offer);
		DBConnector.updatePerson(person);
		
		
	}

	public void checkValidPrice(int price) throws priceException {
		System.out.println("*******************checkValidPrice : "+price);
	
		if (price<minPrice)
			throw new priceException("your price is less than minimum");
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Set<Offer> getOffers(){
		return offers;	
	}
	@Override
	public String toString() {
		return "Auction [id=" + id + ", book=" + book + ", person=" + person
				+ ", offers size=" + offers.size() + ", minPrice=" + minPrice
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isFinished=" + isFinished + "]";
	}
	public void addOffer(Offer offer) {
		offers.add(offer);	
	}
}