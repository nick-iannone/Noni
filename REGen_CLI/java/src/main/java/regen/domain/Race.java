package regen.domain;

public class Race {
	
	private String name;
	private int modAC;
	private int modHP;
	private int modAttack;
	private int modSpell;
	private int raceID;
	
	public Race() {
		
	}

	
	public int getRaceID() {
		return raceID;
	}
	public void setRaceID(int raceID) {
		this.raceID = raceID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getModAC() {
		return modAC;
	}
	public void setModAC(int modAC) {
		this.modAC = modAC;
	}
	public int getModHP() {
		return modHP;
	}
	public void setModHP(int modHP) {
		this.modHP = modHP;
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
	
}
