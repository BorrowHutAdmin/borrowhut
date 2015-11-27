package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PERSONALISED_TOKENS database table.
 * 
 */
@Entity
@Table(name="PERSONALISED_TOKENS")
@NamedQuery(name="PersonalisedToken.findAll", query="SELECT p FROM PersonalisedToken p")
public class PersonalisedToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TOKEN_NAME")
	private String tokenName;

	@Column(name="TOKEN_VALUE")
	private String tokenValue;

	//bi-directional many-to-one association to PersonalisedUiCard
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="PERSONALISED_UI_CARDS_PTY_ID", referencedColumnName="PTY_ID"),
		@JoinColumn(name="PERSONALISED_UI_CARDS_UIC_ID", referencedColumnName="UIC_ID")
		})
	private PersonalisedUiCard personalisedUiCard;

	public PersonalisedToken() {
	}

	public String getTokenName() {
		return this.tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTokenValue() {
		return this.tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public PersonalisedUiCard getPersonalisedUiCard() {
		return this.personalisedUiCard;
	}

	public void setPersonalisedUiCard(PersonalisedUiCard personalisedUiCard) {
		this.personalisedUiCard = personalisedUiCard;
	}

}