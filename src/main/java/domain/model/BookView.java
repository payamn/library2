package domain.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

public class BookView {


	private int id;
	
	private String name;
	private String writerName;
	private int publishYear;
	private Quality quality;


	public BookView(int id,String name, String writerName, int publishYear, Quality quality) {
		this.name = name;
		this.id=id;
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

}
