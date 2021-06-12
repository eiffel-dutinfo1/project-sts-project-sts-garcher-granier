package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class ApplyWeak implements Strat{
	private final int weak;
	
	public ApplyWeak(int value) {
		weak = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		opponents.get(target).getStats().addWeak(weak);
		Log.getLog().addLog("You apply " + weak + " weak to " + opponents.get(target).getName());
	}
}
