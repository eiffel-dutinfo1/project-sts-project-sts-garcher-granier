package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class StartRoom implements Room {
	
	//the startroom is a "false room" in the sense that it is only a screen to let the player choose his hero
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
