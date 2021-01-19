package com.nonius.game.model;

import java.util.List;

public class Resources {
	
	private int cache;
	private List<Mod> mods;
	
	
	public int getCache() {
		return cache;
	}
	public void setCache(int cache) {
		this.cache = cache;
	}
	public List<Mod> getMods() {
		return mods;
	}
	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}
}
