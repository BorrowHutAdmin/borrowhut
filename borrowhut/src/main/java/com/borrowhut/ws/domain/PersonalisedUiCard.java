package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PERSONALISED_UI_CARDS database table.
 * 
 */
@Entity
@Table(name="PERSONALISED_UI_CARDS")
@NamedQuery(name="PersonalisedUiCard.findAll", query="SELECT p FROM PersonalisedUiCard p")
public class PersonalisedUiCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonalisedUiCardPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUC_EXPIRED")
	private Date pucExpired;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUC_VALIDFROM")
	private Date pucValidfrom;

	//bi-directional many-to-one association to PersonalisedToken
	@OneToMany(mappedBy="personalisedUiCard")
	private List<PersonalisedToken> personalisedTokens;

	//bi-directional many-to-one association to Party
	@ManyToOne
	@JoinColumn(name="PTY_ID")
	private Party party;

	//bi-directional many-to-one association to UiCard
	@ManyToOne
	@JoinColumn(name="UIC_ID")
	private UiCard uiCard;

	public PersonalisedUiCard() {
	}

	public PersonalisedUiCardPK getId() {
		return this.id;
	}

	public void setId(PersonalisedUiCardPK id) {
		this.id = id;
	}

	public Date getPucExpired() {
		return this.pucExpired;
	}

	public void setPucExpired(Date pucExpired) {
		this.pucExpired = pucExpired;
	}

	public Date getPucValidfrom() {
		return this.pucValidfrom;
	}

	public void setPucValidfrom(Date pucValidfrom) {
		this.pucValidfrom = pucValidfrom;
	}

	public List<PersonalisedToken> getPersonalisedTokens() {
		return this.personalisedTokens;
	}

	public void setPersonalisedTokens(List<PersonalisedToken> personalisedTokens) {
		this.personalisedTokens = personalisedTokens;
	}

	public PersonalisedToken addPersonalisedToken(PersonalisedToken personalisedToken) {
		getPersonalisedTokens().add(personalisedToken);
		personalisedToken.setPersonalisedUiCard(this);

		return personalisedToken;
	}

	public PersonalisedToken removePersonalisedToken(PersonalisedToken personalisedToken) {
		getPersonalisedTokens().remove(personalisedToken);
		personalisedToken.setPersonalisedUiCard(null);

		return personalisedToken;
	}

	public Party getParty() {
		return this.party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public UiCard getUiCard() {
		return this.uiCard;
	}

	public void setUiCard(UiCard uiCard) {
		this.uiCard = uiCard;
	}

}