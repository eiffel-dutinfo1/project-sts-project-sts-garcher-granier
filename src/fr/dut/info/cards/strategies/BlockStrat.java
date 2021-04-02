package fr.dut.info.cards.strategies;

import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class BlockStrat implements Strat{
private int block;
	
	public BlockStrat(int value) {
		block = value;
	}
	
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar) {
		playerAvatar.giveBlock(block);
	}
	
	@Override
	public String toString() {
		return "Adds " + block + " to blocking.";
	}
}
