package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PERSONALISED_UI_CARDS database table.
 * 
 */
@Embeddable
public class PersonalisedUiCardPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PTY_ID", insertable=false, updatable=false)
	private int ptyId;

	@Column(name="UIC_ID", insertable=false, updatable=false)
	private String uicId;

	public PersonalisedUiCardPK() {
	}
	public int getPtyId() {
		return this.ptyId;
	}
	public void setPtyId(int ptyId) {
		this.ptyId = ptyId;
	}
	public String getUicId() {
		return this.uicId;
	}
	public void setUicId(String uicId) {
		this.uicId = uicId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonalisedUiCardPK)) {
			return false;
		}
		PersonalisedUiCardPK castOther = (PersonalisedUiCardPK)other;
		return 
			(this.ptyId == castOther.ptyId)
			&& this.uicId.equals(castOther.uicId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ptyId;
		hash = hash * prime + this.uicId.hashCode();
		
		return hash;
	}
}