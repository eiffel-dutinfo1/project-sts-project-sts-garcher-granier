package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class SingleDamageIfFatalHpmax implements Strat{
	private final int damage;
	
	public SingleDamageIfFatalHpmax(int value) {
		damage = value;
	}
	
	//single target damage, special effect if target dies
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		int modifiedDamage = damage;
		//we modify the damage according to the monster's stats (block, vulnerability...) before applying damages
		modifiedDamage = playerAvatar.getStats().applyAttackerModifiers(modifiedDamage);
		modifiedDamage = opponents.get(target).getStats().applyDefenderModifiers(modifiedDamage);
		if((opponents.get(target).getCurrentHP() - modifiedDamage) <= 0) {
			playerAvatar.gainMaxHp(3);
			Log.getLog().addLog("You gain " + 3 + " max hp");
		}
		opponents.get(target).takeDamage(modifiedDamage);
		Log.getLog().addLog("You deal " + modifiedDamage + " damage to " + opponents.get(target).getName());
	}
}
