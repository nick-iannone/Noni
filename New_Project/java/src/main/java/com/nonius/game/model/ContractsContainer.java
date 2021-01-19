package com.nonius.game.model;

import java.util.List;

public class ContractsContainer {
	
	private List<Contract> available;
	private List<Contract> active;
	private List<Contract> completed;
	private List<Contract> deck;
	private List<Mod> mods;
	
	
	public List<Contract> getAvailable() {
		return available;
	}
	public void setAvailable(List<Contract> available) {
		this.available = available;
	}
	public List<Contract> getActive() {
		return active;
	}
	public void setActive(List<Contract> active) {
		this.active = active;
	}
	public List<Contract> getCompleted() {
		return completed;
	}
	public void setCompleted(List<Contract> completed) {
		this.completed = completed;
	}
	public List<Contract> getDeck() {
		return deck;
	}
	public void setDeck(List<Contract> deck) {
		this.deck = deck;
	}
	public List<Mod> getMods() {
		return mods;
	}
	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}
}
