package fr.dut.info.monsters;

import java.io.IOException;

import fr.dut.info.player.PlayerAvatar;

public interface Opponent {

	boolean takeDamage(int value);
	void applyStrength(int value);
	void doAction();
	void executeMove(Opponent opponent, PlayerAvatar playerAvatar) throws IOException;
	int getHp();
}
