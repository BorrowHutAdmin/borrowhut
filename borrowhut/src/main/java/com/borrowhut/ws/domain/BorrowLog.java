package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BORROW_LOG database table.
 * 
 */
@Entity
@Table(name="BORROW_LOG")
@NamedQuery(name="BorrowLog.findAll", query="SELECT b FROM BorrowLog b")
public class BorrowLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BOL_ID")
	private int bolId;

	@Column(name="BOL_END")
	private String bolEnd;

	@Column(name="BOL_START")
	private String bolStart;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="BOL_PTY_ID")
	private Party party;

	//bi-directional many-to-one association to ProductListing
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="LENDER_PTY_ID", referencedColumnName="PTY_ID"),
		@JoinColumn(name="PLS_ID", referencedColumnName="PLS_ID")
		})
	private ProductListing productListing;

	//bi-directional many-to-one association to BorrowStatus
	@OneToMany(mappedBy="borrowLog")
	private List<BorrowStatus> borrowStatuses;

	public BorrowLog() {
	}

	public int getBolId() {
		return this.bolId;
	}

	public void setBolId(int bolId) {
		this.bolId = bolId;
	}

	public String getBolEnd() {
		return this.bolEnd;
	}

	public void setBolEnd(String bolEnd) {
		this.bolEnd = bolEnd;
	}

	public String getBolStart() {
		return this.bolStart;
	}

	public void setBolStart(String bolStart) {
		this.bolStart = bolStart;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public ProductListing getProductListing() {
		return this.productListing;
	}

	public void setProductListing(ProductListing productListing) {
		this.productListing = productListing;
	}

	public List<BorrowStatus> getBorrowStatuses() {
		return this.borrowStatuses;
	}

	public void setBorrowStatuses(List<BorrowStatus> borrowStatuses) {
		this.borrowStatuses = borrowStatuses;
	}

	public BorrowStatus addBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().add(borrowStatus);
		borrowStatus.setBorrowLog(this);

		return borrowStatus;
	}

	public BorrowStatus removeBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().remove(borrowStatus);
		borrowStatus.setBorrowLog(null);

		return borrowStatus;
	}


	
	/*
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BorrowLogPK id;

	@Column(name="BOL_END", length=45)
	private String bolEnd;

	@Column(name="BOL_START", length=45)
	private String bolStart;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="BOL_PTY_ID", nullable=false, insertable=false, updatable=false)
	private Party party;

	//bi-directional many-to-one association to ProductListing
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="LENDER_PTY_ID", referencedColumnName="PTY_ID", nullable=false),
		@JoinColumn(name="PLS_ID", referencedColumnName="PLS_ID", nullable=false)
		})
	private ProductListing productListing;

	//bi-directional many-to-one association to BorrowStatus
	@OneToMany(mappedBy="borrowLog")
	private List<BorrowStatus> borrowStatuses;

	public BorrowLog() {
	}

	public BorrowLogPK getId() {
		return this.id;
	}

	public void setId(BorrowLogPK id) {
		this.id = id;
	}

	public String getBolEnd() {
		return this.bolEnd;
	}

	public void setBolEnd(String bolEnd) {
		this.bolEnd = bolEnd;
	}

	public String getBolStart() {
		return this.bolStart;
	}

	public void setBolStart(String bolStart) {
		this.bolStart = bolStart;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public ProductListing getProductListing() {
		return this.productListing;
	}

	public void setProductListing(ProductListing productListing) {
		this.productListing = productListing;
	}

	public List<BorrowStatus> getBorrowStatuses() {
		return this.borrowStatuses;
	}

	public void setBorrowStatuses(List<BorrowStatus> borrowStatuses) {
		this.borrowStatuses = borrowStatuses;
	}

	public BorrowStatus addBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().add(borrowStatus);
		borrowStatus.setBorrowLog(this);

		return borrowStatus;
	}

	public BorrowStatus removeBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().remove(borrowStatus);
		borrowStatus.setBorrowLog(null);

		return borrowStatus;
	}

*/}