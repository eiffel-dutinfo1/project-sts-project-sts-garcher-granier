package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class PreparingAction implements MonsterAction {
	
	public PreparingAction() {
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		Log.getLog().addLog("Slime Boss is preparing something");
	}
	
	@Override
	public String actionPreview() {
		return "preparing";
	}
}
