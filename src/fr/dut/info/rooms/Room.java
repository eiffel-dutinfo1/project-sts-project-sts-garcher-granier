package fr.dut.info.rooms;

import fr.dut.info.UI;
import fr.dut.info.player.Player;

public class Room {
	private final Player player;
	private final UI ui;
	public Room(Player player, UI ui) {
		this.player = player;
		this.ui = ui;
	}
	public void enterRoom(Player player) {
		
	}
	
	public UI getUi() {
		return ui;
	}
}
