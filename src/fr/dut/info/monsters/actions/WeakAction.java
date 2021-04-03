package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class WeakAction implements MonsterAction {
	private final int weak;
	
	public WeakAction(int value) {
		weak = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		avatar.applyWeak(weak);
	}
	
	@Override
	public String toString() {
		return "Applies weak for " + weak + " turn" + (weak > 1 ? "s" : "") + " to you.";
	}
}
