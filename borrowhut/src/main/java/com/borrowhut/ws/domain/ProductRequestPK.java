package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRODUCT_REQUEST database table.
 * 
 */
@Embeddable
public class ProductRequestPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REQUEST_ID")
	private int requestId;

	@Column(name="PARTY_PTY_ID", insertable=false, updatable=false)
	private int partyPtyId;

	public ProductRequestPK() {
	}
	public int getRequestId() {
		return this.requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getPartyPtyId() {
		return this.partyPtyId;
	}
	public void setPartyPtyId(int partyPtyId) {
		this.partyPtyId = partyPtyId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductRequestPK)) {
			return false;
		}
		ProductRequestPK castOther = (ProductRequestPK)other;
		return 
			(this.requestId == castOther.requestId)
			&& (this.partyPtyId == castOther.partyPtyId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.requestId;
		hash = hash * prime + this.partyPtyId;
		
		return hash;
	}
}