package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class ApplyVulnerable implements Strat{
	private final int vulnerable;
	
	public ApplyVulnerable(int value) {
		vulnerable = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().getStats().addVulnerability(vulnerable);
		}
		Log.getLog().addLog("You apply " + vulnerable + " vulnerability to all opponents");
	}
}
