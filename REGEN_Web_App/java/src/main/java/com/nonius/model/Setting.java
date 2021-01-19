package com.techelevator.model;

public class Setting {
	
	private String name;
	private String description;
	private int settingID;
	private boolean isDifficultTerrain;
	private boolean isScary;
	private boolean isPleasant;
	
	
	public Setting() {
		
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDifficultTerrain() {
		return isDifficultTerrain;
	}
	public void setDifficultTerrain(boolean isDifficultTerrain) {
		this.isDifficultTerrain = isDifficultTerrain;
	}
	public boolean isScary() {
		return isScary;
	}
	public void setScary(boolean isScary) {
		this.isScary = isScary;
	}
	public boolean isPleasant() {
		return isPleasant;
	}
	public void setPleasant(boolean isPleasant) {
		this.isPleasant = isPleasant;
	}
	public int getSettingID() {
		return settingID;
	}
	public void setSettingID(int settingID) {
		this.settingID = settingID;
	}
}
