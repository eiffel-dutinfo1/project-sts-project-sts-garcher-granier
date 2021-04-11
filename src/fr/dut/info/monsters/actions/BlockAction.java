package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class BlockAction implements MonsterAction {
	private final int block;
	
	public BlockAction(int value) {
		block = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		opponent.getStats().addBlock(block);
	}
	
	@Override
	public String toString() {
		return "Applies " + block + " stack" + (block > 1 ? "s" : "") + " to himself.";
	}
}
