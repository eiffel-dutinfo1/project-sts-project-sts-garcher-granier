package fr.dut.info.monsters;

import java.io.IOException;

import fr.dut.info.player.PlayerAvatar;

public interface MonsterAction {
	void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException;
}
