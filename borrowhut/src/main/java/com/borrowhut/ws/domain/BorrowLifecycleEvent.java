package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BORROW_LIFECYCLE_EVENT database table.
 * 
 */
@Entity
@Table(name="BORROW_LIFECYCLE_EVENT")
@NamedQuery(name="BorrowLifecycleEvent.findAll", query="SELECT b FROM BorrowLifecycleEvent b")
public class BorrowLifecycleEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BLE_EVENT", unique=true, nullable=false, length=10)
	private String bleEvent;

	//bi-directional many-to-one association to BorrowStatus
	@OneToMany(mappedBy="borrowLifecycleEvent")
	private List<BorrowStatus> borrowStatuses;

	public BorrowLifecycleEvent() {
	}

	public String getBleEvent() {
		return this.bleEvent;
	}

	public void setBleEvent(String bleEvent) {
		this.bleEvent = bleEvent;
	}

	public List<BorrowStatus> getBorrowStatuses() {
		return this.borrowStatuses;
	}

	public void setBorrowStatuses(List<BorrowStatus> borrowStatuses) {
		this.borrowStatuses = borrowStatuses;
	}

	public BorrowStatus addBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().add(borrowStatus);
		borrowStatus.setBorrowLifecycleEvent(this);

		return borrowStatus;
	}

	public BorrowStatus removeBorrowStatus(BorrowStatus borrowStatus) {
		getBorrowStatuses().remove(borrowStatus);
		borrowStatus.setBorrowLifecycleEvent(null);

		return borrowStatus;
	}

}