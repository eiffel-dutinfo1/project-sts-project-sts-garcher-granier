package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class RitualAction implements MonsterAction {
	private final int ritual;
	
	public RitualAction(int value) {
		ritual = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		opponent.getStats().addRitual(ritual);
		Log.getLog().addLog(opponent.getName() + " gains " + ritual + " stack" + (ritual > 1 ? "s" : "") + " of ritual");
	}
}
