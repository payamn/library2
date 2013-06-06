package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

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
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
	private Profile profile;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Auction> auctions;
	
	public Person() {
	}
	public String toString() {
		return id+" "+profile;
	}
	public Person(Profile profile, Set<Auction> auctions) {
		this.profile = profile;
		this.auctions = auctions;
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
	public String getMail() {
		return this.profile.getMail();
	}
	public Profile getProfile() {
		System.out.println("in getprofile");
		System.out.println(profile.getName());
		return profile;
	}
	public String getName(){
		return profile.getFirstName()+" "+profile.getLastName();
	}

	
}