package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DamageNbStrikeStrat implements Strat{
	private int nbStrike;
	
	public DamageNbStrikeStrat(int value) {
		nbStrike = value;
	}
	
	//damages an enemy multiple times
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		nbStrike = 0;
		for(Card card : playerAvatar.getHand()) {
			if(card.getName().contains("Strike")) {
				nbStrike++;
			}
		}
		int modifiedDamage = 2 * nbStrike;
		modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
		modifiedDamage = opponents.get(target).getStats().applyDefenderModifiers(modifiedDamage);
		opponents.get(target).takeDamage(modifiedDamage);
		Log.getLog().addLog("You deal " + modifiedDamage + " damage to " + opponents.get(target).getName());
	}
}
