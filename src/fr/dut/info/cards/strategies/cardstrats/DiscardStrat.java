package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;
import fr.dut.info.rooms.FightRoom;
import fr.dut.info.rooms.Map;

public class DiscardStrat implements Strat{
	private final int value;
	
	public DiscardStrat(int value) {
		this.value = value;
	}
	
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		((FightRoom) Map.getCurrentRoom()).activateDiscardMode(value);
		Log.getLog().addLog("You discard the next " + value + " card(s)");
	}

}
