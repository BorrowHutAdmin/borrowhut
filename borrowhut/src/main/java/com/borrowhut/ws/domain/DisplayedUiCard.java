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

	/*@EmbeddedId
	private DisplayedUiCardPK id;*/

	@Id
	@Column(name="ID", unique=true, nullable=false)
	private int id;

	@Column(name="UIC_ID", insertable=false, updatable=false, unique=true, nullable=false, length=10)
	private String uicId;

	@Column(name="TOKEN_NAME", unique=true, nullable=false, length=30)
	private String tokenName;
	
	
	@Column(name="TOKEN_VALUE", length=45)
	private String tokenValue;
	
	@Column(name="CARD_FACE",length=10)
	private String cardFace;

	public int getId() {
		return id;
	}

	public String getCardFace() {
		return cardFace;
	}

	public void setCardFace(String cardFace) {
		this.cardFace = cardFace;
	}

	//bi-directional many-to-one association to UiCard
	@ManyToOne
	@JoinColumn(name="UIC_ID", nullable=false, insertable=false, updatable=false)
	private UiCard uiCard;
	
	

	public DisplayedUiCard() {
	}

	/*public DisplayedUiCardPK getId() {
		return this.id;
	}

	public void setId(DisplayedUiCardPK id) {
		this.id = id;
	}*/

	
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