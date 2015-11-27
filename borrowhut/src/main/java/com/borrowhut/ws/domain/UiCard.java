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
	@Column(name="ID")
	private String id;

	@Column(name="CARD_GROUP_SIZE")
	private int cardGroupSize;

	@Column(name="DISPLAY_ORDER")
	private int displayOrder;

	@Column(name="HANDLER_CLASS")
	private String handlerClass;

	@Column(name="NAME")
	private String name;

	@Column(name="USER_SPECIFIC")
	private String userSpecific;

	//bi-directional many-to-one association to DisplayedUiCard
	@OneToMany(mappedBy="uiCard")
	private List<DisplayedUiCard> displayedUiCards;

	//bi-directional many-to-one association to PersonalisedUiCard
	@OneToMany(mappedBy="uiCard")
	private List<PersonalisedUiCard> personalisedUiCards;

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

	public List<PersonalisedUiCard> getPersonalisedUiCards() {
		return this.personalisedUiCards;
	}

	public void setPersonalisedUiCards(List<PersonalisedUiCard> personalisedUiCards) {
		this.personalisedUiCards = personalisedUiCards;
	}

	public PersonalisedUiCard addPersonalisedUiCard(PersonalisedUiCard personalisedUiCard) {
		getPersonalisedUiCards().add(personalisedUiCard);
		personalisedUiCard.setUiCard(this);

		return personalisedUiCard;
	}

	public PersonalisedUiCard removePersonalisedUiCard(PersonalisedUiCard personalisedUiCard) {
		getPersonalisedUiCards().remove(personalisedUiCard);
		personalisedUiCard.setUiCard(null);

		return personalisedUiCard;
	}

}