package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRODUCT database table.
 * 
 */
@Embeddable
public class ProductPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PRD_ID", unique=true, nullable=false)
	private int prdId;

	@Column(name="CAT_NAME", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String catName;

	public ProductPK() {
	}
	public int getPrdId() {
		return this.prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public String getCatName() {
		return this.catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductPK)) {
			return false;
		}
		ProductPK castOther = (ProductPK)other;
		return 
			(this.prdId == castOther.prdId)
			&& this.catName.equals(castOther.catName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.prdId;
		hash = hash * prime + this.catName.hashCode();
		
		return hash;
	}
}