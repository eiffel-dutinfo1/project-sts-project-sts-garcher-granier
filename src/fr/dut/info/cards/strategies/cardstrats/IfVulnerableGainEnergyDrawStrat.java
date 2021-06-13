package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class IfVulnerableGainEnergyDrawStrat implements Strat{
	private final int nb;
	
	public IfVulnerableGainEnergyDrawStrat(int value) {
		nb = value;
	}
	
	//if the enemy is vulnerable, gains energy and draw
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		if(opponents.get(target).getStats().getVulnerability() > 0) {
			playerAvatar.gainEnergy(nb);
			playerAvatar.drawOneCard();
			Log.getLog().addLog("You gain " + nb + " energy");
			Log.getLog().addLog("You draw " + 1 + " card");
		}
	}
}
