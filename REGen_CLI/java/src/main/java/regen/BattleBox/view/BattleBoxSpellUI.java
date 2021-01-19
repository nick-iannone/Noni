package regen.BattleBox.view;

import regen.domain.Mob;
import regen.domain.Spell;

public class BattleBoxSpellUI {

	public void printSpellHitAnimation(Spell spell) {
		
		if (spell.getSpellID() == 1) {
			System.out.println("   __/\\'\n" + 
					" ')    )_\n" + 
					"__)     (__'\n" + 
					"\\   _/\\   /\n" + 
					"')  )  ( (\n" + 
					"(  ( () ) ) HOT ENOUGH FOR YA?!");
		}
		else if (spell.getSpellID() == 2) {
			System.out.println("___\n" + 
					"\\  \\zzt\n" + 
					" \\  \\/\\\n" + 
					"  \\    \\\n" + 
					"   \\/\\  \\zzt\n" + 
					"      \\  \\/\\\n" + 
					"       \\    \\__\n" + 
					"     zzt\\/\\   / \n" + 
					"         * \\ /\n" + 
					"            V *SHOCKING*");
		}
		else if (spell.getSpellID() == 3) {
			System.out.println("         _____ _\n" + 
					"      __/ __ _\n" + 
					"   __/ __/    _\n" + 
					"__/   /     \n" + 
					"__ ---*WHOOSH* -\n" + 
					"  \\__ \\__  _\n" + 
					"     \\__ \\__   _\n" + 
					"	    \\____ __");
		}
		else if (spell.getSpellID() == 4) {
			System.out.println("             \\|/\n" + 
					"            .-*-         \n" + 
					"           / /|\\         \n" + 
					"          _L_            \n" + 
					"        ,\"   \".          \n" + 
					"    (\\ /  O O  \\ /)      \n" + 
					"     \\|    _    |/       \n" + 
					"       \\  (_)  /         \n" + 
					"       _/.___,\\_         \n" + 
					"      (_/      \\_)  ");
		}
		else if (spell.getSpellID() == 5) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 6) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 7) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 8) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 9) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 10) {
			System.out.println("");
		}
		else if (spell.getSpellID() == 11) {
			System.out.println("o()xxxx[{:::::::::::::>");
		}
		else if (spell.getSpellID() == 12) {
			System.out.print("\n");
			System.out.println("An EXPLOSION of arcane energy hits each mob!");
			System.out.print("\n");
			System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&  (-(-_-(-_(-_(-_-)_-)-_-)_-)_-)-)");
			System.out.print("\n");
		}
		else if (spell.getSpellID() == 13) {
			System.out.print("\n");
			System.out.print("\n");
			System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&  (-(-_-(-_(-_(-_-)_-)-_-)_-)_-)-)");
			System.out.print("\n");
		}
		else if (spell.getSpellID() == 14) {
			System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&");
			System.out.println("Do you even rage, bro?");
			System.out.print("\n");
		}
	}
	
	public void printSpellDamage(Spell spell, Mob mob, boolean isCrit, int damage) {
		
		printSpellHitAnimation(spell);
		System.out.print("\n");
		if (isCrit == true) {
			System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&");
			System.out.println("   ___ ___ ___ _____ ___ ___   _   _      _  _ ___ _____ \n" + 
							   "  / __| _ \\_ _|_   _|_ _/ __| /_\\ | |    | || |_ _|_   _|\n" + 
							   " | (__|   /| |  | |  | | (__ / _ \\| |__  | __ || |  | |  \n" + 
							   "  \\___|_|_\\___| |_| |___\\___/_/ \\_\\____| |_||_|___| |_|  \n" + 
							   "                                                         ");
		}
		System.out.println("@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&@#$&");
		System.out.print("\n");
		System.out.println("You hit " + mob.getName() + " for " + damage + " damage!");
		System.out.println("HP remaining: " + mob.getHP());
		System.out.print("\n");
	}
}
