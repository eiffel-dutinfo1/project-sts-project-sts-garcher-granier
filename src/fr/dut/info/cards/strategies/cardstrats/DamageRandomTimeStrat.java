package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.Randomizer;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DamageRandomTimeStrat implements Strat{
	private final int damage;
	
	public DamageRandomTimeStrat(int value) {
		damage = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		for(int i = 0; i < damage; i++) {
			Integer nb = Randomizer.randomInt(0, opponents.size());
			Opponent opponentTarget = opponents.get(nb);
			int modifiedDamage = damage + playerAvatar.getStats().getBlock();
			modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
			modifiedDamage = opponents.get(nb).getStats().applyDefenderModifiers(modifiedDamage);
			opponents.get(nb).takeDamage(modifiedDamage);
			Log.getLog().addLog("You deal " + modifiedDamage + " damage to " + opponentTarget.getName());
		}
	}
}
