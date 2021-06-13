package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.AcidSlimeL;
import fr.dut.info.monsters.act1.AcidSlimeM;
import fr.dut.info.monsters.act1.SpikeSlimeL;
import fr.dut.info.monsters.act1.SpikeSlimeM;
import fr.dut.info.player.PlayerAvatar;
import fr.dut.info.rooms.Map;
import fr.dut.info.rooms.FightRoom;

public class SplitAction implements MonsterAction {
	private final int slimeLife;
	
	public SplitAction(int value) {
		slimeLife = value;
	}
	
	//add two new slimes when the "parents" uses split
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		String slime = opponent.getName();
		if (slime.equals("Acid Slime L")) {
			((FightRoom) Map.getCurrentRoom()).addOpponent(new AcidSlimeM(slimeLife));
			((FightRoom) Map.getCurrentRoom()).addOpponent(new AcidSlimeM(slimeLife));
		} else if (slime.equals("Spike Slime L")) {
			((FightRoom) Map.getCurrentRoom()).addOpponent(new SpikeSlimeM(slimeLife));
			((FightRoom) Map.getCurrentRoom()).addOpponent(new SpikeSlimeM(slimeLife));
		}  else if (slime.equals("Slime Boss")) {
			((FightRoom) Map.getCurrentRoom()).addOpponent(new AcidSlimeL(slimeLife));
			((FightRoom) Map.getCurrentRoom()).addOpponent(new SpikeSlimeL(slimeLife));
		}
		Log.getLog().addLog(opponent.getName() + " splits!");
	}

	@Override
	public String actionPreview() {
		return "split";
	}
}
