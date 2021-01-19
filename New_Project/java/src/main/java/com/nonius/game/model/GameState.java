package com.nonius.game.model;

public class GameState {
	
	private Deck deck;
	private Hand hand;
	private CombatZone combatZone;
	private DeadZone deadZone;
	private ContractsContainer contracts;
	private Fortress fortress;
	private Reputation reputation;
	private Resources resources;
	
	
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public CombatZone getCombatZone() {
		return combatZone;
	}
	public void setCombatZone(CombatZone combatZone) {
		this.combatZone = combatZone;
	}
	public DeadZone getDeadZone() {
		return deadZone;
	}
	public void setDeadZone(DeadZone deadZone) {
		this.deadZone = deadZone;
	}
	public ContractsContainer getContracts() {
		return contracts;
	}
	public void setContracts(ContractsContainer contracts) {
		this.contracts = contracts;
	}
	public Fortress getFortress() {
		return fortress;
	}
	public void setFortress(Fortress fortress) {
		this.fortress = fortress;
	}
	public Reputation getReputation() {
		return reputation;
	}
	public void setReputation(Reputation reputation) {
		this.reputation = reputation;
	}
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}	
}
