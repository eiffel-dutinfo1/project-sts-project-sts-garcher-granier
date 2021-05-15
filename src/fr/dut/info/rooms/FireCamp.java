package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class FireCamp implements Room{
	
	public FireCamp() {
		
	}

	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		if(index == 1) {
			player.heal();
			return true;
		}
		return false;
	}

}
