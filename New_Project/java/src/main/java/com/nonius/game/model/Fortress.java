package com.nonius.game.model;

import java.util.List;

public class Fortress {
	
	private int integrity;
	private List<Mod> mods;
	
	
	public int getIntegrity() {
		return integrity;
	}
	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}
	public List<Mod> getMods() {
		return mods;
	}
	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}
}
