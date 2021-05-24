package fr.dut.info.cards.strategies.cardstrats;

import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class VulnerableStrat implements Strat{
private int vulnerable;
	
	public VulnerableStrat(int value) {
		vulnerable = value;
	}
	
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) {
		opponents.get(target).getStats().addVulnerability(vulnerable);
		Log.getLog().addLog("You apply " + vulnerable + " vulnerability to " + opponents.get(target).getName());
	}
}
