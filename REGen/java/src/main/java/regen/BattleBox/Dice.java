package regen.BattleBox;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

	public int roll4() {
		int roll = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		return roll;
	}
	public int roll6() {
		int roll = ThreadLocalRandom.current().nextInt(1, 6 + 1);
		return roll;	
	}
	public int roll8() {
		int roll = ThreadLocalRandom.current().nextInt(1, 8 + 1);
		return roll;
	}
	public int roll10() {
		int roll = ThreadLocalRandom.current().nextInt(1, 10 + 1);
		return roll;
	}
	public int roll12() {
		int roll = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		return roll;
	}
	public int roll20() {
		int roll = ThreadLocalRandom.current().nextInt(1, 20 + 1);
		return roll;
	}
	public int roll100() {
		int roll = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		return roll;
	}
	public int rollMinions() {
		int roll = ThreadLocalRandom.current().nextInt(1, 3 + 1);
		return roll;
	}
}
