package domain;

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
public class Book {

	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "auction"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name="id", unique = true, nullable = false)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Auction auction;
	private String name;
	private String writerName;
	private int publishYear;
	private Quality quality;

	public Book() {
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public String toString() {
		return id+" "+name+" "+writerName+" "+publishYear+" "+quality;	
	}
	public Book(String name, String writerName, int publishYear, Quality quality) {
		this.name = name;
		this.writerName = writerName;
		this.publishYear = publishYear;
		this.quality = quality;
	}
	public String getName() {
		return this.name;
	}
	public String getWriterName() {
		return this.writerName;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public Quality getQuality() {
		return this.quality;
	}
	public String getQualityStr(){
		if(this.quality==Quality.BAD){
			return "bad";
		}else if(this.quality==Quality.GOOD)
			return "good";
		else if(this.quality==Quality.EXCELLENT)
			return "excellent";
		else if(this.quality==Quality.AWFUL)
			return "awful";
		else
			return "normal";
	}
	
	public Auction getAuction() {
		return auction;
	}
	public BookView getView() {
		// TODO Auto-generated method stub
		System.out.println("in book getview");
		System.out.println(id+name+writerName+publishYear+quality);
		return new BookView(id,name,writerName,publishYear,quality);
	}
}
