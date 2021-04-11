package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class StrengthAction implements MonsterAction {
	private final int strength;
	
	public StrengthAction(int value) {
		strength = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		opponent.applyStrength(strength);
	}
	
	@Override
	public String toString() {
		return "Applies " + strength + " stack" + (strength > 1 ? "s" : "") + " to himself.";
	}
}
