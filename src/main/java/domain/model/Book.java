package domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Book {

//	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "auction"))
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private int id;
	private String name;
	private String writerName;
	private int publishYear;
	private Quality quality;
	public Book() {
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
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", writerName="
				+ writerName + ", publishYear=" + publishYear + ", quality="
				+ quality + "]";
	}
}