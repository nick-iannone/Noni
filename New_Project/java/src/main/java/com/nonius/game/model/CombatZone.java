package com.nonius.game.model;

import java.util.List;

public class CombatZone {
	
	private List<Card> cards;
	private List<Mod> mods;
	
	
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public List<Mod> getMods() {
		return mods;
	}
	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}
}
