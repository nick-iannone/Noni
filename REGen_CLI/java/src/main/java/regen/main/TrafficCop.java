package regen.main;

import java.util.List;

import javax.sql.DataSource;

import regen.dao.JDBCDomainDAO;
import regen.domain.CharacterClass;
import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Race;
import regen.domain.Setting;
import regen.domain.Spell;
import regen.domain.Weapon;


public class TrafficCop {
	
	private JDBCDomainDAO dao;
	
	public TrafficCop(DataSource dataSource) {
		
		this.dao = new JDBCDomainDAO(dataSource);
		
	}
	
	
	public List<Race> getListOfRaces() {
		
		return dao.getListOfRaces();
	}
	public List<CharacterClass> getListOfClasses() {
		
		return dao.getListOfClasses();
	}
	public List<Weapon> getListOfWeapons() {
		
		return dao.getListOfWeapons();
	}
	public List<Setting> getLocationList() {
		
		return dao.getListOfLocations();
	}
	public List<Spell> getListOfSpells() {
		
		return dao.getListOfSpells();
	}
	public List<Mob> getMobList() {
		
		return dao.getMobList();
	}
	public List<PlayerCharacter> getSavedCharacters() {
		
		return dao.getSavedCharacters();
	}
	public void saveCharacter(PlayerCharacter newSavedChar) {
		
		dao.saveCharacter(newSavedChar);
	}
	public List<Spell> getListOfSpellsByClass(int classID) {
		
		return dao.getListOfSpellsByClass(classID);
	}
}
