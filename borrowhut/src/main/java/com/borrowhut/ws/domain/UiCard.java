package com.borrowhut.ws.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UI_CARDS database table.
 * 
 */
@Entity
@Table(name="UI_CARDS")
@NamedQuery(name="UiCard.findAll", query="SELECT u FROM UiCard u")
public class UiCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", unique=true, nullable=false, length=10)
	private String id;

	@Column(name="CARD_GROUP_SIZE")
	private int cardGroupSize;

	@Column(name="DISPLAY_ORDER")
	private int displayOrder;

	@Column(name="HANDLER_CLASS", length=45)
	private String handlerClass;

	@Column(name="NAME", length=45)
	private String name;

	@Column(name="USER_SPECIFIC", length=1)
	private String userSpecific;

	//bi-directional many-to-one association to DisplayedUiCard
	@OneToMany(mappedBy="uiCard")
	private List<DisplayedUiCard> displayedUiCards;

	//bi-directional many-to-one association to PersonalisedCard
	@OneToMany(mappedBy="uiCard")
	private List<PersonalisedCard> personalisedCards;

	public UiCard() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCardGroupSize() {
		return this.cardGroupSize;
	}

	public void setCardGroupSize(int cardGroupSize) {
		this.cardGroupSize = cardGroupSize;
	}

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getHandlerClass() {
		return this.handlerClass;
	}

	public void setHandlerClass(String handlerClass) {
		this.handlerClass = handlerClass;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserSpecific() {
		return this.userSpecific;
	}

	public void setUserSpecific(String userSpecific) {
		this.userSpecific = userSpecific;
	}

	public List<DisplayedUiCard> getDisplayedUiCards() {
		return this.displayedUiCards;
	}

	public void setDisplayedUiCards(List<DisplayedUiCard> displayedUiCards) {
		this.displayedUiCards = displayedUiCards;
	}

	public DisplayedUiCard addDisplayedUiCard(DisplayedUiCard displayedUiCard) {
		getDisplayedUiCards().add(displayedUiCard);
		displayedUiCard.setUiCard(this);

		return displayedUiCard;
	}

	public DisplayedUiCard removeDisplayedUiCard(DisplayedUiCard displayedUiCard) {
		getDisplayedUiCards().remove(displayedUiCard);
		displayedUiCard.setUiCard(null);

		return displayedUiCard;
	}

	public List<PersonalisedCard> getPersonalisedCards() {
		return this.personalisedCards;
	}

	public void setPersonalisedCards(List<PersonalisedCard> personalisedCards) {
		this.personalisedCards = personalisedCards;
	}

	public PersonalisedCard addPersonalisedCard(PersonalisedCard personalisedCard) {
		getPersonalisedCards().add(personalisedCard);
		personalisedCard.setUiCard(this);

		return personalisedCard;
	}

	public PersonalisedCard removePersonalisedCard(PersonalisedCard personalisedCard) {
		getPersonalisedCards().remove(personalisedCard);
		personalisedCard.setUiCard(null);

		return personalisedCard;
	}

}