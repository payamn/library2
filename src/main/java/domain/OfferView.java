package domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OfferView {
		private int id;
		public int getPrice() {
			return price;
		}
		//private AuctionView auction;
		private PersonView person;
		private int price;
		private Date offerDate;
		public String toString() {
			return id+" "+price+" "+offerDate;
		}
		
		public PersonView getPerson() {
			return this.person;
		}
		public OfferView(int iid,int p,PersonView pperson,Date d){
			id=iid;
			price=p;
			person=pperson;
			offerDate=d;
			
			
		}
	
	
}
