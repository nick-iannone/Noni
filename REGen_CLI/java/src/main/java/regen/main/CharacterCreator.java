package regen.main;

import java.util.List;
import java.util.Scanner;

import regen.domain.CharacterClass;
import regen.domain.PlayerCharacter;
import regen.domain.Race;
import regen.domain.Weapon;


public class CharacterCreator {
	
	private PlayerCharacter player;
	private Scanner scanner;
	
	public CharacterCreator() {
		
		this.scanner = new Scanner(System.in);
		this.player = new PlayerCharacter();
	}
	
	
	public String selectCharacterName() {
		
		boolean isValid = false;
		String validName = null;
		
		while(!isValid) {
			
			String name = scanner.nextLine();
			if (name.length() > 16 || name.length() < 3) {
				System.out.print("\n");
				System.out.println(" *** Character names must be at least 3 letters long and cannot exceed 16 letters. Please try again. *** ");
				System.out.print(">>> ");
			}
			else {
				player.setName(name);
				isValid = true;
				validName = name;
			}
		}
		return validName;
	}
	
	public void selectCharacterRace(List<Race> raceList) {
		
		int selection = scanner.nextInt();
		scanner.nextLine();
		for (Race r : raceList) {
			if (selection == r.getRaceID()) {
				player.setRace(r);
			}
		}
		player.setAC(player.getRace().getModAC() + player.getAC());
		player.setHP(player.getRace().getModHP() + player.getHP());
		player.setModAttack(player.getRace().getModAttack() + player.getModAttack());
		player.setModSpell(player.getRace().getModSpell() + player.getModSpell());
		
	}
	
	public void selectCharacterClass(List<CharacterClass> classList) {
		
		int selection = scanner.nextInt();
		scanner.nextLine();
		for (CharacterClass c : classList) {
			if (selection == c.getClassID()) {
				player.setCharClass(c);
			}
		}
		player.setAC(player.getCharClass().getModAC() + player.getAC());
		player.setHP(player.getCharClass().getModHP() + player.getHP());
		player.setModAttack(player.getCharClass().getModAttack() + player.getModAttack());
		player.setModSpell(player.getCharClass().getModSpell() + player.getModSpell());
	}
	
	public void selectCharacterWeapon(List<Weapon> weaponList) {
		
		int selection = scanner.nextInt();
		scanner.nextLine();
		for (Weapon w : weaponList) {
			if (selection == w.getWeaponID()) {
				player.setWeapon(w);
			}
		}
		player.setModAttack(player.getWeapon().getModAttack() + player.getModAttack());
		player.setModSpell(player.getWeapon().getModSpell() + player.getModSpell());
	}

	public PlayerCharacter getPlayer() {
		return player;
	}
	public void setPlayer(PlayerCharacter player) {
		this.player = player;
	}
	public void resetPlayer() {
		this.player = new PlayerCharacter();
	}
}
