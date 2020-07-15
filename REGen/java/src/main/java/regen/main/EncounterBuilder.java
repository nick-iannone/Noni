package regen.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Setting;
import regen.view.SoundEffect;


public class EncounterBuilder {
	
	private int encounterID;
	private int difficultyRating;
	private PlayerCharacter player;
	private Map<Integer, Mob> mobs;
	private Setting setting;
	private List<String> rewards;
	private int xpReward;
	private EncounterBuilderUI ebui;
	private String selection;
	private Scanner scanner;
	private SoundEffect sound;
	
	public EncounterBuilder() {
		
		this.ebui = new EncounterBuilderUI();
		this.scanner = new Scanner(System.in);
		this.selection = "";
		this.difficultyRating = 0;
		this.mobs = new HashMap<Integer, Mob>();
		this.setting = new Setting();
		this.sound = new SoundEffect();
	}

	
	public void callEncounterBuilderMenu() {
		
		ebui.printEncounterBuilderMenuFull();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callEncounterBuilderMenuLight() {
		
		ebui.printEncounterBuilderMenuLight();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callSetDifficultyScreen(List<Mob> mobList) {
		
		ebui.printSetDifficulty();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
		difficultyRating = Integer.parseInt(selection);
		if (difficultyRating > 5 || difficultyRating < 1) {
			difficultyRating = 1;
			ebui.printInvalidDifficultySelection();
		}
		
		while (mobs.size() < difficultyRating) {
			int index = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			mobs.put(mobList.get(index).getMobID(), mobList.get(index));
		}
	}
	
	public void callSelectLocationScreen(List<Setting> locationList) {
		
		ebui.printSelectLocationMenu();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
		for (Setting s : locationList) {
			if (s.getSettingID() == Integer.parseInt(selection)) {
				setting = s;
			}
		}
	}
	
	public void generateRandomEncounter(List<Mob> mobList, List<Setting> location) {
		
		mobs.clear();
		
		difficultyRating = ThreadLocalRandom.current().nextInt(1, 5 + 1);
		int randSettingID = ThreadLocalRandom.current().nextInt(1, 5 + 1);
		
		for (Setting s : location) {
			if (s.getSettingID() == randSettingID) {
				setting = s;
			}
		}
		
		while (mobs.size() < difficultyRating) {
			int index = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			mobs.put(mobList.get(index).getMobID(), mobList.get(index));
		}
		
		ebui.printEncounterReadyPrompt();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callPrintEncounterReady() {
		ebui.printEncounterReadyPrompt();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void viewEncounterDetails() {
		ebui.printEncounterDetails(difficultyRating, player, mobs, setting);
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public int getDifficultyRating() {
		return difficultyRating;
	}
	public void setDifficultyRating(int difficultyRating) {
		this.difficultyRating = difficultyRating;
	}
	public PlayerCharacter getPlayer() {
		return player;
	}
	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	public List<String> getRewards() {
		return rewards;
	}
	public void setRewards(List<String> rewards) {
		this.rewards = rewards;
	}
	public int getXpReward() {
		return xpReward;
	}
	public void setXpReward(int xpReward) {
		this.xpReward = xpReward;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public int getEncounterID() {
		return encounterID;
	}
	public void setEncounterID(int encounterID) {
		this.encounterID = encounterID;
	}
	public Map<Integer, Mob> getMobs() {
		return mobs;
	}
	public void setMobs(Map<Integer, Mob> mobs) {
		this.mobs = mobs;
	}
}
