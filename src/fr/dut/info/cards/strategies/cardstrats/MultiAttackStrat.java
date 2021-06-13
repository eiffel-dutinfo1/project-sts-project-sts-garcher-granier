package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class MultiAttackStrat implements Strat {
	private final int damage;
	
	public MultiAttackStrat(int value) {
		damage = value;
	}
	
	//deals damages to all enemies
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int modifiedDamage = damage;
		//we modify the damage according to the monster's stats (block, vulnerability...) before applying damages
		modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().getStats().applyDefenderModifiers(modifiedDamage);
			entry.getValue().takeDamage(modifiedDamage);
		}
		Log.getLog().addLog("You deal " + modifiedDamage + " damage to all opponents");
	}
}