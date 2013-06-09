package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Profile {
	
	//@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "person"))
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	/*@OneToOne(fetch = FetchType.LAZY)
	//@PrimaryKeyJoinColumn
	private Person person;
	*/
	private String firstName;
	private String lastName;
	private int rate;
	private Date joinDate;

	public Profile() {
	}
	public Profile(String firstName, String lastName, Date joinDate) {
		this.rate = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
	}
	public void increaseRate() {
		rate ++;
	}
	public void decreaseRate() {
		rate --;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getName(){
		return firstName+lastName;
	}
	public String toString() {
		return id+" "+firstName+" "+lastName+" "+rate+" "+joinDate;
	}
}
