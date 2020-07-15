package regen.main;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Setting;


public class EncounterBuilderUI {
	
	private Scanner scanner;
	
	public EncounterBuilderUI() {
		this.scanner = new Scanner(System.in);
	}
	
	
	public void printEncounterBuilderMenuFull() {
		
		System.out.print("\n");
		System.out.println("************************************************************************************");
		System.out.println(" _______  __    _  _______  _______  __   __  __    _  _______  _______  ______   \n" + 
				"|       ||  |  | ||       ||       ||  | |  ||  |  | ||       ||       ||    _ |  \n" + 
				"|    ___||   |_| ||       ||   _   ||  | |  ||   |_| ||_     _||    ___||   | ||  \n" + 
				"|   |___ |       ||       ||  | |  ||  |_|  ||       |  |   |  |   |___ |   |_||_ \n" + 
				"|    ___||  _    ||      _||  |_|  ||       ||  _    |  |   |  |    ___||    __  |\n" + 
				"|   |___ | | |   ||     |_ |       ||       || | |   |  |   |  |   |___ |   |  | |\n" + 
				"|_______||_|  |__||_______||_______||_______||_|  |__|  |___|  |_______||___|  |_|\n" + 
				" _______  __   __  ___   ___      ______   _______  ______                        \n" + 
				"|  _    ||  | |  ||   | |   |    |      | |       ||    _ |                       \n" + 
				"| |_|   ||  | |  ||   | |   |    |  _    ||    ___||   | ||                       \n" + 
				"|       ||  |_|  ||   | |   |    | | |   ||   |___ |   |_||_                      \n" + 
				"|  _   | |       ||   | |   |___ | |_|   ||    ___||    __  |                     \n" + 
				"| |_|   ||       ||   | |       ||       ||   |___ |   |  | |                     \n" + 
				"|_______||_______||___| |_______||______| |_______||___|  |_|                     ");
		
		System.out.println("************************************************************************************");
		System.out.println("|  ENCOUNTER BUILDER v1.0  |");
		System.out.print("\n");
		System.out.println("[1] SET DIFFICULTY RATING");
		System.out.println("[2] SELECT LOCATION");
		System.out.println("[3] GENERATE RANDOM ENCOUNTER");
		System.out.println("[4] VIEW / START ENCOUNTER");
		System.out.println("[5] QUIT TO MAIN MENU");
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printEncounterBuilderMenuLight() {
		
		System.out.print("\n");
		System.out.println("----------------------------");
		System.out.println("|  ENCOUNTER BUILDER v1.0  |");
		System.out.println("----------------------------");
		System.out.println("[1] SET DIFFICULTY RATING");
		System.out.println("[2] SELECT LOCATION");
		System.out.println("[3] GENERATE RANDOM ENCOUNTER");
		System.out.println("[4] VIEW / START ENCOUNTER");
		System.out.println("[5] QUIT TO MAIN MENU");
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printSetDifficulty() {
		
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("The difficulty setting will determine how many mobs spawn and the power level of those mobs.");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("'1' is the easiest setting, while '5' is the most difficult.");
		System.out.print("\n");
		System.out.println("Please enter 1-5 to set the difficulty");
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printInvalidDifficultySelection() {
		
		System.out.print("\n");
		System.out.println("Invalid difficulty setting. Please try again.");
		System.out.print("\n");
	}
	
	public void printSelectLocationMenu() {
		
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("The location will determine the types of mobs that spawn and will also help paint a mental picture!");
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("[1] ANCIENT JUNGLE RUINS");
		System.out.println("[2] CAPITAL CITY CRYPT");
		System.out.println("[3] THE TWISTED WOOD");
		System.out.println("[4] MISTY MARSH");
		System.out.println("[5] THE MOLTEN CHASM");
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printEncounterReadyPrompt() {
		
		System.out.print("\n");
		System.out.println("------------------------");
		System.out.println("YOUR ENCOUNTER AWAITS...");
		System.out.println("------------------------");
		System.out.println("[1] START ENCOUNTER");
		System.out.println("[2] VIEW ENCOUNTER DETAILS");
		System.out.print("\n");
		System.out.print(">>> ");
	}
	
	public void printEncounterDetails(int difficulty, PlayerCharacter player, Map<Integer, Mob> mobs, Setting setting) {
		
		System.out.print("\n");
		System.out.println("---------------------------");
		System.out.println("|||  ENCOUNTER DETAILS  |||");
		System.out.println("---------------------------");
		System.out.println("Player: " + player.getName().toUpperCase() + " || " + player.getRace().getName() + " " + player.getCharClass().getName() + " || " + player.getWeapon().getName());
		System.out.print("\n");
		System.out.print("Mobs: ");
		
		Set<Integer> keys = mobs.keySet();
		for (Integer k : keys) {
			System.out.print(mobs.get(k).getName() + " || ");
		}
		System.out.print("\n");
		System.out.println("Location: " + setting.getName());
		System.out.print("\n");
		System.out.println("Difficulty: " + difficulty);
		System.out.print("\n");
		System.out.println("Press [1] to start Encounter or [2] to exit to Encounter Menu");
		System.out.print(">>> ");
		
	}
}