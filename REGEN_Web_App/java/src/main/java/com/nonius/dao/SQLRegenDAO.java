package com.nonius.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.nonius.model.CharacterClass;
import com.nonius.model.Mob;
import com.nonius.model.PlayerCharacter;
import com.nonius.model.Race;
import com.nonius.model.Setting;
import com.nonius.model.Spell;
import com.nonius.model.Weapon;


@Component
public class SQLRegenDAO implements RegenDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public SQLRegenDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveCharacter(PlayerCharacter newCharacter) {
		newCharacter.setCharID(getNextCharacterID());
		String sqlSaveCharacter = "INSERT INTO character (character_id, name, class_id, race_id, weapon_id, ac, hp, xp, saved_on, user_id) " +
							   	  "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlSaveCharacter, newCharacter.getCharID(), newCharacter.getName(), newCharacter.getCharClass().getClassID(), 
											  newCharacter.getRace().getRaceID(), newCharacter.getWeapon().getWeaponID(), 
											  newCharacter.getAC(), newCharacter.getHP(), newCharacter.getXP(), newCharacter.getSavedOn(), newCharacter.getUserID());
	}
	
	private int getNextCharacterID() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('character_character_id_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new encounter");
		}
	}
	
	@Override
	public List<PlayerCharacter> getSavedCharacters(int userID) {
		List<PlayerCharacter> savedCharacters = new ArrayList<PlayerCharacter>();
		String sqlGetSavedCharacters = "SELECT * FROM character WHERE user_id = ? ORDER BY character_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSavedCharacters, userID);
		while(results.next()) {
			savedCharacters.add(mapRowToPlayerChar(results));
		}
		return savedCharacters;
	}
	
	private PlayerCharacter mapRowToPlayerChar(SqlRowSet results) {
		
		PlayerCharacter character = new PlayerCharacter();
		
		character.setCharID(results.getInt("character_id"));
		character.setName(results.getString("name"));
		character.setAC(results.getInt("ac"));
		character.setHP(results.getInt("hp"));
		character.setXP(results.getInt("xp"));
		int raceID = results.getInt("race_id");
		int classID = results.getInt("class_id");
		int weaponID = results.getInt("weapon_id");
		
		for(Race r : getListOfRaces()) {
			if (r.getRaceID() == raceID) {
				character.setRace(r);
			}
		}
		for(CharacterClass c : getListOfClasses()) {
			if (c.getClassID() == classID) {
				character.setCharClass(c);
			}
		}
		for(Weapon w : getListOfWeapons()) {
			if (w.getWeaponID() == weaponID) {
				character.setWeapon(w);
			}
		}
		character.setModAttack(character.getRace().getModAttack() + character.getCharClass().getModAttack() + character.getWeapon().getModAttack());
		character.setModSpell(character.getRace().getModSpell() + character.getCharClass().getModSpell() + character.getWeapon().getModSpell());
		
		return character;
	}
	
	@Override
	public List<Race> getListOfRaces() {
		
		List<Race> raceList = new ArrayList<Race>();
		String sqlGetListOfRaces = "SELECT * FROM race ORDER BY race_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfRaces);
		while(results.next()) {
			raceList.add(mapRowToRace(results));
		}
		return raceList;
	}
	
	private Race mapRowToRace(SqlRowSet results) {
		
		Race race = new Race();
		race.setRaceID(results.getInt("race_id"));
		race.setName(results.getString("name"));
		race.setModAC(results.getInt("modac"));
		race.setModHP(results.getInt("modhp"));
		race.setModAttack(results.getInt("modattack"));
		race.setModSpell(results.getInt("modspell"));
		race.setDescription(results.getString("description"));
			
		return race;
	}
	
	@Override
	public List<CharacterClass> getListOfClasses() {
		
		List<CharacterClass> classList = new ArrayList<CharacterClass>();
		String sqlGetListOfClasses = "SELECT * FROM class ORDER BY class_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfClasses);
		while(results.next()) {
			classList.add(mapRowToClass(results));
		}
		for (CharacterClass c : classList) {
			c.setSpellList(getListOfSpellsByClass(c.getClassID()));
		}
		return classList;
	}
	
	private CharacterClass mapRowToClass(SqlRowSet results) {
		
		CharacterClass charClass = new CharacterClass();
		charClass.setClassID(results.getInt("class_id"));
		charClass.setName(results.getString("name"));
		charClass.setModAC(results.getInt("modac"));
		charClass.setModHP(results.getInt("modhp"));
		charClass.setModAttack(results.getInt("modattack"));
		charClass.setModSpell(results.getInt("modspell"));
		charClass.setNumberOfSpellSlots(results.getInt("spellslots"));
		charClass.setDescription(results.getString("description"));
		
		return charClass;
	}
	
	@Override
	public List<Weapon> getListOfWeapons() {
		
		List<Weapon> weapons =  new ArrayList<Weapon>();
		String sqlGetListOfWeapons = "SELECT * FROM weapon ORDER BY weapon_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfWeapons);
		while (results.next()) {
			weapons.add(mapRowToWeapon(results));
		}
		
		return weapons;
	}
	
	private Weapon mapRowToWeapon(SqlRowSet results) {
		
		Weapon weapon = new Weapon();
		weapon.setWeaponID(results.getInt("weapon_id"));
		weapon.setName(results.getString("name"));
		weapon.setDamageDie(results.getInt("damagedie"));
		weapon.setModAttack(results.getInt("modattack"));
		weapon.setModSpell(results.getInt("modspell"));
		weapon.setRanged(results.getBoolean("isranged"));
		weapon.setDescription(results.getString("description"));
		
		return weapon;
	}
	
	@Override
	public List<Mob> getMobList() {
		
		List<Mob> mobList = new ArrayList<Mob>();
		String sqlGetMobList = "SELECT * FROM mob ORDER BY difficulty_rating, name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetMobList);
		while (results.next() ) {
			mobList.add(mapRowToMob(results));
		}
		
		return mobList;
	}
	
	private Mob mapRowToMob(SqlRowSet results) {
		
		Mob mob = new Mob();
	
		mob.setMobID(results.getInt("mob_id"));
		mob.setName(results.getString("name"));
		mob.setAC(results.getInt("ac"));
		mob.setHP(results.getInt("hp"));
		int raceID = results.getInt("race_id");
		int classID = results.getInt("class_id");
		int weaponID = results.getInt("weapon_id");
		
		for(Race r : getListOfRaces()) {
			if (r.getRaceID() == raceID) {
				mob.setRace(r);
			}
		}
		for(CharacterClass c : getListOfClasses()) {
			if (c.getClassID() == classID) {
				mob.setCharClass(c);
			}
		}
		for(Weapon w : getListOfWeapons()) {
			if (w.getWeaponID() == weaponID) {
				mob.setWeapon(w);
			}
		}
		mob.setModAttack(mob.getRace().getModAttack() + mob.getCharClass().getModAttack() + mob.getWeapon().getModAttack());
		mob.setModSpell(mob.getRace().getModSpell() + mob.getCharClass().getModSpell() + mob.getWeapon().getModSpell());
		
		return mob;
	}
	
	@Override
	public List<Setting> getListOfLocations () {
		
		List<Setting> locationList = new ArrayList<Setting>();
		String sqlGetListOfLocations = "SELECT * FROM location ORDER BY location_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfLocations);
		while (results.next()) {
			locationList.add(mapRowToLocation(results));
		}
		
		return locationList;
	}
	
	private Setting mapRowToLocation (SqlRowSet results) {
		
		Setting locale = new Setting();
		locale.setName(results.getString("name"));
		locale.setSettingID(results.getInt("location_id"));
		
		return locale;
	}
	
	@Override
	public List<Spell> getListOfSpells() {
		
		List<Spell> spellList = new ArrayList<Spell>();
		String sqlGetListOfSpells = "SELECT * FROM spell ORDER BY spell_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfSpells);
		while (results.next()) {
			spellList.add(mapRowToSpell(results));
		}
		return spellList;
	}
	
	private Spell mapRowToSpell(SqlRowSet results) {
		
		Spell spell = new Spell();
		spell.setSpellID(results.getInt("spell_id"));
		spell.setName(results.getString("name"));
		spell.setSchool(results.getString("school"));
		spell.setDamageType(results.getString("damagetype"));
		spell.setDamageDie(results.getInt("damagedie"));
		spell.setModAC(results.getInt("modac"));
		spell.setModHP(results.getInt("modhp"));
		spell.setModAttack(results.getInt("modattack"));
		spell.setModSpell(results.getInt("modspell"));
		spell.setDuration(results.getInt("duration"));
		spell.setTarget(results.getString("target"));
		spell.setDescription(results.getString("description"));
		
		return spell;
	}
	
	@Override
	public List<Spell> getListOfSpellsByClass(int classID) {
		
		List<Spell> spellListClass = new ArrayList<Spell>();
		String sqlGetListOfSpells = "SELECT spell_id FROM class_spell WHERE class_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfSpells, classID);
		while (results.next()) {
			for (Spell s : getListOfSpells()) {
				if (s.getSpellID() == results.getInt("spell_id")) {
					spellListClass.add(s);
				}
			}
		}
		return spellListClass;
	}
}
