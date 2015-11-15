package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PARTY_AUTH_MECH database table.
 * 
 */
@Embeddable
public class PartyAuthMechPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PTY_ID", insertable=false, updatable=false, unique=true, nullable=false)
	private int ptyId;

	@Column(name="AMH_ID", insertable=false, updatable=false, unique=true, nullable=false, length=10)
	private String amhId;

	public PartyAuthMechPK() {
	}
	public int getPtyId() {
		return this.ptyId;
	}
	public void setPtyId(int ptyId) {
		this.ptyId = ptyId;
	}
	public String getAmhId() {
		return this.amhId;
	}
	public void setAmhId(String amhId) {
		this.amhId = amhId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PartyAuthMechPK)) {
			return false;
		}
		PartyAuthMechPK castOther = (PartyAuthMechPK)other;
		return 
			(this.ptyId == castOther.ptyId)
			&& this.amhId.equals(castOther.amhId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ptyId;
		hash = hash * prime + this.amhId.hashCode();
		
		return hash;
	}
}