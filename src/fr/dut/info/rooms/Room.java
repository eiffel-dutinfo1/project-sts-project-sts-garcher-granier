package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.player.Player;

public interface Room {
	public boolean roomEvent(int index, Player player) throws IOException;
	public String getRoomType();
}
