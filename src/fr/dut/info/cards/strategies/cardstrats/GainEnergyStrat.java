package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class GainEnergyStrat implements Strat{
	private final int energy;
	
	public GainEnergyStrat(int value) {
		energy = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.gainEnergy(energy);
		Log.getLog().addLog("You gain " + energy + " energy");
	}
}
