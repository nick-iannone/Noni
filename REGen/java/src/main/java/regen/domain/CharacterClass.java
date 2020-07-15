package regen.domain;

import java.util.List;


public class CharacterClass {
	
	private String name;
	private int modHP;
	private int modAC;
	private int modAttack;
	private int modSpell;
	private List<Spell> spellList;
	private int numberOfSpellSlots;
	private int classID;
	
	public CharacterClass() {
		
	}
	
	
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getModHP() {
		return modHP;
	}
	public void setModHP(int modHP) {
		this.modHP = modHP;
	}
	public int getModAC() {
		return modAC;
	}
	public void setModAC(int modAC) {
		this.modAC = modAC;
	}
	public int getModAttack() {
		return modAttack;
	}
	public void setModAttack(int modAttack) {
		this.modAttack = modAttack;
	}
	public List<Spell> getSpellList() {
		return spellList;
	}
	public void setSpellList(List<Spell> spellList) {
		this.spellList = spellList;
	}
	public int getModSpell() {
		return modSpell;
	}
	public void setModSpell(int modSpell) {
		this.modSpell = modSpell;
	}
	public int getNumberOfSpellSlots() {
		return numberOfSpellSlots;
	}
	public void setNumberOfSpellSlots(int numberOfSpellSlots) {
		this.numberOfSpellSlots = numberOfSpellSlots;
	}
	
}
