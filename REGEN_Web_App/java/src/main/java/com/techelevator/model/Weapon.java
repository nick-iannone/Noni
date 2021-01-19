package com.techelevator.model;


public class Weapon {
	
	private String name;
	private int damageDie;
	private int modAttack;
	private int modSpell;
	private boolean isRanged;
	private int weaponID;
	private String description;
	
	public Weapon() {
		
	}
	
	
	public int getWeaponID() {
		return weaponID;
	}
	public void setWeaponID(int weaponID) {
		this.weaponID = weaponID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDamageDie() {
		return damageDie;
	}
	public void setDamageDie(int damageDie) {
		this.damageDie = damageDie;
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
	public boolean isRanged() {
		return isRanged;
	}
	public void setRanged(boolean isRanged) {
		this.isRanged = isRanged;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
