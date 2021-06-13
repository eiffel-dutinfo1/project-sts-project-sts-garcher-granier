package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class CardHandIntoDrawStrat implements Strat{
	private final int nbCard;
	
	public CardHandIntoDrawStrat(int value) {
		nbCard = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		if(playerAvatar.getHand().size()-nbCard > 0) {
			for(int i = 0; i < nbCard; i++) {
				Card card = playerAvatar.cardHandToDraw();
				Log.getLog().addLog("You put " + card.getName() + " in your draw");
			}
		}
	}
}
