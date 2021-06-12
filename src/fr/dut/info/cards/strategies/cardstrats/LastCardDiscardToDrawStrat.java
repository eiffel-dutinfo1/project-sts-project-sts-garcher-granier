package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class LastCardDiscardToDrawStrat implements Strat{
	private final int nbCard;
	
	public LastCardDiscardToDrawStrat(int value) {
		nbCard = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(int i = 0; i < nbCard; i++) {
			Card card = playerAvatar.getDiscard().get(playerAvatar.getDiscard().size()-1);
			playerAvatar.reverseRemoveCard(card);
			Log.getLog().addLog("You recovered " + card.getName() + " from your discard pile ");
		}
	}
}
