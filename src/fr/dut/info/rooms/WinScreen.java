package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class WinScreen implements Room{
	
	//returns always false to not go any forward in the Map room[] list (which would trigger an error)
	//the only choice is to press a key to close the program
	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		return false;
	}

	@Override
	public String getRoomType() {
		return "WinScreen";
	}

}
