package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DISPLAYED_UI_CARDS database table.
 * 
 */
@Entity
@Table(name="DISPLAYED_UI_CARDS")
@NamedQuery(name="DisplayedUiCard.findAll", query="SELECT d FROM DisplayedUiCard d")
public class DisplayedUiCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DisplayedUiCardPK id;

	@Column(name="TOKEN_VALUE", length=45)
	private String tokenValue;

	//bi-directional many-to-one association to UiCard
	@ManyToOne
	@JoinColumn(name="UIC_ID", nullable=false, insertable=false, updatable=false)
	private UiCard uiCard;

	public DisplayedUiCard() {
	}

	public DisplayedUiCardPK getId() {
		return this.id;
	}

	public void setId(DisplayedUiCardPK id) {
		this.id = id;
	}

	public String getTokenValue() {
		return this.tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public UiCard getUiCard() {
		return this.uiCard;
	}

	public void setUiCard(UiCard uiCard) {
		this.uiCard = uiCard;
	}

}