package com.nonius.websocket.model;

import java.util.List;

import com.nonius.game.model.Card;
import com.nonius.game.model.GameState;

public class GameActionMessage {
	private String username;
	private Boolean readyForBattle;
	private Boolean keepHand;
	private Boolean keepContracts;
	private Boolean turnBegin;
	private Boolean isTurn;
	private int turnCount;
	private Card attacker;
	private Card target;
	private List<Card> hasAttacked;
	private Card deadOppCard;
	private GameState gameState;
	
	
	public List<Card> getHasAttacked() {
		return hasAttacked;
	}
	public void setHasAttacked(List<Card> hasAttacked) {
		this.hasAttacked = hasAttacked;
	}
	public Card getDeadOppCard() {
		return deadOppCard;
	}
	public void setDeadOppCard(Card deadCard) {
		this.deadOppCard = deadCard;
	}
	public Card getAttacker() {
		return attacker;
	}
	public void setAttacker(Card attacker) {
		this.attacker = attacker;
	}
	public Card getTarget() {
		return target;
	}
	public void setTarget(Card target) {
		this.target = target;
	}
	public Boolean getKeepContracts() {
		return keepContracts;
	}
	public void setKeepContracts(Boolean keepContracts) {
		this.keepContracts = keepContracts;
	}
	public Boolean getTurnBegin() {
		return turnBegin;
	}
	public void setTurnBegin(Boolean turnBegin) {
		this.turnBegin = turnBegin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getReadyForBattle() {
		return readyForBattle;
	}
	public void setReadyForBattle(Boolean readyForBattle) {
		this.readyForBattle = readyForBattle;
	}
	public Boolean getKeepHand() {
		return keepHand;
	}
	public void setKeepHand(Boolean keepHand) {
		this.keepHand = keepHand;
	}
	public Boolean getIsTurn() {
		return isTurn;
	}
	public void setIsTurn(Boolean isTurn) {
		this.isTurn = isTurn;
	}
	public int getTurnCount() {
		return turnCount;
	}
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}
