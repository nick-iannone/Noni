package com.nonius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonius.dao.SQLRegenDAO;
import com.nonius.model.CharacterClass;
import com.nonius.model.PlayerCharacter;
import com.nonius.model.Race;
import com.nonius.model.Spell;
import com.nonius.model.Weapon;

@CrossOrigin
@RestController
public class RegenController {
	
	private SQLRegenDAO regenDAO;
	
	public RegenController(SQLRegenDAO regenDAO) {
		this.regenDAO = regenDAO;
	}
	
	@RequestMapping (path = "/races", method = RequestMethod.GET)
	public List<Race> getRaceList() {
		
		return regenDAO.getListOfRaces();
	}
	
	@RequestMapping (path = "/classes", method = RequestMethod.GET)
	public List<CharacterClass> getClassList() {
		
		return regenDAO.getListOfClasses();
	}
	
	@RequestMapping (path = "/weapons", method = RequestMethod.GET)
	public List<Weapon> getWeaponList() {
		
		return regenDAO.getListOfWeapons();
	}

	@RequestMapping (path = "/spells/{id}", method = RequestMethod.GET)
	public List<Spell> getSpellsByClass(@PathVariable int id) {
		return regenDAO.getListOfSpellsByClass(id);
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping (path = "/characters/{id}", method = RequestMethod.POST)
	public void saveCharacter(@RequestBody PlayerCharacter player, @PathVariable int userID) {
		
	}
	
	@RequestMapping (path = "/characters/{id}", method = RequestMethod.GET)
	public List<PlayerCharacter> getSavedCharacters(@PathVariable int userID) {
		
		return regenDAO.getSavedCharacters(userID);
	}

}
