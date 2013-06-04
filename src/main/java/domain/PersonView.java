package domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class PersonView {
	
	private int id;
	

	public int getId() {
		return this.id;
	}
	private String  name; 

	public String getName(){
		return name;
	}
	PersonView(int ID,String n){
		id=ID;
		name=n;
	}
	
	

}
