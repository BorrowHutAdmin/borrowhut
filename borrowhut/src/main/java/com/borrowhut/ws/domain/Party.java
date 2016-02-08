package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PARTY database table.
 *      
 */
@Entity
@Table(name="PARTY")
@NamedQuery(name="Party.findAll", query="SELECT p FROM Party p")
public class Party implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PTY_ID")
	private int ptyId;

	@Column(name="PTY_ADDRESS_LINE_1")
	private String ptyAddressLine1;

	@Column(name="PTY_ADDRESS_LINE_2")
	private String ptyAddressLine2;

	@Column(name="PTY_ADDRESS_LINE_3")
	private String ptyAddressLine3;

	@Column(name="PTY_COUNTRY")
	private String ptyCountry;

	@Column(name="PTY_COUNTY")
	private String ptyCounty;

	@Column(name="PTY_EMAIL")
	private String ptyEmail;

	@Column(name="PTY_LATITUDE")
	private float ptyLatitude;

	/*@Column(name="PTY_LOCATION")
	private Object ptyLocation;*/

	@Column(name="PTY_LONGITUDE")
	private float ptyLongitude;

	@Column(name="PTY_MOBILE")
	private String ptyMobile;

	@Column(name="PTY_NAME")
	private String ptyName;

	@Column(name="PTY_PHOTO")
	private String ptyPhoto;

	@Column(name="PTY_POST_CODE")
	private String ptyPostCode;

	@Column(name="PTY_TEL")
	private String ptyTel;

	@Column(name="PTY_TOWN")
	private String ptyTown;

	@Column(name="PTY_TRUST_SCORE")
	private String ptyTrustScore;

	//bi-directional many-to-one association to BorrowLog
	@OneToMany(mappedBy="party")
	private List<BorrowLog> borrowLogs;

	//bi-directional many-to-one association to MyTrustCircle
	@OneToMany(mappedBy="party1")
	private List<MyTrustCircle> myTrustCircles1;

	//bi-directional many-to-one association to MyTrustCircle
	@OneToMany(mappedBy="party2")
	private List<MyTrustCircle> myTrustCircles2;

	//bi-directional many-to-one association to PartyAuthMech
	@OneToMany(mappedBy="party")
	private List<PartyAuthMech> partyAuthMeches;

	//bi-directional many-to-one association to PersonalisedUiCard
	@OneToMany(mappedBy="party")
	private List<PersonalisedUiCard> personalisedUiCards;

	//bi-directional many-to-one association to ProductListing
	@OneToMany(mappedBy="party")
	private List<ProductListing> productListings;

	public Party() {
	}

	public int getPtyId() {
		return this.ptyId;
	}

	public void setPtyId(int ptyId) {
		this.ptyId = ptyId;
	}

	public String getPtyAddressLine1() {
		return this.ptyAddressLine1;
	}

	public void setPtyAddressLine1(String ptyAddressLine1) {
		this.ptyAddressLine1 = ptyAddressLine1;
	}

	public String getPtyAddressLine2() {
		return this.ptyAddressLine2;
	}

	public void setPtyAddressLine2(String ptyAddressLine2) {
		this.ptyAddressLine2 = ptyAddressLine2;
	}

	public String getPtyAddressLine3() {
		return this.ptyAddressLine3;
	}

	public void setPtyAddressLine3(String ptyAddressLine3) {
		this.ptyAddressLine3 = ptyAddressLine3;
	}

	public String getPtyCountry() {
		return this.ptyCountry;
	}

	public void setPtyCountry(String ptyCountry) {
		this.ptyCountry = ptyCountry;
	}

	public String getPtyCounty() {
		return this.ptyCounty;
	}

	public void setPtyCounty(String ptyCounty) {
		this.ptyCounty = ptyCounty;
	}

	public String getPtyEmail() {
		return this.ptyEmail;
	}

	public void setPtyEmail(String ptyEmail) {
		this.ptyEmail = ptyEmail;
	}

	public float getPtyLatitude() {
		return this.ptyLatitude;
	}

	public void setPtyLatitude(float ptyLatitude) {
		this.ptyLatitude = ptyLatitude;
	}

/*	public Object getPtyLocation() {
		return this.ptyLocation;
	}

	public void setPtyLocation(Object ptyLocation) {
		this.ptyLocation = ptyLocation;
	}*/

	public float getPtyLongitude() {
		return this.ptyLongitude;
	}

	public void setPtyLongitude(float ptyLongitude) {
		this.ptyLongitude = ptyLongitude;
	}

	public String getPtyMobile() {
		return this.ptyMobile;
	}

	public void setPtyMobile(String ptyMobile) {
		this.ptyMobile = ptyMobile;
	}

	public String getPtyName() {
		return this.ptyName;
	}

	public void setPtyName(String ptyName) {
		this.ptyName = ptyName;
	}

	public String getPtyPhoto() {
		return this.ptyPhoto;
	}

	public void setPtyPhoto(String ptyPhoto) {
		this.ptyPhoto = ptyPhoto;
	}

	public String getPtyPostCode() {
		return this.ptyPostCode;
	}

	public void setPtyPostCode(String ptyPostCode) {
		this.ptyPostCode = ptyPostCode;
	}

	public String getPtyTel() {
		return this.ptyTel;
	}

	public void setPtyTel(String ptyTel) {
		this.ptyTel = ptyTel;
	}

	public String getPtyTown() {
		return this.ptyTown;
	}

	public void setPtyTown(String ptyTown) {
		this.ptyTown = ptyTown;
	}

	public String getPtyTrustScore() {
		return this.ptyTrustScore;
	}

	public void setPtyTrustScore(String ptyTrustScore) {
		this.ptyTrustScore = ptyTrustScore;
	}

	public List<BorrowLog> getBorrowLogs() {
		return this.borrowLogs;
	}

	public void setBorrowLogs(List<BorrowLog> borrowLogs) {
		this.borrowLogs = borrowLogs;
	}

	public BorrowLog addBorrowLog(BorrowLog borrowLog) {
		getBorrowLogs().add(borrowLog);
		borrowLog.setParty(this);

		return borrowLog;
	}

	public BorrowLog removeBorrowLog(BorrowLog borrowLog) {
		getBorrowLogs().remove(borrowLog);
		borrowLog.setParty(null);

		return borrowLog;
	}

	public List<MyTrustCircle> getMyTrustCircles1() {
		return this.myTrustCircles1;
	}

	public void setMyTrustCircles1(List<MyTrustCircle> myTrustCircles1) {
		this.myTrustCircles1 = myTrustCircles1;
	}

	public MyTrustCircle addMyTrustCircles1(MyTrustCircle myTrustCircles1) {
		getMyTrustCircles1().add(myTrustCircles1);
		myTrustCircles1.setParty1(this);

		return myTrustCircles1;
	}

	public MyTrustCircle removeMyTrustCircles1(MyTrustCircle myTrustCircles1) {
		getMyTrustCircles1().remove(myTrustCircles1);
		myTrustCircles1.setParty1(null);

		return myTrustCircles1;
	}

	public List<MyTrustCircle> getMyTrustCircles2() {
		return this.myTrustCircles2;
	}

	public void setMyTrustCircles2(List<MyTrustCircle> myTrustCircles2) {
		this.myTrustCircles2 = myTrustCircles2;
	}

	public MyTrustCircle addMyTrustCircles2(MyTrustCircle myTrustCircles2) {
		getMyTrustCircles2().add(myTrustCircles2);
		myTrustCircles2.setParty2(this);

		return myTrustCircles2;
	}

	public MyTrustCircle removeMyTrustCircles2(MyTrustCircle myTrustCircles2) {
		getMyTrustCircles2().remove(myTrustCircles2);
		myTrustCircles2.setParty2(null);

		return myTrustCircles2;
	}

	public List<PartyAuthMech> getPartyAuthMeches() {
		return this.partyAuthMeches;
	}

	public void setPartyAuthMeches(List<PartyAuthMech> partyAuthMeches) {
		this.partyAuthMeches = partyAuthMeches;
	}

	public PartyAuthMech addPartyAuthMech(PartyAuthMech partyAuthMech) {
		getPartyAuthMeches().add(partyAuthMech);
		partyAuthMech.setParty(this);

		return partyAuthMech;
	}

	public PartyAuthMech removePartyAuthMech(PartyAuthMech partyAuthMech) {
		getPartyAuthMeches().remove(partyAuthMech);
		partyAuthMech.setParty(null);

		return partyAuthMech;
	}

	public List<PersonalisedUiCard> getPersonalisedUiCards() {
		return this.personalisedUiCards;
	}

	public void setPersonalisedUiCards(List<PersonalisedUiCard> personalisedUiCards) {
		this.personalisedUiCards = personalisedUiCards;
	}

	public PersonalisedUiCard addPersonalisedUiCard(PersonalisedUiCard personalisedUiCard) {
		getPersonalisedUiCards().add(personalisedUiCard);
		personalisedUiCard.setParty(this);

		return personalisedUiCard;
	}

	public PersonalisedUiCard removePersonalisedUiCard(PersonalisedUiCard personalisedUiCard) {
		getPersonalisedUiCards().remove(personalisedUiCard);
		personalisedUiCard.setParty(null);

		return personalisedUiCard;
	}

	public List<ProductListing> getProductListings() {
		return this.productListings;
	}

	public void setProductListings(List<ProductListing> productListings) {
		this.productListings = productListings;
	}

	public ProductListing addProductListing(ProductListing productListing) {
		getProductListings().add(productListing);
		productListing.setParty(this);

		return productListing;
	}

	public ProductListing removeProductListing(ProductListing productListing) {
		getProductListings().remove(productListing);
		productListing.setParty(null);

		return productListing;
	}

}