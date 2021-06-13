package fr.dut.info.cards.strategies.cardstrats;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.Log;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class LoseHpStrat implements Strat{
	private final int hp;
	
	public LoseHpStrat(int value) {
		hp = value;
	}
	
	//substracts hp from player healthpool
	@Override
	public void useStrat(TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar, int target) throws IOException {
		playerAvatar.takeDamage(hp);
		Log.getLog().addLog("You lost " + hp + " hp");
	}
}
