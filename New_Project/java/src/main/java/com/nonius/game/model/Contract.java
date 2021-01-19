package com.nonius.game.model;

public class Contract {
	
	private int id;
	private String name;
	private String cost;
	private String desc;
	private int dura;
	private int counters;
	private boolean isActive;
	
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getDura() {
		return dura;
	}
	public void setDura(int dura) {
		this.dura = dura;
	}
	public int getCounters() {
		return counters;
	}
	public void setCounters(int counters) {
		this.counters = counters;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
