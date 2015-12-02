package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BORROW_STATUS database table.
 * 
 */
@Entity
@Table(name="BORROW_STATUS")
@NamedQuery(name="BorrowStatus.findAll", query="SELECT b FROM BorrowStatus b")
public class BorrowStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BST_ENDDATE")
	private Date bstEnddate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BST_STARTDATE")
	private Date bstStartdate;

	//bi-directional many-to-one association to BorrowLog
	@ManyToOne
	@JoinColumn(name="BOL_ID")
	private BorrowLog borrowLog;

	//bi-directional many-to-one association to BorrowLifecycleEvent
	@ManyToOne
	@JoinColumn(name="BLE_EVENT")
	private BorrowLifecycleEvent borrowLifecycleEvent;

	public BorrowStatus() {
	}

	public Date getBstEnddate() {
		return this.bstEnddate;
	}

	public void setBstEnddate(Date bstEnddate) {
		this.bstEnddate = bstEnddate;
	}

	public Date getBstStartdate() {
		return this.bstStartdate;
	}

	public void setBstStartdate(Date bstStartdate) {
		this.bstStartdate = bstStartdate;
	}

	public BorrowLog getBorrowLog() {
		return this.borrowLog;
	}

	public void setBorrowLog(BorrowLog borrowLog) {
		this.borrowLog = borrowLog;
	}

	public BorrowLifecycleEvent getBorrowLifecycleEvent() {
		return this.borrowLifecycleEvent;
	}

	public void setBorrowLifecycleEvent(BorrowLifecycleEvent borrowLifecycleEvent) {
		this.borrowLifecycleEvent = borrowLifecycleEvent;
	}


	/*
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BorrowStatusPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BST_ENDDATE")
	private Date bstEnddate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BST_STARTDATE")
	private Date bstStartdate;

	//bi-directional many-to-one association to BorrowLifecycleEvent
	@ManyToOne
	@JoinColumn(name="BLE_EVENT", nullable=false, insertable=false, updatable=false)
	private BorrowLifecycleEvent borrowLifecycleEvent;

	//bi-directional many-to-one association to BorrowLog
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="BOL_ID", referencedColumnName="BOL_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="BOL_PTY_ID", referencedColumnName="BOL_PTY_ID", nullable=false, insertable=false, updatable=false)
		})
	private BorrowLog borrowLog;

	public BorrowStatus() {
	}

	public BorrowStatusPK getId() {
		return this.id;
	}

	public void setId(BorrowStatusPK id) {
		this.id = id;
	}

	public Date getBstEnddate() {
		return this.bstEnddate;
	}

	public void setBstEnddate(Date bstEnddate) {
		this.bstEnddate = bstEnddate;
	}

	public Date getBstStartdate() {
		return this.bstStartdate;
	}

	public void setBstStartdate(Date bstStartdate) {
		this.bstStartdate = bstStartdate;
	}

	public BorrowLifecycleEvent getBorrowLifecycleEvent() {
		return this.borrowLifecycleEvent;
	}

	public void setBorrowLifecycleEvent(BorrowLifecycleEvent borrowLifecycleEvent) {
		this.borrowLifecycleEvent = borrowLifecycleEvent;
	}

	public BorrowLog getBorrowLog() {
		return this.borrowLog;
	}

	public void setBorrowLog(BorrowLog borrowLog) {
		this.borrowLog = borrowLog;
	}

*/}