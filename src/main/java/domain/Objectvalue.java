package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Objectvalue {

	private String bookName;
	private String bookWriter;
	private int publishyear;
	private Quality quality;
	private int auctionId;
	private Date auctionStartDate;
	private Date auctionEndDate;
	private	int price;
	private Date offerDate;
	private int profileId;
	private int personId;
	private int SellerId;
	private String sellerFirstName;
	private String sellerLastName;
	private String mail;
	@SuppressWarnings("unused")
	private String password;

	private List<AuctionView> findedAuctions = null;
	public String getMail(){
		return mail;
	}
	public void setMail(String m){
		mail=m;
	}
	public void setPassword(String m){
		password=m;
	}
	// alaki
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getSellerId() {
		return SellerId;
	}
	public void setSellerId(int sellerId) {
		SellerId = sellerId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public int getPublishyear() {
		return publishyear;
	}
	public void setPublishyear(int publishyear) {
		this.publishyear = publishyear;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		if(quality.equalsIgnoreCase("BAD"))
			this.quality=Quality.BAD;
		else if (quality.equalsIgnoreCase("GOOD"))
			this.quality=Quality.GOOD;
		else if (quality.equalsIgnoreCase("EXCELLENT"))
			this.quality=Quality.EXCELLENT;
		else if (quality.equalsIgnoreCase("AWFUL"))
			this.quality=Quality.AWFUL;
		else if (quality.equalsIgnoreCase("NORMAL"))
			this.quality=Quality.NORMAL;


	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public Date getAuctionStartDate() {
		return auctionStartDate;
	}
	public void setAuctionStartDate(Date auctionStartDate) {
		this.auctionStartDate = auctionStartDate;
	}
	public Date getAuctionEndDate() {
		return auctionEndDate;
	}
	public void setAuctionEndDate(Date auctionEndDate) {
		this.auctionEndDate = auctionEndDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getOfferDate() {
		return offerDate;
	}
	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	public List<AuctionView> getFindedAuctions() {
		System.out.println(findedAuctions.size());
		return findedAuctions;
	}
	public void setFindedAuctions(List<Auction> findedAuctions) {
		System.out.println("in setfindedAuction");
		this.findedAuctions=new ArrayList<AuctionView>();
		for (Auction a : findedAuctions){
			System.out.println("in for (Auction a : findedAuctions)");
			System.out.println(a.getId());
			
			this.findedAuctions.add(a.getView());
			System.out.println("before");
		}
		System.out.println("out");
		
	
	}
	public String getSellerFirstName() {
		return sellerFirstName;
	}
	public void setSellerFirstName(String sellerFirstName) {
		this.sellerFirstName = sellerFirstName;
	}
	public String getSellerLastName() {
		return sellerLastName;
	}
	public void setSellerLastName(String sellerLastName) {
		this.sellerLastName = sellerLastName;
	}

}
