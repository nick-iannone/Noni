package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.CharacterClass;
import com.techelevator.model.Mob;
import com.techelevator.model.PlayerCharacter;
import com.techelevator.model.Race;
import com.techelevator.model.Setting;
import com.techelevator.model.Spell;
import com.techelevator.model.Weapon;

public interface RegenDAO {
	
	public void saveCharacter(PlayerCharacter newCharacter);
	public List<PlayerCharacter> getSavedCharacters(int userID);
	public List<Race> getListOfRaces();
	public List<CharacterClass> getListOfClasses();
	public List<Weapon> getListOfWeapons();
	public List<Mob> getMobList();
	public List<Setting> getListOfLocations ();
	public List<Spell> getListOfSpells();
	public List<Spell> getListOfSpellsByClass(int classID);
}
