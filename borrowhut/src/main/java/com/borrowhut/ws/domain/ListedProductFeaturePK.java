package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LISTED_PRODUCT_FEATURE database table.
 * 
 */
@Embeddable
public class ListedProductFeaturePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="FTR_NAME", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String ftrName;

	@Column(name="PRD_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int prdId;

	@Column(name="CAT_NAME", insertable=false, updatable=false, unique=true, nullable=false, length=45)
	private String catName;

	@Column(name="PLS_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int plsId;

	@Column(name="PTY_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int ptyId;

	public ListedProductFeaturePK() {
	}
	public String getFtrName() {
		return this.ftrName;
	}
	public void setFtrName(String ftrName) {
		this.ftrName = ftrName;
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
		if (!(other instanceof ListedProductFeaturePK)) {
			return false;
		}
		ListedProductFeaturePK castOther = (ListedProductFeaturePK)other;
		return 
			this.ftrName.equals(castOther.ftrName)
			&& (this.prdId == castOther.prdId)
			&& this.catName.equals(castOther.catName)
			&& (this.plsId == castOther.plsId)
			&& (this.ptyId == castOther.ptyId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ftrName.hashCode();
		hash = hash * prime + this.prdId;
		hash = hash * prime + this.catName.hashCode();
		hash = hash * prime + this.plsId;
		hash = hash * prime + this.ptyId;
		
		return hash;
	}
}