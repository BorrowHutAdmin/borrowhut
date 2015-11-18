package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DISPLAYED_UI_CARDS database table.
 * 
 */
@Embeddable
public class DisplayedUiCardPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID", unique=true, nullable=false)
	private int id;

	@Column(name="UIC_ID", insertable=false, updatable=false, unique=true, nullable=false, length=10)
	private String uicId;

	@Column(name="TOKEN_NAME", unique=true, nullable=false, length=30)
	private String tokenName;

	public DisplayedUiCardPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUicId() {
		return this.uicId;
	}
	public void setUicId(String uicId) {
		this.uicId = uicId;
	}
	public String getTokenName() {
		return this.tokenName;
	}
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DisplayedUiCardPK)) {
			return false;
		}
		DisplayedUiCardPK castOther = (DisplayedUiCardPK)other;
		return 
			(this.id == castOther.id)
			&& this.uicId.equals(castOther.uicId)
			&& this.tokenName.equals(castOther.tokenName);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.uicId.hashCode();
		hash = hash * prime + this.tokenName.hashCode();
		
		return hash;
	}
}