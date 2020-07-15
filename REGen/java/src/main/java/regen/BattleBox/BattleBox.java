package regen.BattleBox;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import regen.BattleBox.domain.Minion;
import regen.BattleBox.view.BattleBoxUI;
import regen.domain.Mob;
import regen.domain.PlayerCharacter;
import regen.domain.Spell;
import regen.main.EncounterBuilder;
import regen.view.SoundEffect;

public class BattleBox {
	
	private BattleBoxUI bbui;
	private Dice dice;
	private EncounterBuilder encounter;
	private int branchIndex;
	private String selection;
	private Scanner scanner;
	private Mob singleTarget;
	private Queue<Mob> queueMob;
	private List<Minion> minionList;
	private Queue<Minion> minions;
	private Spell spellToCast;
	private SoundEffect sound;
	
	
	public BattleBox(EncounterBuilder encounter) {
		this.encounter = encounter;
		this.branchIndex = 6;
		this.dice = new Dice();
		this.bbui = new BattleBoxUI();
		this.scanner = new Scanner(System.in);
		this.spellToCast = new Spell();
		this.queueMob = new LinkedList<Mob>();
		this.minionList = new ArrayList<Minion>();
		this.minions = new LinkedList<Minion>();
		this.sound = new SoundEffect();
	}

	//______________________________________________________________________________________________________________________________________	
	public void playerTurn() {
		
		while (branchIndex == 6) {
			
			if (evaluateVictory() == true) {
				bbui.printVictory();					
				sound.play("regenVictoryCheer");
				String response = scanner.nextLine();
				branchIndex = 0;
				continue;
			}	
			
			sound.play("COMBAT");
			bbui.printBattleStart(encounter.getPlayer());
			selection = scanner.nextLine();
			sound.play("MenuSelect");
			
			if (selection.equals("1")) { 											//WEAPON ATTACK BRANCH
			
				bbui.printSelectAttackTarget(encounter.getMobs());
				selection = scanner.nextLine();
				sound.play("MenuSelect");
				
				int selectionAsInt = 0;
				try { 
					selectionAsInt = Integer.parseInt(selection);
				} catch (NumberFormatException e){System.out.println("Invalid Number Input");}
				
				Set<Integer> keys = encounter.getMobs().keySet();
				for (Integer k : keys) {
					
					if (selectionAsInt == k) {
						singleTarget = encounter.getMobs().get(k);
					}
					else if (singleTarget == null) {
						singleTarget = encounter.getMobs().get(k);
					}
				}
				makeWeaponAttack();
				endPlayerTurn();
				continue;
			}
			
			else if (selection.equals("2")) { 										//SPELL CASTING BRANCH
				
				bbui.printAvailableSpells(encounter.getPlayer());
				selection = scanner.nextLine();
				sound.play("MenuSelect");
				
				int selectionAsInt = 0;
				
				try {
					selectionAsInt = Integer.parseInt(selection);
				} catch (NumberFormatException e) {System.out.println("Invalid Number Input");}
					
				for (Spell s : encounter.getPlayer().getCharClass().getSpellList()) {
					if (selectionAsInt == s.getSpellID()) {
						spellToCast = s;
					}
				}
				selectSpellTargets();	
				endPlayerTurn();
				continue;
			}
					
			else if (selection.equals("3")) { 											//EXIT BRANCH
				bbui.printExitVerification();
				selection = scanner.nextLine();
				sound.play("MenuSelect");
				if (selection.equalsIgnoreCase("Y")) {
					branchIndex = 0;
					continue;
				}
			}
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void minionTurn() {
		
		setQueueMinions();
		
		while (branchIndex == 7) {
		
			if (minions.isEmpty()) {
				branchIndex = 8;
				mobTurn();
				continue;
			}
			else {
				Minion active = minions.poll();	
				bbui.printMinionTurnStart(active);
				String resume = scanner.nextLine();
				
				if (singleTarget.getHP() > 0) { 
					makeMinionAttack(active);
				}
		        else {
		        	Set<Integer> keys = encounter.getMobs().keySet();
		        	for (Integer k : keys) {
		        		if (encounter.getMobs().get(k).getHP() > 0) {
		        			singleTarget = encounter.getMobs().get(k);
		        		}
		        	}
		        	makeMinionAttack(active);
				}
			}
			if (evaluateVictory() == true) {
				bbui.printVictory();
				sound.play("regenVictoryCheer");
				String response = scanner.nextLine();
				branchIndex = 0;
				continue;
			}
		}
	}
	//______________________________________________________________________________________________________________________________________	
	public void mobTurn() {
		
		setQueueMobs();
		
		while (branchIndex == 8) {
			
			if (queueMob.isEmpty()) {
				branchIndex = 6;
				continue;
			}
			
			Mob activeMob = queueMob.poll();
			
			if (activeMob.getHP() > 0) {
				bbui.printMobTurnStart(activeMob);
				String resume = scanner.nextLine();
			
				if (activeMob.isTurned() == true) {
					
					Set<Integer> keys = encounter.getMobs().keySet();
					for (Integer k : keys) {
						if (k != activeMob.getMobID() && encounter.getMobs().get(k).getHP() > 0) {
							singleTarget = encounter.getMobs().get(k);
						}
					}
					makeMobWeaponAttack(activeMob);
					activeMob.setTurned(false);
				}
				else if (activeMob.getModSpell() > activeMob.getModAttack()){
					castSpellMob(encounter.getPlayer(), activeMob);
					if (evaluateDefeat() == true) {
						bbui.printDefeat();
						sound.play("GASP");
						String response = scanner.nextLine();
						branchIndex = 0;
						continue;
					}	
				}
				else {
					makeMobWeaponAttack(activeMob);
					if (evaluateDefeat() == true) {
						bbui.printDefeat();
						sound.play("GASP");
						String response = scanner.nextLine();
						branchIndex = 0;
						continue;
					}	
				}
			}
		}
	}
	//______________________________________________________________________________________________________________________________________	
	public void makeWeaponAttack() {
		
		int attackRoll = dice.roll20();
	    int critBonus = 0;
	    boolean isCrit = false;
	    
		if (attackRoll == 20) {
			critBonus += encounter.getPlayer().getWeapon().getDamageDie();
			isCrit = true;
		}
		attackRoll += (encounter.getPlayer().getModAttack()/2);
		
		if (attackRoll >= singleTarget.getAC()) {
			
			sound.play("MetalStrike");
			bbui.printAttackHit(singleTarget);
			String resume = scanner.nextLine();
			sound.play("Hit2");
			int damageRollAttack = critBonus + damageRollAttack() + encounter.getPlayer().getModAttack();
			singleTarget.setHP(singleTarget.getHP() - damageRollAttack);
			if (singleTarget.getHP() < 0) {
				singleTarget.setHP(0);
			}
			bbui.printAttackDamage(singleTarget, damageRollAttack, isCrit);
							
		}
		else {
			bbui.printMiss();
			sound.play("Whiff");
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void makeMobWeaponAttack(Mob mob) {
		
		int attackRoll = dice.roll20();
	    int critBonus = 0;
	    boolean isCrit = false;
	    
	    if (attackRoll == 20) {
			critBonus += mob.getWeapon().getDamageDie();
			isCrit = true;
		}
		attackRoll += (mob.getModAttack()/2);
		
	    if (mob.isTurned() == true) {
			
	    	if (attackRoll >= singleTarget.getAC()) {
				sound.play("MetalStrike");
	    		bbui.printAttackHitMobFriendly(mob, singleTarget);
	    		String resume = scanner.nextLine();
				sound.play("Hit2");
	    		int damageRollAttack = critBonus + attackRollMob(mob) + ((mob.getModAttack() / 2));
	    		singleTarget.setHP(singleTarget.getHP() - damageRollAttack);
	    		if (singleTarget.getHP() < 0) {
	    			singleTarget.setHP(0);
	    		}
	    		bbui.printAttackDamageMob(mob, singleTarget, encounter.getPlayer(), damageRollAttack, isCrit);
	    	}
	    	else {
	    		bbui.printMiss();
				sound.play("Whiff");
	    	}
		}
	    else if (attackRoll >= encounter.getPlayer().getAC()) {
			sound.play("MetalStrike");
			int rand = ThreadLocalRandom.current().nextInt(1, 11);
			
			if (rand > 4) {
				
				bbui.printAttackHitMob(mob, encounter.getPlayer());
				String resume = scanner.nextLine();
				sound.play("Hit2");
				int damageRollAttack = critBonus + attackRollMob(mob) + ((mob.getModAttack() / 2));
	    		encounter.getPlayer().setHP(encounter.getPlayer().getHP() - damageRollAttack);
	    		bbui.printAttackDamageMob(mob, singleTarget, encounter.getPlayer(), damageRollAttack, isCrit);	
			}
			else if (minionList.size() > 0) {
				
				int minionTotalHP = 0;
				
				for (Minion m : minionList) {
					minionTotalHP += m.getHP();
				}
				if (minionTotalHP == 0) {
					
					bbui.printAttackHitMob(mob, encounter.getPlayer());
					String resume = scanner.nextLine();
					sound.play("Hit2");
					int damageRollAttack = critBonus + attackRollMob(mob) + ((mob.getModAttack() / 2));
		    		encounter.getPlayer().setHP(encounter.getPlayer().getHP() - damageRollAttack);
		    		bbui.printAttackDamageMob(mob, singleTarget, encounter.getPlayer(), damageRollAttack, isCrit);	
				}
				else {
					Minion target = minionList.get(0);
					for (Minion m : minionList) {
						if (m.getHP() > 0) {
							target = m;
						}
					}
					bbui.printAttackHitMobMinion(mob, target);
					String resume = scanner.nextLine();
					sound.play("Hit2");
					int damageRollAttack = critBonus + attackRollMob(mob) + ((mob.getModAttack() / 2));
		    		target.setHP(target.getHP() - damageRollAttack);
		    		if (target.getHP() < 0) {
		    			target.setHP(0);
		    		}
		    		bbui.printAttackDamageMobMinion(mob, target, damageRollAttack, isCrit);
				}
			}			
		}
		else {
			bbui.printMiss();
			sound.play("Whiff");
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void makeMinionAttack(Minion minion) {
		
		int attackRoll = dice.roll20();
	    int critBonus = 0;
	    boolean isCrit = false;
	    
		if (attackRoll == 20) {
			critBonus += minion.getDamageDie();
			isCrit = true;
		}
		
		attackRoll += (minion.getModAttack() / 2);
		
		if (attackRoll >= singleTarget.getAC()) {
			sound.play("MetalStrike");
			bbui.printAttackHitMinion(singleTarget, minion);//HERE
			String resume = scanner.nextLine();
			sound.play("Hit2");
			int damageRollAttack = critBonus + attackRollMinion(minion) + (minion.getModAttack() / 2); //HERE
			singleTarget.setHP(singleTarget.getHP() - damageRollAttack);
			if (singleTarget.getHP() < 0) {
				singleTarget.setHP(0);
			}
			bbui.printAttackDamageMinion(minion, singleTarget, damageRollAttack, isCrit);		//HERE	
		}
		else {
			bbui.printMiss();
			sound.play("Whiff");
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void selectSpellTargets() {
		
		bbui.printSelectSpellTargets(encounter.getMobs());
		selection = scanner.nextLine();
		sound.play("MenuSelect");
		
		if (selection.equalsIgnoreCase("A")) {
			
			if (spellToCast.getTarget().equals("multiple targets")) {
				castSpell();
			}
			else {
				bbui.printSpellTargetError();
			}	
		}
		else {	
			int selectionAsInt = 0;
			try {
			selectionAsInt = Integer.parseInt(selection);
			} catch (NumberFormatException e) {System.out.println("Invalid Selection");}
			Set<Integer> keys = encounter.getMobs().keySet();
			for (Integer k : keys) {
				if (selectionAsInt == encounter.getMobs().get(k).getMobID()) {
					singleTarget = encounter.getMobs().get(k);
				}
				else if (selection == null) {
					singleTarget = encounter.getMobs().get(k);
				}
			}
			castSpell();
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void castSpell() {
		
		int spellAttack = dice.roll20();
		int critBonus = 0;
		boolean isCrit = false;
		
		if (spellAttack == 20) {
			critBonus += spellToCast.getDamageDie();
			isCrit = true;
		}
		spellAttack += (encounter.getPlayer().getModSpell() / 2);
		
		if (selection.equalsIgnoreCase("A") && spellToCast.getSpellID() != 4) { 
			//multiple targets
			Set<Integer> keys = encounter.getMobs().keySet();
			for (Integer k : keys) {
				encounter.getMobs().get(k).setHP(encounter.getMobs().get(k).getHP() - (damageRollSpell() + encounter.getPlayer().getModSpell()));
				if (encounter.getMobs().get(k).getHP() < 0) {
					encounter.getMobs().get(k).setHP(0);
				}
				encounter.getMobs().get(k).setAC(encounter.getMobs().get(k).getAC() + spellToCast.getModAC());
				encounter.getMobs().get(k).setModAttack(encounter.getMobs().get(k).getModAttack() + spellToCast.getModAttack());
				encounter.getMobs().get(k).setModSpell(encounter.getMobs().get(k).getModSpell() + spellToCast.getModSpell());
			}
			bbui.printMultiTargetSpell(spellToCast);
			sound.play("SpellWhoosh");
		}
		else if (selection.equals("0")) {	  		 				
			
			encounter.getPlayer().setAC(encounter.getPlayer().getAC() + spellToCast.getModAC());
			if (encounter.getPlayer().getAC() > 20) {
				encounter.getPlayer().setAC(20);
			}
			encounter.getPlayer().setHP(encounter.getPlayer().getHP() + spellToCast.getModHP());
			if (encounter.getPlayer().getHP() > 70) {
				encounter.getPlayer().setHP(70);
			}
			encounter.getPlayer().setModAttack(encounter.getPlayer().getModAttack() + spellToCast.getModAttack());
			encounter.getPlayer().setModSpell(encounter.getPlayer().getModSpell() + spellToCast.getModSpell());
			
			if (spellToCast.getSpellID() == 5) {													//healing
				bbui.printHeal(spellToCast);
				sound.play("Heal");
			}
			else if (spellToCast.getSpellID() == 8) {												//wild armor
				bbui.printArmor(spellToCast);
				sound.play("WildArmor");
			}
			else if (spellToCast.getSpellID() == 14) {
				bbui.printRage(spellToCast);
				sound.play("MonsterSound");
			}
			else if (spellToCast.getSpellID() == 15) {
				int minionHP = 0;
				for (int i = 0; i < minionList.size(); i++) {
					if (minionList.get(i).getHP() > minionHP) {
						minionHP = minionList.get(i).getHP();
					}
				}
				encounter.getPlayer().setHP(encounter.getPlayer().getHP() + minionHP);
				minionList.remove(0);
			}
		}
		
		else if (spellToCast.getSpellID() == 6) {     												//summon minions
			
			for (int i = 0; i < minionList.size(); i++) {
				if (minionList.get(i).getHP() <= 0) {
					minionList.remove(i);
					i--;
				}
			}
			int numberOfMinions = dice.rollMinions();
			String name = "Minion ";
			int count = minionList.size() + 1;
			int spawns = minionList.size();
			
			for (int i = 0; i < numberOfMinions; i++) {
				
				if (minionList.size() < 3) {
				minionList.add(makeMinion(name + count));
				}
				count++;
			}
			spawns = minionList.size() - spawns;
			bbui.printSummonMinions(spawns, spellToCast);
			sound.play("MonsterSound");
		}
		
		else if (spellToCast.getSpellID() == 10) {													//turn mob
			bbui.printTurnMob(spellToCast, singleTarget);
			sound.play("WarpPop");
			singleTarget.setTurned(true);
		}
		
		else if (spellToCast.getSpellID() == 4) {												//explode minion
			
			for (int i = 0; i < minionList.size(); i++) {
				if (minionList.get(i).getHP() <= 0) {
					minionList.remove(i);
					i--;
				}
			}
			if (minionList.size() > 0) {
				int rand = ThreadLocalRandom.current().nextInt(0, minionList.size());
				int damage = (minionList.get(rand).getHP() / 2);
				minionList.remove(rand);
				
				Set<Integer> keys = encounter.getMobs().keySet();
				for (Integer k : keys) {
					encounter.getMobs().get(k).setHP(encounter.getMobs().get(k).getHP() - damage);
				}
				bbui.printExplodeMinion(damage, spellToCast);
				sound.play("SpellHitHard");
			}
			else {
				bbui.printMinionError();
				String response = scanner.nextLine();
			}
		}
		else if (spellToCast.getSpellID() == 11) {													//poison weapon
		
			spellAttack += encounter.getPlayer().getModAttack();
			
			if (spellAttack >= singleTarget.getAC()) {
				sound.play("MetalStrike");
				int damageRollSpell = damageRollAttack() + damageRollSpell() + critBonus + encounter.getPlayer().getWeapon().getDamageDie();
				singleTarget.setAC(singleTarget.getAC() + spellToCast.getModAC());
				singleTarget.setHP(singleTarget.getHP() - damageRollSpell);
				if (singleTarget.getHP() < 0) {
					singleTarget.setHP(0);
				}
				singleTarget.setModAttack(singleTarget.getModAttack() + spellToCast.getModAttack());
				singleTarget.setModSpell(singleTarget.getModSpell() + spellToCast.getModSpell());
				bbui.printSpellHit(singleTarget, spellToCast);
				String resume = scanner.nextLine();
				sound.play("SpellExplode");
				bbui.printSpellDamage(spellToCast, singleTarget, isCrit, damageRollSpell);
			}
			else {
				bbui.printMiss();
				sound.play("Whiff");
			}
		}
		
		else {     
																									//single target
			if (spellAttack >= singleTarget.getAC()) {
				sound.play("SpellWhoosh");
				int damageRollSpell = damageRollSpell() + critBonus;
				singleTarget.setAC(singleTarget.getAC() + spellToCast.getModAC());
				singleTarget.setHP(singleTarget.getHP() - damageRollSpell);
				if (singleTarget.getHP() < 0) {
					singleTarget.setHP(0);
				}
				singleTarget.setModAttack(singleTarget.getModAttack() + spellToCast.getModAttack());
				singleTarget.setModSpell(singleTarget.getModSpell() + spellToCast.getModSpell());
				bbui.printSpellHit(singleTarget, spellToCast);
				String resume = scanner.nextLine();
				sound.play("SpellHitHard");
				bbui.printSpellDamage(spellToCast, singleTarget, isCrit, damageRollSpell);
			}
			else {
				bbui.printMiss();
				sound.play("Whiff");
			}
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void castSpellMob(PlayerCharacter player, Mob mob) {
		
		if (mob.getCharClass().getClassID() == 3 || mob.getCharClass().getClassID() == 5) {
			
			for (Spell s : mob.getCharClass().getSpellList()) {
				if (s.getSpellID() == 1) {
					spellToCast = s;
				}
			}
		}
		else if (mob.getCharClass().getClassID() == 1 || mob.getCharClass().getClassID() == 4) {
			
			for (Spell s : mob.getCharClass().getSpellList()) {
				if (s.getSpellID() == 11) {
					spellToCast = s;
				}
			}
		}
		else {
			for (Spell s : mob.getCharClass().getSpellList()) {
				if (s.getSpellID() == 7) {
					spellToCast = s;
				}
			}
		}
		int spellAttack = dice.roll20();
		int critBonus = 0;
		boolean isCrit = false;
		
		if (spellAttack == 20) {
			critBonus += spellToCast.getDamageDie();
			isCrit = true;
		}
		spellAttack += (mob.getModSpell() / 2);
		
		if (spellAttack >= encounter.getPlayer().getAC()) {
			sound.play("SpellExplode");
			int damageRollSpell = damageRollSpell() + critBonus;
			encounter.getPlayer().setAC(encounter.getPlayer().getAC() + spellToCast.getModAC());
			encounter.getPlayer().setHP(encounter.getPlayer().getHP() - damageRollSpell);
			encounter.getPlayer().setModAttack(encounter.getPlayer().getModAttack() + spellToCast.getModAttack());
			encounter.getPlayer().setModSpell(encounter.getPlayer().getModSpell() + spellToCast.getModSpell());
			
			bbui.printSpellHitMob(mob, spellToCast);
			String resume = scanner.nextLine();
			sound.play("SpellExplode");
			bbui.printAttackDamageMob(mob, singleTarget, player, damageRollSpell, isCrit);
			bbui.printPressEnter();
			String resume1 = scanner.nextLine();
			sound.play("MenuSelect");
		}
		else {
			bbui.printMiss();
			sound.play("Whiff");
			bbui.printPressEnter();
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void endPlayerTurn() {
	
		if (evaluateVictory() == true) {
			bbui.printVictory();					
			sound.play("regenVictoryCheer");
			String response = scanner.nextLine();
			branchIndex = 0;
		}
		else if (minionList.isEmpty()) {
			branchIndex = 8;
			mobTurn();
		}
		else {
			branchIndex = 7;
			minionTurn();
		}
	}
	//______________________________________________________________________________________________________________________________________
	public int damageRollAttack() {
		
		if (encounter.getPlayer().getWeapon().getDamageDie() == 4) {
			return dice.roll4();
		}
		else if (encounter.getPlayer().getWeapon().getDamageDie() == 6) {
			return dice.roll6();
		}
		else if (encounter.getPlayer().getWeapon().getDamageDie() == 8) {
			return dice.roll8();
		}
		else if (encounter.getPlayer().getWeapon().getDamageDie() == 10) {
			return dice.roll10();
		}
		else if (encounter.getPlayer().getWeapon().getDamageDie() == 12) {
			return dice.roll12();
		}
		else if (encounter.getPlayer().getWeapon().getDamageDie() == 20) {
			return dice.roll20();
		}
		else {
			return 0;
		}
	}
	//______________________________________________________________________________________________________________________________________
	public int damageRollSpell() {
		
		if (spellToCast.getDamageDie() == 4) {
			return dice.roll4();
		}
		else if (spellToCast.getDamageDie() == 6) {
			return dice.roll6();
		}
		else if (spellToCast.getDamageDie() == 8) {
			return dice.roll8();
		}
		else if (spellToCast.getDamageDie() == 10) {
			return dice.roll10();
		}
		else if (spellToCast.getDamageDie() == 12) {
			return dice.roll12();
		}
		else if (spellToCast.getDamageDie() == 20) {
			return dice.roll20();
		}
		else {
			return 0;
		}
	}
	//______________________________________________________________________________________________________________________________________
	public int attackRollMinion(Minion minion) {

		if (minion.getDamageDie() == 4) {
			return dice.roll4();
		}
		else if (minion.getDamageDie() == 6) {
			return dice.roll6();
		}
		else if (minion.getDamageDie() == 8) {
			return dice.roll8();
		}
		else if (minion.getDamageDie() == 10) {
			return dice.roll10();
		}
		else if (minion.getDamageDie() == 12) {
			return dice.roll12();
		}
		else if (minion.getDamageDie() == 20) {
			return dice.roll20();
		}
		else {
			return 0;
		}
	}
	//______________________________________________________________________________________________________________________________________
	public int attackRollMob(Mob mob) {

		if (mob.getWeapon().getDamageDie() == 4) {
			return dice.roll4();
		}
		else if (mob.getWeapon().getDamageDie() == 6) {
			return dice.roll6();
		}
		else if (mob.getWeapon().getDamageDie() == 8) {
			return dice.roll8();
		}
		else if (mob.getWeapon().getDamageDie() == 10) {
			return dice.roll10();
		}
		else if (mob.getWeapon().getDamageDie() == 12) {
			return dice.roll12();
		}
		else if (mob.getWeapon().getDamageDie() == 20) {
			return dice.roll20();
		}
		else {
			return 0;
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void setQueueMobs() {
		
		Set<Integer> keys = encounter.getMobs().keySet();
		for (Integer k : keys) {
			if (encounter.getMobs().get(k).getHP() > 0) {
				queueMob.add(encounter.getMobs().get(k));
			}
		}
	}
	//______________________________________________________________________________________________________________________________________
	public void setQueueMinions() {
		
		for (Minion m : minionList) {
			if (m.getHP() > 0) {
				minions.add(m);
			}
		}
	}
	//______________________________________________________________________________________________________________________________________
	public Minion makeMinion(String name) {
		
		Minion minion = new Minion(name);
	
		return minion;
	}
	//______________________________________________________________________________________________________________________________________
	public boolean evaluateVictory() {
		
		boolean isVictorious = false;
		int mobTotalHP = 0;
		
		Set<Integer> keys = encounter.getMobs().keySet();
		for (Integer k : keys) {
			mobTotalHP += encounter.getMobs().get(k).getHP();
		}
		
		if (mobTotalHP == 0) {
			isVictorious = true;
		}
		return isVictorious;
	}
	//______________________________________________________________________________________________________________________________________
	public boolean evaluateDefeat() {
		
		boolean isDefeated = false;
		
		if (encounter.getPlayer().getHP() <= 0) {
			isDefeated = true;
		}
		return isDefeated;
	}
	//______________________________________________________________________________________________________________________________________
	public String getSelection() {
		return selection;
	}

}
