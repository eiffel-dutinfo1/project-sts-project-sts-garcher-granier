package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public class FireCamp implements Room{
	
	public FireCamp() {
		
	}

	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		if(index == 0) {
			player.heal();
			return true;
		}
		//card improvement not yet implemented
		if(index == 1) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoomType() {
		return "FireCamp";
	}

}
