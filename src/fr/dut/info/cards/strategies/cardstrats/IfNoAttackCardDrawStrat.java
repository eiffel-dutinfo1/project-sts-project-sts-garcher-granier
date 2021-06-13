package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class IfNoAttackCardDrawStrat implements Strat{
	private final int nbCard;
	
	public IfNoAttackCardDrawStrat(int value) {
		nbCard = value;
	}
	
	//special attack only possible if no attack card is present in hand
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int nbAttack = 0;
		for(Card card : playerAvatar.getHand()) {
			if(card.getCardType().equals("attack")) {
				nbAttack ++;
				break;
			}
		}
		if(nbAttack == 0) {
			for(int i = 0; i < nbCard; i++ ) {
				playerAvatar.getHand().add(playerAvatar.drawOneCard());
			}
			Log.getLog().addLog("You draw " + nbCard + "card");
		}
	}
}
