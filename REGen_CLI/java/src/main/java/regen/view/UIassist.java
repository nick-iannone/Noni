package regen.view;

import java.util.List;
import java.util.Scanner;

import regen.domain.CharacterClass;
import regen.domain.PlayerCharacter;
import regen.domain.Race;
import regen.domain.Weapon;
import regen.main.EncounterBuilder;


public class UIassist {

	private UI menu;
	private Scanner scanner;
	private String selection;
	private boolean finished;
	private boolean reset;
	private SoundEffect sound;
	
	public UIassist() {
		
		this.menu = new UI();
		this.scanner = new Scanner(System.in);
		this.selection = "";
		this.sound = new SoundEffect();
	}
	
	
	public void callPrintMainMenu() {
		menu.printMainMenu();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callPrintReducedMainMenu() {
		menu.printReturnToMainMenu();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callCharacterCreationScreen() {
		menu.printCharacterCreationMenu();
	}

	public void callChooseRaceScreen(String name, List<Race> raceList) {
		menu.printListOfRacesToSelect(name, raceList);
	}

	public void callChooseClassScreen(String name, List<CharacterClass> classList) {
		menu.printListOfClassesToSelect(name, classList);
	}
	
	public void callChooseWeaponScreen(String name, List<Weapon> weaponList) {
		menu.printListOfWeaponsToSelect(name, weaponList);
	}
	
	public void callViewCharacterScreen(PlayerCharacter player) {
		menu.printCharacterSnapshotNewChar(player);
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callViewCharacterScreenLoadChar(PlayerCharacter player) {
		menu.printCharacterSnapshotLoadChar(player);
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callSuccessfulCharSaveScreen() {
		menu.printCharSaveSuccessful();
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callLoadCharacters(List<PlayerCharacter> savedCharacters) {
		menu.printListOfLoadedCharacters(savedCharacters);
		selection = scanner.nextLine();
		sound.play("MenuSelect");
	}
	
	public void callSuccessfulAddToEncounter(String name) {
		menu.printSuccessfulAddToEncounter(name);
	}
	
	public void callInvalidSelection() {
		menu.printErrorInvalidSelection();
	}
	
	public void callNameErrorMessage() {
		menu.printErrorInvalidNameLength();
	}
	
	public void callGoodbye() {
		menu.printGoodbye();
	}
	
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public boolean isReset() {
		return reset;
	}
	public void setReset(boolean reset) {
		this.reset = reset;
	}
}
