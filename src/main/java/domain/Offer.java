package domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Offer {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	public int getPrice() {
		return price;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_id", nullable = false)
	private Auction auction;
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;
	private int price;
	private Date offerDate;
	public String toString() {
		return id+" "+price+" "+offerDate;
	}
	public Offer() {
	}
	public Offer(int price, Date offerDate) {
		super();
		this.price = price;
		this.offerDate = offerDate;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public Person getPerson() {
		return this.person;
	}
	
}
