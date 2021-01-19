package regen.BattleBox.domain;

public class Minion {
	
	private int damageDie;
	private int HP;
	private int AC;
	private int modAttack;
	private String name; 
	
	public Minion(String name) {
		
		this.damageDie = 6;
		this.modAttack = 4;
		this.HP = 20;
		this.AC = 15;
		this.name = name;
	}

	
	public int getDamageDie() {
		return damageDie;
	}
	public void setDamageDie(int damageDie) {
		this.damageDie = damageDie;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getAC() {
		return AC;
	}
	public void setAC(int aC) {
		AC = aC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getModAttack() {
		return modAttack;
	}
	public void setModAttack(int modAttack) {
		this.modAttack = modAttack;
	}
}
