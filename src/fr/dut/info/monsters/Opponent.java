package fr.dut.info.monsters;

import java.io.IOException;

import fr.dut.info.player.PlayerAvatar;

public interface Opponent {

	boolean takeDamage(int value);
	void applyStrength(int value);
	void applyBlock(int value);
	void executeMove(Opponent self, PlayerAvatar avatar) throws IOException ;
}
