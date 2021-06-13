package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
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
		opponent.getStats().addWeak(weak);
		Log.getLog().addLog(opponent.getName() + " applies " + weak + " stack" + (weak > 1 ? "s" : "") + " of weak to you");
	}
	
	@Override
	public String actionPreview() {
		return "weak";
	}
}
