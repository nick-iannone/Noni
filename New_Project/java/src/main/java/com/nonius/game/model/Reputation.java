package com.nonius.game.model;

import java.util.List;

public class Reputation {
	
	private int score;
	private List<Mod> mods;
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Mod> getMods() {
		return mods;
	}
	public void setMods(List<Mod> mods) {
		this.mods = mods;
	}	
}
