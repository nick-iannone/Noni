package regen.domain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Mob {
	
	private String name;
	private int AC;
	private int HP;
	private Race race;
	private CharacterClass charClass;
	private Weapon weapon;
	private int modAttack;
	private int modSpell;
	private int mobID;
	private int turnOrder;
	private boolean isTurned;
	
	public Mob() {
		this.isTurned = false;
		this.AC = ThreadLocalRandom.current().nextInt(14, 18 + 1);
		this.HP = ThreadLocalRandom.current().nextInt(30, 40 + 1);
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
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public CharacterClass getCharClass() {
		return charClass;
	}
	public void setCharClass(CharacterClass charClass) {
		this.charClass = charClass;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobID() {
		return mobID;
	}
	public void setMobID(int mobID) {
		this.mobID = mobID;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public int getTurnOrder() {
		return turnOrder;
	}
	public void setTurnOrder(int turnOrder) {
		this.turnOrder = turnOrder;
	}
	public boolean isTurned() {
		return isTurned;
	}
	public void setTurned(boolean isTurned) {
		this.isTurned = isTurned;
	}
}
