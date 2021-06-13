package fr.dut.info.monsters;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.player.PlayerAvatar;
import fr.dut.info.stats.Stats;

public interface Opponent {
	boolean takeDamage(int value);
	void executeMove(Opponent self, PlayerAvatar avatar) throws IOException ;
	String getPicturePath();
	int getCurrentHP();
	int getMaxHP();
	Stats getStats();
	String getName();
	boolean isDead();
	ArrayList<Move> getMoves();
	Move nextMove();
}
