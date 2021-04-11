package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class DamageAction implements MonsterAction {
	private final int damage;
	
	public DamageAction(int value) {
		damage = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		avatar.takeDamage(damage);
		Log.getLog().addLog("You take " + damage + " damage from " + opponent);
	}
	
	@Override
	public String toString() {
		return "Deals " + damage + " damage to you.";
	}
}
