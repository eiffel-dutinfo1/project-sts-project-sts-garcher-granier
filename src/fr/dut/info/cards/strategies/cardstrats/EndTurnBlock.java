package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class EndTurnBlock implements Strat{
	private final int block;
	
	public EndTurnBlock(int value) {
		block = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.getStats().addEndTurnBlock(block);
		Log.getLog().addLog("You apply " + block + " block to yourself");
	}
}
