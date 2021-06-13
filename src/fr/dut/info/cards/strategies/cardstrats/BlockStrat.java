package fr.dut.info.cards.strategies.cardstrats;

import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class BlockStrat implements Strat{
private int block;
	
	public BlockStrat(int value) {
		block = value;
	}
	
	//gives block to player
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) {
		playerAvatar.getStats().addBlock(block);
		Log.getLog().addLog("You apply " + block + " block to yourself");
	}
}
