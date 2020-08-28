package regen.BattleBox.view;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import regen.BattleBox.domain.Minion;
import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Spell;
import regen.view.SoundEffect;

public class BattleBoxUI {
	
	private String selection;
	private BattleBoxSpellUI spellUI;
	private SoundEffect sound;
	
	public BattleBoxUI() {
		this.spellUI = new BattleBoxSpellUI();
		this.sound = new SoundEffect();
	}
	
	public void printBattleStart(PlayerCharacter player) {
		
		System.out.println("      ________________________________________\n" + 
				"     /  ___________________   ___________ __ /\n" + 
				"    /  / _____/ / ____  / /  / /___  ___// //\n" + 
				"   /  / /__  / / /   /_/ /__/ /   / /   / //\n" + 
				"  /  / ___/ / / / ____/ ___  /   / /   /_//\n" + 
				" /  / /    / / /_/__ / /  / /   / /   __ /\n" + 
				"/  /_/    /_/_______/_/  /_/   /_/   /_//\n" + 
				"---------------------------------------/\n" + 
				"-------------------------------------------");
		System.out.println("It is your turn. What would you like to do?");
		System.out.println("-------------------------------------------");
		System.out.println("[1] Weapon Attack");
		System.out.println("[2] Cast Spell");
		System.out.println("[3] Run Away! (exit encounter)");
		System.out.println("-------------------------------------------");
		System.out.println("PLAYER HP: " + player.getHP() + " HP remaining");
		System.out.println("PLAYER AC: " + player.getAC() + " Armor Class");
		System.out.println("PLAYER MOD ATTACK: " + player.getModAttack());
		System.out.println("PLAYER MOD SPELL: " + player.getModSpell());
	}
	
	public void printSelectAttackTarget(Map<Integer, Mob> mobs) {
		System.out.println("--------------------------------------------");
		System.out.println("Who are you attacking? Enter the [ID] below.");
		System.out.println("--------------------------------------------");
		Set<Integer> keys = mobs.keySet();
		for (Integer k : keys) {
			System.out.println("[" + mobs.get(k).getMobID() + "] " + mobs.get(k).getName() + " || " + mobs.get(k).getHP() + " HP remaining");
			System.out.print("\n");
		}
		System.out.print(">>> ");
	}
	
	public void printAvailableSpells(PlayerCharacter player) {
		
		System.out.println("---------------------------------------------------------------");
		System.out.println("You have these spells to choose from. Enter the spell [ID] below.");
		System.out.println("---------------------------------------------------------------");
		for (Spell s : player.getCharClass().getSpellList()) {
			System.out.println("[" + s.getSpellID() + "] " + s.getName().toUpperCase());
			
			System.out.println(s.getDescription());
			System.out.println("---------------------------------------------------------------");
		}
		System.out.print(">>> ");
	}
	
	public void printSelectSpellTargets(Map<Integer, Mob> mobs) {
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		System.out.println("Who are you targeting? Enter the [ID] below for single targets, enter [A] for multi-target spells, or enter [0] for self.");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		Set<Integer> keys = mobs.keySet();
		for (Integer k: keys) {
			if (mobs.get(k).getHP() >= 0) {
				System.out.println("[" + mobs.get(k).getMobID() + "] " + mobs.get(k).getName() + " || " + mobs.get(k).getHP() + " HP remaining");
				System.out.print("\n");
			}
		}
		System.out.print(">>> ");
	}
	
	public void printAttackHit(Mob target) {
		
		System.out.println("\n");
		System.out.println("GOT 'EM!");
		System.out.println("Your attack hit " + target.getName());
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to deal damage!");
		System.out.println(":::::::::::::::::::::::::::::");
		
	}
	
	public void printAttackHitMinion(Mob mob, Minion minion) {
		
		System.out.println("\n");
		System.out.println("GOT 'EM!");
		System.out.println(minion.getName() + "'s attack hit " + mob.getName());
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to deal damage!");
		System.out.println(":::::::::::::::::::::::::::::");
	}
	
	public void printAttackHitMob(Mob mob, PlayerCharacter player) {
		
		System.out.println("@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&");
		System.out.println("OUCH!");
		System.out.println("-----------------------------------");
		System.out.println(mob.getName() + " hit " + player.getName() + " with its " + mob.getWeapon().getName() + "!");
		System.out.print("\n");
		System.out.println("::::::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to see the damage!");
		System.out.println("::::::::::::::::::::::::::::::::");
		
	}
	
	public void printAttackHitMobMinion(Mob mob, Minion minion) {
		
		System.out.println("@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&");
		System.out.println("~~~~~~~~~~~~~~ OUCH! ~~~~~~~~~~~~~~");
		System.out.println("-----------------------------------");
		System.out.println(mob.getName() + " hit " + minion.getName() + " with its " + mob.getWeapon().getName() + "!");
		System.out.print("\n");
		System.out.println("::::::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to see the damage!");
		System.out.println("::::::::::::::::::::::::::::::::");
		
	}
	public void printAttackHitMobFriendly(Mob mob, Mob singleTarget) {
		
		System.out.println("           FRIENDLY FIRE!          ");
		System.out.println("\n");
		System.out.println("@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&@#$%&");
		System.out.println(mob.getName() + " hit " + singleTarget.getName() + "!");
		System.out.print("\n");
		System.out.println("::::::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to see the damage!");
		System.out.println("::::::::::::::::::::::::::::::::");
	}
	
	public void printAttackDamage(Mob target, int damageRoll, boolean crit) {
		
		if (crit == true) {
			System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&");
			System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
							   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
							   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
							   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
							   "                                                         ");
			sound.play("SpellHitHard2");
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("()xxxx[{::::::::> *CLANG*");
		System.out.println("OH, THAT'S GOTTA HURT!");
		System.out.print("\n");
		System.out.println("You hit " + target.getName() + " for " + damageRoll + " damage!");
		System.out.println("HP remaining: " + target.getHP());
		System.out.print("\n");
	}
	
	public void printAttackDamageMinion(Minion minion, Mob mob, int damageRoll, boolean crit) {
		
		if (crit == true) {
			System.out.println("~@#%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@$%~@");
			System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
							   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
							   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
							   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
							   "                                                         ");
			sound.play("SpellHitHard2");
		}
		System.out.println("\n");
		System.out.println("*CLANG*");
		System.out.println("OH, THAT'S GOTTA HURT!");
		System.out.print("\n");
		System.out.println(minion.getName() + " hit " + mob.getName() + " for " + damageRoll + " damage!");
		System.out.println("HP remaining: " + mob.getHP());
		System.out.print("\n");
	}
	
	public void printAttackDamageMob(Mob mob, Mob singleTarget, PlayerCharacter player, int damage, boolean isCrit) {
		
		if (mob.isTurned() == true) {
			if (isCrit == true) {
				System.out.println("\n");
				System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
								   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
								   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
								   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
								   "                                                         ");
				sound.play("SpellHitHard2");
			}
			System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
			System.out.println("()xxxx[{::::::::>");
			System.out.println("WHAT ARE FRIENDS FOR?");
			System.out.print("\n");
			System.out.println(mob.getName() + " hit " + singleTarget.getName() + " for " + damage + " damage!");
			System.out.println("HP remaining: " + singleTarget.getHP());
			System.out.print("\n");
		}
		else {
			if (isCrit == true) {
				System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
				System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
								   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
								   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
								   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
								   "                                                         ");
				sound.play("SpellHitHard2");
			}
			System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
			System.out.println("()xxxx[{::::::::>");
			System.out.println("THAT HURT, CHARLIE!");
			System.out.print("\n");
			System.out.println(mob.getName() + " hit " + player.getName() + " for " + damage + " damage!");
			System.out.println("Player HP remaining: " + player.getHP());
			System.out.print("\n");
		}
	}
	
	public void printAttackDamageMobMinion(Mob mob, Minion minion, int damage, boolean isCrit) {
		if (isCrit == true) {
			System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
			System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
							   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
							   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
							   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
							   "                                                         ");
			sound.play("SpellHitHard2");
		}
		System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
		System.out.println("()xxxx[{::::::::>");
		System.out.println("THAT HURT, CHARLIE!");
		System.out.print("\n");
		System.out.println(mob.getName() + " hit " + minion.getName() + " for " + damage + " damage!");
		System.out.println("Minion HP remaining: " + minion.getHP());
		System.out.print("\n");
	}
	
	public void printMiss() {
		
		System.out.println("---------------------------------------------------------------");
		System.out.println("¯\\(°_o)/¯");
		System.out.println("That... is a miss!");
		System.out.print("\n");
	}
	
	public void printSpellTargetError() {
		
		System.out.println("This spell cannot target multiple enemies. Please try again.");
	}
	
	public void printSpellHit(Mob mob, Spell spell) {
		
		spellUI.printSpellHitAnimation(spell);
		System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
		System.out.println("GOT 'EM!");
		System.out.println("Your spell hit " + mob.getName());
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to deal damage!");
		System.out.println(":::::::::::::::::::::::::::::");
	}
	
	public void printSpellHitMob(Mob mob, Spell spell) {
		
		spellUI.printSpellHitAnimation(spell);
		System.out.println("@#$%@#$%@#$%@#$%@#$%@#$%@#$%@#%$@#%$@#%$@#%$@#$@#%@$#%@$#%@$#%@$#%@$#%@$#");
		System.out.println("GOTCHA!");
		System.out.println(mob.getName() + " hit you with " + spell.getName());
		System.out.print("\n");
		System.out.println("::::::::::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to see the damage!");
		System.out.println("::::::::::::::::::::::::::::::::");
	}
	
	public void printHeal(Spell spell) {
		spellUI.printSpellHitAnimation(spell);
	}
	
	public void printArmor(Spell spell) {
		spellUI.printSpellHitAnimation(spell);
	}
	
	public void printMultiTargetSpell(Spell spell) {
		spellUI.printSpellHitAnimation(spell);
	}
	
	public void printSummonMinions(int spawns, Spell spell) {
		
		spellUI.printSpellHitAnimation(spell);
		if (spawns == 1) {
			System.out.println("You summoned " + spawns + " minion!");
		}
		else {
			System.out.println("You summoned " + spawns + " minions!");
		}
		System.out.print("\n");
	}
	
	public void printTurnMob(Spell spell, Mob mob) {
		
		spellUI.printSpellHitAnimation(spell);
		System.out.println("You turned " + mob.getName() + " against its allies!");
		System.out.println("It will attack a mob next turn instead of you!");
	}
	
	public void printRage(Spell spell) {
		
		spellUI.printSpellHitAnimation(spell);
		System.out.println("You go into a blood frenzy! Your weapon attacks will hit harder!");
		System.out.print("\n");
	}
	
	public void printExplodeMinion(int damage, Spell spell) {
		
		spellUI.printSpellHitAnimation(spell);
		System.out.println("And BOOM goes the... minion!");
		System.out.println("The explosion hits each mob for " + damage + " damage!");
		System.out.print("\n");
	}
	
	public void printSpellDamage(Spell spell, Mob mob, boolean isCrit, int damage) {
		spellUI.printSpellDamage(spell, mob, isCrit, damage);
	}
	
	public void printSpellAnimation(Spell spell) {
		spellUI.printSpellHitAnimation(spell);
	}
	
	public void printMobTurnStart(Mob mob) {
		
		System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
		System.out.println("\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/");
		System.out.print("\n");
		System.out.println("INCOMING! It's " + mob.getName() + "'s turn.");
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
		
	}
	
	public void printMinionTurnStart(Minion minion) {
		
		System.out.print("\n");
		System.out.println("It's " + minion.getName() + "'s turn.");
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
	}
	
	public void printExitVerification() {
		
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Your encounter will end and you will be returned to the Main Menu...");
		System.out.println("Are you sure you want to exit?");
		System.out.println("[Y] for yes");
		System.out.println("[N] for no");
		System.out.print(">>> ");
	}
	public void printPressEnter() {
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
	}
	
	public void printVictory() {
		
		System.out.print("\n");
		System.out.println(" /$$    /$$ /$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$  /$$$$$$$  /$$     /$$ /$$\n" + 
				"| $$   | $$|_  $$_/ /$$__  $$|__  $$__//$$__  $$| $$__  $$|  $$   /$$/| $$\n" + 
				"| $$   | $$  | $$  | $$  \\__/   | $$  | $$  \\ $$| $$  \\ $$ \\  $$ /$$/ | $$\n" + 
				"|  $$ / $$/  | $$  | $$         | $$  | $$  | $$| $$$$$$$/  \\  $$$$/  | $$\n" + 
				" \\  $$ $$/   | $$  | $$         | $$  | $$  | $$| $$__  $$   \\  $$/   |__/\n" + 
				"  \\  $$$/    | $$  | $$    $$   | $$  | $$  | $$| $$  \\ $$    | $$        \n" + 
				"   \\  $/    /$$$$$$|  $$$$$$/   | $$  |  $$$$$$/| $$  | $$    | $$     /$$\n" + 
				"    \\_/    |______/ \\______/    |__/   \\______/ |__/  |__/    |__/    |__/\n" + 
				"                                                                          ");
		System.out.print("\n");
		System.out.println("CONGRATULATIONS! You are VICTORIOUS!");
		System.out.print("\n");
		System.out.println("YOU ROCK!");
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
	}
	public void printDefeat() {
		System.out.print("\n");
		System.out.println(" ______   _______  _______  _______  _______ _________ _ \n" + 
				"(  __  \\ (  ____ \\(  ____ \\(  ____ \\(  ___  )\\__   __/( )\n" + 
				"| (  \\  )| (    \\/| (    \\/| (    \\/| (   ) |   ) (   | |\n" + 
				"| |   ) || (__    | (__    | (__    | (___) |   | |   | |\n" + 
				"| |   | ||  __)   |  __)   |  __)   |  ___  |   | |   | |\n" + 
				"| |   ) || (      | (      | (      | (   ) |   | |   (_)\n" + 
				"| (__/  )| (____/\\| )      | (____/\\| )   ( |   | |    _ \n" + 
				"(______/ (_______/|/       (_______/|/     \\|   )_(   (_)\n" + 
				"                                                         ");
		System.out.println("OUR HERO HAS BEEN DEFEATED!");
		System.out.print("\n");
		System.out.println("BETTER LUCK NEXT TIME!");
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
	}
	public void printMinionError() {
		System.out.print("\n");
		System.out.println("Whoops! You don't have any minions!");
		System.out.print("\n");
		System.out.println(":::::::::::::::::::::::::");
		System.out.println("Press [ENTER] to continue");
		System.out.println(":::::::::::::::::::::::::");
	}
	
	
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
}
