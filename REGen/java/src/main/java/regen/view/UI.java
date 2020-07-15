package regen.view;

import java.util.List;
import java.util.Set;

import regen.domain.CharacterClass;
import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Race;
import regen.domain.Spell;
import regen.domain.Weapon;
import regen.main.EncounterBuilder;

public class UI {
	
	
	public void printMainMenu() {
		System.out.println("\n" + 
				"**************   ******************       \n" + 
				"***************  ******************   \n" + 
				"*****       **** ****	                                    \n" + 
				"*****      ***** ****                                        \n" + 
				"**************** **********                \n" + 
				"*************    **********           \n" + 
				"****     *****   ****                    \n" + 
				"****       ***** ****                      \n" + 
				"****         ********************************** \n" + 
				"****          ***********************************    \n" + 
				"      *************      **************************              ****\n" + 
				"    ******************   ***************************             ****\n" + 
				"  ****             ****  *******               *******           ****\n" + 
				" ****              ****  *******               *********         ****\n" + 
				"****                     ***************       ****   ****       ****\n" + 
				"****                     ***************       ****     ****     ****\n" + 
				"****          ********** *******               ****       ****   ****\n" + 
				" ****         ********** *******               ****         **** ****\n" + 
				"   ***************** *** **************************           *******\n" + 
				"     **************  *** **************************             *****");
		System.out.print("\n");
		System.out.println("Random Encounter Generator |  Ver. 1.0  | A CLI program by Nick Iannone");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("*************************** WHERE TO, BOSS? ****************************");
		System.out.print("\n");
		System.out.println("[1] Create New Character");
		System.out.println("[2] View Saved Characters");
		System.out.println("[3] Quit");
		System.out.print(">>> ");
	}
	
	public void printReturnToMainMenu() {
		System.out.print("\n");
		System.out.println("Random Encounter Generator |  Ver. 1.0  | A CLI program by Nick Iannone");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("*************************** WHERE TO, BOSS? ****************************");
		System.out.print("\n");
		System.out.println("[1] Create New Character");
		System.out.println("[2] View Saved Characters");
		System.out.println("[3] Quit");
		System.out.print(">>> ");
	}
	
	public void printLoadSavedCharacters(List<PlayerCharacter> savedCharList) {
		
	}
	
	public void printCharacterCreationMenu() {
		
		System.out.print("\n");
		System.out.println(" ------------------------");
		System.out.println("| CHARACTER CREATION HUB |");
		System.out.println(" ------------------------ ");
		System.out.print("\n");
		System.out.println("Please enter a name for your character");
		System.out.print(">>> ");
	}

	public void printListOfRacesToSelect(String name, List<Race> raceList) {
		
		System.out.print("\n");
		System.out.println("Please select a race for " + name + " by entering the corresponding ID number");
		System.out.print("\n");
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s", "[ID]", "Name", "AC Mod", "HP Mod", "Attack Mod", "Spell Mod");
		System.out.println("\n");
		for(Race r : raceList) {
			System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s", "[" + r.getRaceID() + "]", r.getName(), r.getModAC(), 
																	 r.getModHP(), r.getModAttack(), r.getModSpell());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printListOfClassesToSelect(String name, List<CharacterClass> classList) {
		
		System.out.print("\n");
		System.out.println("Please select a class for " + name + " by entering the corresponding ID number");
		System.out.print("\n");
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s", "[ID]", "Name", "AC Mod", "HP Mod", "Attack Mod", "Spell Mod", "Spell Slots");
		System.out.println("\n");
		for(CharacterClass c : classList) {
			System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s", "[" + c.getClassID() + "]", c.getName(), 
																		  c.getModAC(), c.getModHP(), c.getModAttack(), 
																		  c.getModSpell(), c.getNumberOfSpellSlots());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printListOfWeaponsToSelect(String name, List<Weapon> weaponList) {
		
		System.out.print("\n");
		System.out.println("Please select a weapon for " + name + " by entering the corresponding ID number");
		System.out.print("\n");
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s", "[ID]", "Name", "Damage Die", "Attack Mod", "Spell Mod", "Has Range");
		System.out.println("\n");
		for(Weapon w : weaponList) {
			System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s", "[" + w.getWeaponID() + "]", w.getName(), 
																		  w.getDamageDie(), w.getModAttack(),
																		  w.getModSpell(), w.isRanged());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printCharacterSnapshotNewChar(PlayerCharacter player) {
		
		System.out.print("\n");
		System.out.println("|*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*|  " + player.getName() + "  |*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*| ");
		System.out.print("\n");
		System.out.printf("%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", "Name", "Race", "Class", "Weapon", "Armor Class", "Attack Mod", "Spell Mod", "Hit Points", "XP");
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", player.getName(), player.getRace().getName(),
																		   player.getCharClass().getName(), player.getWeapon().getName(),
																	       player.getAC(), player.getModAttack(),
																	       player.getModSpell(), player.getHP(), player.getXP());
		
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("AVAILABLE SPELLS ");
		System.out.println("**************************************************************************************************************************");
		for (Spell s : player.getCharClass().getSpellList()) {
			System.out.println(s.getName() + " - " + s.getDescription());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.println("******************************");
		System.out.println("What would you like to do now?");
		System.out.print("\n");
		System.out.println("[1] Save Character");
		System.out.println("[2] Quit to Main Menu");
		System.out.print(">>> ");
		
	}
	
	public void printCharacterSnapshotLoadChar(PlayerCharacter player) {
		
		System.out.print("\n");
		System.out.println("|*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*|  " + player.getName() + "  |*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*~_*| ");
		System.out.print("\n");
		System.out.printf("%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", "Name", "Race", "Class", "Weapon", "Armor Class", "Attack Mod", "Spell Mod", "Hit Points", "XP");
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", player.getName(), player.getRace().getName(),
																		   player.getCharClass().getName(), player.getWeapon().getName(),
																	       player.getAC(), player.getModAttack(),
																	       player.getModSpell(), player.getHP(), player.getXP());
		
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("AVAILABLE SPELLS ");
		System.out.println("**************************************************************************************************************************");
		for (Spell s : player.getCharClass().getSpellList()) {
			System.out.println(s.getName() + " - " + s.getDescription());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.println("******************************");
		System.out.println("What would you like to do now?");
		System.out.print("\n");
		System.out.println("[1] Add player to Encounter");
		System.out.println("[2] Quit to Main Menu");
		System.out.print(">>> ");
		
	}
	
	public void printCharSaveSuccessful() {
		System.out.println("\n");
		System.out.println("** Your character was successfully saved! **");
		System.out.println("********************************************");
		System.out.print("\n");
		System.out.println("What would you like to do now?");
		System.out.print("\n");
		System.out.println("[1] Add Player to Encounter");
		System.out.println("[2] Main Menu");
		System.out.print(">>> ");
	}
	
	public void printListOfLoadedCharacters(List<PlayerCharacter> savedCharacters) {
		System.out.println("\n");
		System.out.println("Here are the saved characters we found: ");
		System.out.print("\n");
		System.out.printf("%-5s%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", "[ID]", "Name", "Race", "Class", "Weapon", "Armor Class", "Attack Mod", "Spell Mod", "Hit Points", "XP");
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		for(PlayerCharacter c : savedCharacters) {
			System.out.printf("%-5s%-19s%-10s%-13s%-13s%-15s%-15s%-15s%-15s%-15s", "[" + c.getCharID() + "]", c.getName(), c.getRace().getName(),
																		  c.getCharClass().getName(), c.getWeapon().getName(),
																		  c.getAC(), c.getModAttack(), c.getModSpell(), c.getHP(), c.getXP());
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Select a character to view by entering the ID number, or return to the main menu by entering 0");
		System.out.print(">>> ");
	}
	
	public void printSuccessfulAddToEncounter(String name) {
		System.out.print("\n");
		System.out.println("*+*+* " + name + " successfully added to Encounter! *+*+*");
		System.out.println("\n");
	}
	
	public void printErrorInvalidSelection() {
		System.out.println("\n");
		System.out.println("Invalid Selection. Please try again.");
		System.out.println("\n");
	}
	
	public void printErrorInvalidNameLength() {
		System.out.println("\n");
		System.out.println(" *** Character names must be at least 2 letters long and cannot exceed 16 letters. Please try again. *** ");
	}
	
	public void printGoodbye() {
		System.out.println("\n");
		System.out.println("Thank you for playing");
		System.out.println("########      ########      ######   ######## ##    ## \n" + 
				"##     ##     ##           ##    ##  ##       ###   ## \n" + 
				"##     ##     ##           ##        ##       ####  ## \n" + 
				"########      ######       ##   #### ######   ## ## ## \n" + 
				"##   ##       ##           ##    ##  ##       ##  #### \n" + 
				"##    ##  ### ##       ### ##    ##  ##       ##   ### \n" + 
				"##     ## ### ######## ###  ######   ######## ##    ## ");
		System.out.println("\n");
	}
	public void printListOfSpellsByClass(List<Spell> spellListForClass) {
		
	}
}
