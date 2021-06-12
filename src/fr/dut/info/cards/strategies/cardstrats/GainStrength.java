package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class GainStrength implements Strat{
	private final int strength;
	
	public GainStrength(int value) {
		strength = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.getStats().addStrength(strength);
		Log.getLog().addLog("You gain " + strength + " strength");
	}
}
