package fr.dut.info.monsters;

import fr.dut.info.player.PlayerAvatar;

public class Cultist extends AbstractOpponent{
	
	public Cultist() {
		super((int) (Math.random() * ( 54 - 48 )));
	}
	
	public void darkStrike(PlayerAvatar playerAvatar) {
		dealDamage(playerAvatar, 6);
	}
	
	public void incantation() {
		dealStrength(3);
	}
}
