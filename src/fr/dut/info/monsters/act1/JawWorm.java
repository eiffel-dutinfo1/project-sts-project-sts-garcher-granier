package fr.dut.info.monsters.act1;

import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.player.PlayerAvatar;

public class JawWorm extends AbstractOpponent{
	
	public JawWorm() {
		super((int) (Math.random() * ( 44 - 40 )));
	}
	
	public void chomp(PlayerAvatar playerAvatar) {
		dealDamage(playerAvatar, 11);
	}
	
	public void trash(PlayerAvatar playerAvatar) {
		dealDamage(playerAvatar, 7);
		dealBlock(5);
	}
	
	public void bellow() {
		dealStrength(3);
		dealBlock(6);
	}
}
