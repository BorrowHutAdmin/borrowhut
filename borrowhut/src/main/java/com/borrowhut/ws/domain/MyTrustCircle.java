package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
  

/**
 * The persistent class for the MY_TRUST_CIRCLE database table..
 * 
 */
@Entity
@Table(name="MY_TRUST_CIRCLE")
@NamedQuery(name="MyTrustCircle.findAll", query="SELECT m FROM MyTrustCircle m")
public class MyTrustCircle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MyTrustCirclePK id;

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

	public MyTrustCircle() {
	}

	public MyTrustCirclePK getId() {
		return this.id;
	}

	public void setId(MyTrustCirclePK id) {
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