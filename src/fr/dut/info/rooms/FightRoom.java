package fr.dut.info.rooms;

import java.util.TreeMap;

import fr.dut.info.UI;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.Player;

public class FightRoom extends Room{
	private final TreeMap<Integer, Opponent> opponents;
	
	public FightRoom(Player player, UI ui) {
		super(player, ui);
		
	}
}
