package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PERSONALISED_CARDS database table.
 * 
 */
@Entity
@Table(name="PERSONALISED_CARDS")
@NamedQuery(name="PersonalisedCard.findAll", query="SELECT p FROM PersonalisedCard p")
public class PersonalisedCard implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name="PC_CONSUMED", length=1)
	private String pcConsumed;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PC_EXPIRY")
	private Date pcExpiry;

	//bi-directional many-to-one association to UiCard
	@ManyToOne
	@JoinColumn(name="UIC_ID", nullable=false)
	private UiCard uiCard;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="PTY_ID", nullable=false)
	private Party party;

	public PersonalisedCard() {
	}

	public String getPcConsumed() {
		return this.pcConsumed;
	}

	public void setPcConsumed(String pcConsumed) {
		this.pcConsumed = pcConsumed;
	}

	public Date getPcExpiry() {
		return this.pcExpiry;
	}

	public void setPcExpiry(Date pcExpiry) {
		this.pcExpiry = pcExpiry;
	}

	public UiCard getUiCard() {
		return this.uiCard;
	}

	public void setUiCard(UiCard uiCard) {
		this.uiCard = uiCard;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

}