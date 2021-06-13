package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class IfAllAttackSingleDamageStrat implements Strat{
	private final int damage;
	
	public IfAllAttackSingleDamageStrat(int value) {
		damage = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int compteur = 0;
		for(Card card : playerAvatar.getHand()) {
			if(card.getCardType().equals("attack")) {
				compteur++;
			}
		}
		if(compteur == playerAvatar.getHand().size()) {
			int modifiedDamage = damage;
			modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
			modifiedDamage = opponents.get(target).getStats().applyDefenderModifiers(modifiedDamage);
			opponents.get(target).takeDamage(modifiedDamage);
			Log.getLog().addLog("You deal " + modifiedDamage + " damage to " + opponents.get(target).getName());
		}
	}
}
