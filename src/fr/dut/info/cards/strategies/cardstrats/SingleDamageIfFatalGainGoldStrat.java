package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class SingleDamageIfFatalGainGoldStrat implements Strat{
	private final int damage;
	
	public SingleDamageIfFatalGainGoldStrat(int value) {
		damage = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int modifiedDamage = damage;
		modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
		modifiedDamage = opponents.get(target).getStats().applyDefenderModifiers(modifiedDamage);
		if((opponents.get(target).getCurrentHP() - modifiedDamage) <= 0) {
			playerAvatar.gainGold(damage);
			Log.getLog().addLog("You gain " + damage + " gold");
		}
		opponents.get(target).takeDamage(modifiedDamage);
		Log.getLog().addLog("You deal " + modifiedDamage + " damage to " + opponents.get(target).getName());
	}
}
