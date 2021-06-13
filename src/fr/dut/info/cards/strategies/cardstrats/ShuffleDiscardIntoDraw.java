package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class ShuffleDiscardIntoDraw implements Strat{
	private final int nb;
	
	public ShuffleDiscardIntoDraw(int value) {
		nb = value;
	}
	
	//shuffle a card from discard into draw
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(Card card : playerAvatar.getDiscard()) {
			playerAvatar.reverseRemoveCard(card);
		}
		playerAvatar.shuffleHand();
		Log.getLog().addLog("You have shuffled your discard pile in your hand");
	}
}
