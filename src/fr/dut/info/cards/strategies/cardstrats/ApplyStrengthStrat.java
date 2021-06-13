package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class ApplyStrengthStrat implements Strat{
	private final int strength;
	
	public ApplyStrengthStrat(int value) {
		strength = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		opponents.get(target).getStats().addStrength(strength);;
		Log.getLog().addLog(opponents.get(target).getName() + " lose " + Math.abs(strength) + " strength");
	}
}
