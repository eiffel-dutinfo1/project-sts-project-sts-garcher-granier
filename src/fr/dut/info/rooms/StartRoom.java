package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class StartRoom implements Room {

	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		if (index == 0) {
			Map.setHero("IronClad");
		}
		if (index == 1) {
			Map.setHero("Silent");
		}
		return true;
	}

	@Override
	public String getRoomType() {
		return "StartRoom";
	}
	
	

}
