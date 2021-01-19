package com.nonius.game.model;

import java.util.List;

public class Deck {
	
	private List<Card> cards;
	private Boolean isEmpty;

	
	public Boolean getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
