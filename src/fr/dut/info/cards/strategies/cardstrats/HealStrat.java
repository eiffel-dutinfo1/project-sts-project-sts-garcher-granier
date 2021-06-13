package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class HealStrat implements Strat{
	private final int heal;
	
	public HealStrat(int value) {
		heal = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.heal(heal);
		Log.getLog().addLog("You have been heal of " + heal + " hp");
	}
}
