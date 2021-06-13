package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DoubleBlockStrat implements Strat{
	private final int nb;

	public DoubleBlockStrat(int value) {
		nb = value;
	}
	
	//double player's block
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.getStats().addBlock(playerAvatar.getStats().getBlock());
		Log.getLog().addLog("You have doubled your block");
	}
}
