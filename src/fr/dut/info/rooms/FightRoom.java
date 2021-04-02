package fr.dut.info.rooms;

import java.util.TreeMap;

import fr.dut.info.UI;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;

public class FightRoom extends Room{
	private final TreeMap<Integer, Opponent> opponents;
	
	public FightRoom(Player player, UI ui) {
		super(player, ui);
		this.opponents = new TreeMap<Integer, Opponent>();
		
	}
	
	public void startCombat() {
		PlayerAvatar playerAvatar = new PlayerAvatar(getPlayer(), 3);
		while(opponents.size() != 0 || !(playerAvatar.isDead())) {
			playerAvatar.drawFiveCards();
			
		}
	}
}
