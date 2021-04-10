package fr.dut.info.monsters;

import java.io.IOException;

import fr.dut.info.player.PlayerAvatar;

public interface Opponent {

	boolean takeDamage(int value);
	void applyStrength(int value);
	void applyBlock(int value);
	int getHp();
	void executeMove(Opponent self, PlayerAvatar avatar) throws IOException ;
	String getPicturePath();
	int getCurrentHP();
	int getMaxHP();
}
