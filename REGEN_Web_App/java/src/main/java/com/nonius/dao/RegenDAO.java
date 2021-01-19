package com.nonius.dao;

import java.util.List;

import com.nonius.model.CharacterClass;
import com.nonius.model.Mob;
import com.nonius.model.PlayerCharacter;
import com.nonius.model.Race;
import com.nonius.model.Setting;
import com.nonius.model.Spell;
import com.nonius.model.Weapon;

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
