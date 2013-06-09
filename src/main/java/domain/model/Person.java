package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Person {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	// TODO : is optional=false good ?
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional=false)
	private Profile profile;
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade=CascadeType.ALL)
	private Set<Auction> auctions;
	*/
	@OneToMany(fetch = FetchType.LAZY,mappedBy="person", cascade=CascadeType.ALL)
	private Set<Offer> offers;
	@Column(unique = true, nullable=false)
	private String mail;
	@Column(nullable=false)
	private String password;
	
	public Person() {
		offers = new HashSet<Offer>();
	}
	public Person(Profile profile, String mail, String password, Set<Offer> offers) {
		this.mail = mail;
		this.password = password;
		this.profile = profile;
		this.offers = offers;
	}
	public int getId() {
		return this.id;
	}
	public void increaseRate() {
		this.profile.increaseRate();
	}
	public void deacreaseRate() {
		this.profile.decreaseRate();
	}
	public Profile getProfile() {
		System.out.println("in getprofile");
		System.out.println(profile.getName());
		return profile;
	}
	public String getName(){
		return profile.getFirstName()+" "+profile.getLastName();
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public void addOffer(Offer offer) {
		offers.add(offer);
	}
	public String toString() {
		return id+" "+profile;
	}
}