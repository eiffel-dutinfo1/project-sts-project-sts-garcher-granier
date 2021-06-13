package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class WinScreen implements Room{

	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		return false;
	}

	@Override
	public String getRoomType() {
		return "WinScreen";
	}

}
