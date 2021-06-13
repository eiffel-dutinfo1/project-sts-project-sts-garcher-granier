package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class ApplyAllWeak implements Strat{
	private final int weak;
	
	public ApplyAllWeak(int value) {
		weak = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().getStats().addWeak(weak);
		}
		Log.getLog().addLog("You apply " + weak + " weak to all opponents");
	}
}
