package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BORROW_LOG database table.
 * 
 */
@Embeddable
public class BorrowLogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BOL_ID", unique=true, nullable=false)
	private int bolId;

	@Column(name="BOL_PTY_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int bolPtyId;

	public BorrowLogPK() {
	}
	public int getBolId() {
		return this.bolId;
	}
	public void setBolId(int bolId) {
		this.bolId = bolId;
	}
	public int getBolPtyId() {
		return this.bolPtyId;
	}
	public void setBolPtyId(int bolPtyId) {
		this.bolPtyId = bolPtyId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BorrowLogPK)) {
			return false;
		}
		BorrowLogPK castOther = (BorrowLogPK)other;
		return 
			(this.bolId == castOther.bolId)
			&& (this.bolPtyId == castOther.bolPtyId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bolId;
		hash = hash * prime + this.bolPtyId;
		
		return hash;
	}
}