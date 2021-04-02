package fr.dut.info.cards.strategies;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.controller.Input;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class SingleTargetAttackStrat implements Strat {
	private int damage;
	
	public SingleTargetAttackStrat(int value) {
		damage = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar) throws IOException {
		if (opponents.size() == 1) {
			opponents.get(opponents.firstKey()).takeDamage(damage);
		} else {
			opponents.get(Input.getTarget(opponents)).takeDamage(damage);
		}
	}
	
	@Override
	public String toString() {
		return "Deals " + damage + " damage to a single opponent.";
	}
}
