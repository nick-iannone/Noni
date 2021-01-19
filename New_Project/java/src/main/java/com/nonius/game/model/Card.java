package com.nonius.game.model;

public class Card {
	
	private int id;
	private String name;
	private String cost;
	private String imageName;
	private String abilities;
	private String flavor;
	private int attack;
	private int resilience;
	private int loyalty;
	private Boolean isAttacking;
	private Boolean isTarget;
	private Boolean canAttack;
	
	
	public Boolean getCanAttack() {
		return canAttack;
	}
	public void setCanAttack(Boolean canAttack) {
		this.canAttack = canAttack;
	}
	public Boolean getIsAttacking() {
		return isAttacking;
	}
	public void setIsAttacking(Boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	public Boolean getIsTarget() {
		return isTarget;
	}
	public void setIsTarget(Boolean isTarget) {
		this.isTarget = isTarget;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getAbilities() {
		return abilities;
	}
	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}
	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getResilience() {
		return resilience;
	}
	public void setResilience(int resilience) {
		this.resilience = resilience;
	}
	public int getLoyalty() {
		return loyalty;
	}
	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}
}
