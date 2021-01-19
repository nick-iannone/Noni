package regen.main;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;  
import org.apache.commons.dbcp2.BasicDataSource;

import regen.BattleBox.BattleBox;
import regen.domain.PlayerCharacter;
import regen.view.SoundEffect;
import regen.view.UIassist;


public class RandomEncounterGeneratorCLI {

	private UIassist uiAssist;
	private TrafficCop cop;
	private CharacterCreator charCreate;
	private EncounterBuilder buildEnc;
	private BattleBox battleBox;
	private int branchIndex;
	private SoundEffect sound;
	
	public static void main(String[] args) {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/encounter_generator");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		RandomEncounterGeneratorCLI application = new RandomEncounterGeneratorCLI(dataSource);
		
		application.run();
	}
	
	public RandomEncounterGeneratorCLI(DataSource dataSource) {
		
		this.branchIndex = 0;
		this.charCreate = new CharacterCreator();
		this.cop = new TrafficCop(dataSource);
		this.uiAssist = new UIassist();
		this.sound = new SoundEffect();
	}
	
 
	public int getMinDeletions(String s) {
	    
	    String checkString = "";
	    Map<String, Integer> checked = new HashMap<String, Integer>();
	    Set<String> keys = new HashSet<String>();
	    
	    for (int i = 0; i < s.length(); i++) {
	    	
	    	int deletionCount = 0;
	    	int charCount = 0;
	        checkString = s.substring(i, i+1);
	        keys.add(checkString);
	        
	        for (int n = 0; n < s.length(); n++) {
	            if (checkString.equals(s.substring(n, n+1))) {
	                charCount++;
	                if (charCount > 1) {
	                	deletionCount++;
	                }
	            }
	        }
	        checked.put(checkString, deletionCount);
	    }
	    
	    int totalDeletions = 0;
	    
	    for (String k : keys) {
	    	totalDeletions += checked.get(k);
	    }
	    
	    return totalDeletions;
	}


	public void run() {

		System.out.println(getMinDeletions("kkkkkllooooiiiddbb"));
		
		while(branchIndex == 0) {
		
			if (uiAssist.getSelection().isEmpty()) {
				sound.play("REGEN THEME");
				uiAssist.callPrintMainMenu();
			}
			else {
				uiAssist.callPrintReducedMainMenu();
			}
			
			this.buildEnc = new EncounterBuilder();
			
			if (!uiAssist.isReset() && uiAssist.getSelection().equals("1")) {
				
				charCreate.resetPlayer();
				uiAssist.callCharacterCreationScreen();
				String name = charCreate.selectCharacterName();
				uiAssist.callChooseRaceScreen(name, cop.getListOfRaces());
				charCreate.selectCharacterRace(cop.getListOfRaces());
				uiAssist.callChooseClassScreen(name, cop.getListOfClasses());
				charCreate.selectCharacterClass(cop.getListOfClasses());
				charCreate.getPlayer().getCharClass().setSpellList(cop.getListOfSpellsByClass(charCreate.getPlayer().getCharClass().getClassID()));
				uiAssist.callChooseWeaponScreen(name, cop.getListOfWeapons());
				charCreate.selectCharacterWeapon(cop.getListOfWeapons());
				uiAssist.callViewCharacterScreen(charCreate.getPlayer());
				
				if (!uiAssist.isReset() && uiAssist.getSelection().equals("1")) {
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now(); 
					charCreate.getPlayer().setSavedOn(now);
					cop.saveCharacter(charCreate.getPlayer());
					uiAssist.callSuccessfulCharSaveScreen();
					
					if (!uiAssist.isReset() && uiAssist.getSelection().equals("1")) {
						buildEnc.setPlayer(charCreate.getPlayer());
						branchIndex = 1;
						runEncounterBuilder();
					}
					else if (!uiAssist.isReset() && uiAssist.getSelection().equals("2")) {
						continue;
					}
				}
				else if (uiAssist.getSelection().equals("2")) {
					charCreate.resetPlayer();
					continue;
				}
			}
			else if (!uiAssist.isReset() && uiAssist.getSelection().equals("2")) {
				
				uiAssist.callLoadCharacters(cop.getSavedCharacters());
				boolean isValid = false;
				if (uiAssist.getSelection().equals("0")) {
					continue;
				}
				else {					
					for (PlayerCharacter p : cop.getSavedCharacters()) {
						if (uiAssist.getSelection().equals(String.valueOf(p.getCharID()))) {
							isValid = true;
							uiAssist.callViewCharacterScreenLoadChar(p);
						
							if (uiAssist.getSelection().equals("1") && isValid == true) {
								buildEnc.setPlayer(p);
								uiAssist.callSuccessfulAddToEncounter(buildEnc.getPlayer().getName());
								branchIndex = 1;
								runEncounterBuilder();
							}
							else if (uiAssist.getSelection().equals("0")) {
								continue;
							}
						}
					}
					
					if (isValid == false) {
						uiAssist.callInvalidSelection();
					}
				}
			}
			else if (uiAssist.getSelection().equals("3")) {
				
				uiAssist.callGoodbye();
				System.exit(0);
			}
		}
	}
	
	public void runEncounterBuilder() {
		
		while(branchIndex == 1) {
			
			if (buildEnc.getSelection().isEmpty()) {
				buildEnc.callEncounterBuilderMenu();
			}
			else {
				buildEnc.callEncounterBuilderMenuLight();
			}
			
			if (buildEnc.getSelection().equals("1")) {
				buildEnc.callSetDifficultyScreen(cop.getMobList());
			}
			
			else if (buildEnc.getSelection().equals("2")) {
				buildEnc.callSelectLocationScreen(cop.getLocationList());
			}
			
			else if (buildEnc.getSelection().equals("3")) {
				buildEnc.generateRandomEncounter(cop.getMobList(), cop.getLocationList());
				
				if (buildEnc.getSelection().equals("1")) {
					branchIndex = 0;
					battleBox = new BattleBox(buildEnc);
					battleBox.playerTurn();
				}
				else if (buildEnc.getSelection().equals("2")) {
					buildEnc.viewEncounterDetails();
					if (buildEnc.getSelection().equals("1")) {
						branchIndex = 0;
						battleBox = new BattleBox(buildEnc);
						battleBox.playerTurn();
					}
				}
			}
			else if (buildEnc.getSelection().equals("4")) {
				buildEnc.callPrintEncounterReady();
				if (buildEnc.getSelection().equals("1")) {
					branchIndex = 0;
					battleBox = new BattleBox(buildEnc);
					battleBox.playerTurn();
				}
				else if (buildEnc.getSelection().equals("2")) {
					buildEnc.viewEncounterDetails();
					if (buildEnc.getSelection().equals("1")) {
						branchIndex = 0;
						battleBox = new BattleBox(buildEnc);
						battleBox.playerTurn();
					}
				}
			}			
			else if (buildEnc.getSelection().equals("5")) {
				branchIndex = 0;
				run();
			}
		}
	}

	public int getBranchIndex() {
		return branchIndex;
	}
	public void setBranchIndex(int branchIndex) {
		this.branchIndex = branchIndex;
	}
}
