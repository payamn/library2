package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table
public class Profile {
	
	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "person"))
			@Id
			@GeneratedValue(generator = "generator")
			@Column(unique = true, nullable = false)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Person person;
	private String firstName;
	private String lastName;
	private int rate;
	private Date joinDate;
	@Column(unique = true)
	private String mail;
	private String password;

	public Profile() {
	}
	@Override
	public
	String toString() {
		return id+" "+firstName+" "+lastName+" "+rate+" "+joinDate+" "+mail;
	}
	public Profile(String firstName, String lastName, Date joinDate, String mail, String password) {
		this.rate = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
		this.mail = mail;
		this.password = password;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void increaseRate() {
		rate ++;
	}
	public void decreaseRate() {
		rate --;
	}
	public String getMail() {
		return mail;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public Person getPerson() {
		return person;
	}
	public String getName(){
		return firstName+lastName;
	}
}
