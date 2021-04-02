package fr.dut.info.cards.strategies;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public interface Strat {
	void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar) throws IOException;
}
