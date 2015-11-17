package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TRUST_CIRCLE database table.
 * 
 */
@Entity
@Table(name="TRUST_CIRCLE")
@NamedQuery(name="TrustCircle.findAll", query="SELECT t FROM TrustCircle t")
public class TrustCircle  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrustCirclePK id;

	@Column(name="MTC_CIRCLE", length=45)
	private String mtcCircle;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="PTY_ID", nullable=false, insertable=false, updatable=false)
	private Party party1;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="MTC_CONTACT_PTY_ID", nullable=false, insertable=false, updatable=false)
	private Party party2;

	public TrustCircle() {
	}

	public TrustCirclePK getId() {
		return this.id;
	}

	public void setId(TrustCirclePK id) {
		this.id = id;
	}

	public String getMtcCircle() {
		return this.mtcCircle;
	}

	public void setMtcCircle(String mtcCircle) {
		this.mtcCircle = mtcCircle;
	}

	public Party getParty1() {
		return this.party1;
	}

	public void setParty1(Party party1) {
		this.party1 = party1;
	}

	public Party getParty2() {
		return this.party2;
	}

	public void setParty2(Party party2) {
		this.party2 = party2;
	}

}