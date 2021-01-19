package com.nonius.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class PlayerCharacter {
	
	private int AC;
	private int HP;
	private int modAttack;
	private int modSpell;
	private String name;
	private CharacterClass charClass;
	private Race race;
	private Weapon weapon;
	private int XP;
	private int charID;
	private int turnOrder;
	private LocalDateTime savedOn;
	private int userID;


	public PlayerCharacter() {

	}
	
	
	public int getCharID() {
		return charID;
	}
	public void setCharID(int charID) {
		this.charID = charID;
	}
	public int getAC() {
		return AC;
	}
	public void setAC(int aC) {
		AC = aC;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CharacterClass getCharClass() {
		return charClass;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public int getXP() {
		return XP;
	}
	public void setXP(int xP) {
		XP = xP;
	}
	public void setCharClass(CharacterClass charClass) {
		this.charClass = charClass;
	}
	public LocalDateTime getSavedOn() {
		return savedOn;
	}
	public void setSavedOn(LocalDateTime now) {
		this.savedOn = now;
	}
	public int getModAttack() {
		return modAttack;
	}
	public void setModAttack(int modAttack) {
		this.modAttack = modAttack;
	}
	public int getModSpell() {
		return modSpell;
	}
	public void setModSpell(int modSpell) {
		this.modSpell = modSpell;
	}
	public int getTurnOrder() {
		return turnOrder;
	}
	public void setTurnOrder(int turnOrder) {
		this.turnOrder = turnOrder;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
