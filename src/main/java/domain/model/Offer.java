package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Offer {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;
	private int price;
	private Date offerDate;
	
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
	public Person getPerson() {
		return this.person;
	}
	public int getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", person=" + person + ", price=" + price
				+ ", offerDate=" + offerDate + "]";
	}
	public int getId(){
		return id;
	}
}
