package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.Randomizer;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DiscardRandomStrat implements Strat{
	private final int value;
	
	public DiscardRandomStrat(int value) {
		this.value = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.removeCardNoExhaust(playerAvatar.getHand().get(Randomizer.randomInt(0, playerAvatar.getHand().size())));
		Log.getLog().addLog("You discard " + value + " random card(s)");
	}
}
