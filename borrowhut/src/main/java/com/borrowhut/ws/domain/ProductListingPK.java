package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRODUCT_LISTING database table.
 * 
 */
@Embeddable
public class ProductListingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PLS_ID", unique=true, nullable=false)
	private int plsId;

	@Column(name="PTY_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int ptyId;

	public ProductListingPK() {
	}
	public int getPlsId() {
		return this.plsId;
	}
	public void setPlsId(int plsId) {
		this.plsId = plsId;
	}
	public int getPtyId() {
		return this.ptyId;
	}
	public void setPtyId(int ptyId) {
		this.ptyId = ptyId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductListingPK)) {
			return false;
		}
		ProductListingPK castOther = (ProductListingPK)other;
		return 
			(this.plsId == castOther.plsId)
			&& (this.ptyId == castOther.ptyId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.plsId;
		hash = hash * prime + this.ptyId;
		
		return hash;
	}
}