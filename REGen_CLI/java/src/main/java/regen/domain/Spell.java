package regen.domain;


public class Spell {
	
	private String name;
	private String school;
	private String damageType;
	private int damageDie;
	private int modAC;
	private int modHP;
	private int modAttack;
	private int modSpell;
	private int duration;
	private String target;
	private String description;
	private int spellID;

	
	public Spell() {
		
	}
	
	
	public int getSpellID() {
		return spellID;
	}
	public void setSpellID(int spellID) {
		this.spellID = spellID;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDamageDie() {
		return damageDie;
	}
	public void setDamageDie(int damage) {
		this.damageDie = damage;
	}
	public String getDamageType() {
		return damageType;
	}
	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
}
