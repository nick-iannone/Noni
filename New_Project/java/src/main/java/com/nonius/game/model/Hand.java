package com.nonius.game.model;

import java.util.List;

public class Hand {
	
	private List<Card> cards;
	
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> hand) {
		this.cards = hand;
	}
}
