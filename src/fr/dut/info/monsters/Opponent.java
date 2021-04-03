package fr.dut.info.monsters;

public interface Opponent {

	boolean takeDamage(int value);
	void applyStrength(int value);
	void doAction();
}
