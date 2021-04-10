package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class SingleTargetAttackStrat implements Strat {
	private final int damage;
	
	public SingleTargetAttackStrat(int value) {
		damage = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		opponents.get(target).takeDamage(damage);
	}
	
	@Override
	public String toString() {
		return "Deals " + damage + " damage to a single opponent.";
	}
}
