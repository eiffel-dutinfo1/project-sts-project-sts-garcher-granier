package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DrawCardStrat implements Strat{
	private final int nbCard;
	
	public DrawCardStrat(int value) {
		nbCard = value;
	}
	
	//draw a number of cards
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(int i = 0; i < nbCard; i++ ) {
			playerAvatar.getHand().add(playerAvatar.drawOneCard());
		}
		Log.getLog().addLog("You draw " + nbCard + "card");
	}
}
