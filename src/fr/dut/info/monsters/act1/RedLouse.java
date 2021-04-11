package fr.dut.info.monsters.act1;

import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.player.PlayerAvatar;

public class RedLouse extends AbstractOpponent{
	
	private final int damage;
	
	public RedLouse() {
		super((int) (Math.random() * ( 15 - 10 )));
		this.damage = (int) (Math.random() * ( 7 - 5 ));
	}
	
	public void bite(PlayerAvatar playerAvatar) {
		dealDamage(playerAvatar, damage);
	}
	
	public void grow() {
		dealStrength(3);
	}
}
