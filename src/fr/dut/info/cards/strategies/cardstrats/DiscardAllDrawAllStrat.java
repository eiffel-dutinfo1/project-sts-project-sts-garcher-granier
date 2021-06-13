package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.Randomizer;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DiscardAllDrawAllStrat implements Strat {
	public DiscardAllDrawAllStrat() {
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int handSize = playerAvatar.getHand().size();
		for (int i = 0; i < handSize; i++) {
			playerAvatar.removeCardNoExhaust(playerAvatar.getHand().get(0));
		}
		for (int i = 0; i < handSize; i++) {
			playerAvatar.getHand().add(playerAvatar.drawOneCard());
		}
		Log.getLog().addLog("You replaced your hand with new cards");
	}

}
