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
public class Party  {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PTY_ID", unique=true, nullable=false)
	private int ptyId;

	@Column(name="PTY_ADDRESS_LINE_1", length=45)
	private String ptyAddressLine1;

	@Column(name="PTY_ADDRESS_LINE_2", length=45)
	private String ptyAddressLine2;

	@Column(name="PTY_ADDRESS_LINE_3", length=45)
	private String ptyAddressLine3;

	@Column(name="PTY_COUNTRY", length=45)
	private String ptyCountry;

	@Column(name="PTY_COUNTY", length=45)
	private String ptyCounty;

	@Column(name="PTY_EMAIL", length=45)
	private String ptyEmail;

	@Column(name="PTY_MOBILE", length=45)
	private String ptyMobile;

	@Column(name="PTY_NAME", length=45)
	private String ptyName;

	@Column(name="PTY_PHOTO", length=45)
	private String ptyPhoto;

	@Column(name="PTY_POST_CODE", length=45)
	private String ptyPostCode;

	@Column(name="PTY_TEL", length=45)
	private String ptyTel;

	@Column(name="PTY_TOWN", length=45)
	private String ptyTown;

	@Column(name="PTY_TRUST_SCORE", length=45)
	private String ptyTrustScore;

	//bi-directional many-to-one association to BorrowLog
	@OneToMany(mappedBy="party")
	private List<BorrowLog> borrowLogs;

	//bi-directional many-to-one association to PartyAuthMech
	@OneToMany(mappedBy="party")
	private List<PartyAuthMech> partyAuthMeches;

	//bi-directional many-to-one association to PersonalisedCard
	

	//bi-directional many-to-one association to ProductListing
	@OneToMany(mappedBy="party")
	private List<ProductListing> productListings;

	//bi-directional many-to-one association to TrustCircle
	@OneToMany(mappedBy="party1")
	private List<TrustCircle> trustCircles1;

	//bi-directional many-to-one association to TrustCircle
	@OneToMany(mappedBy="party2")
	private List<TrustCircle> trustCircles2;

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

	public List<TrustCircle> getTrustCircles1() {
		return this.trustCircles1;
	}

	public void setTrustCircles1(List<TrustCircle> trustCircles1) {
		this.trustCircles1 = trustCircles1;
	}

	public TrustCircle addTrustCircles1(TrustCircle trustCircles1) {
		getTrustCircles1().add(trustCircles1);
		trustCircles1.setParty1(this);

		return trustCircles1;
	}

	public TrustCircle removeTrustCircles1(TrustCircle trustCircles1) {
		getTrustCircles1().remove(trustCircles1);
		trustCircles1.setParty1(null);

		return trustCircles1;
	}

	public List<TrustCircle> getTrustCircles2() {
		return this.trustCircles2;
	}

	public void setTrustCircles2(List<TrustCircle> trustCircles2) {
		this.trustCircles2 = trustCircles2;
	}

	public TrustCircle addTrustCircles2(TrustCircle trustCircles2) {
		getTrustCircles2().add(trustCircles2);
		trustCircles2.setParty2(this);

		return trustCircles2;
	}

	public TrustCircle removeTrustCircles2(TrustCircle trustCircles2) {
		getTrustCircles2().remove(trustCircles2);
		trustCircles2.setParty2(null);

		return trustCircles2;
	}

}