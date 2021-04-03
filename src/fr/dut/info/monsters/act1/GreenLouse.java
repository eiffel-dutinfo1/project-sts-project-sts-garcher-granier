package fr.dut.info.monsters.act1;

import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.player.PlayerAvatar;

public class GreenLouse extends AbstractOpponent{

	private final int damage;
	
	public GreenLouse() {
		super((int) (Math.random() * ( 17 - 11 )));
		this.damage = (int) (Math.random() * ( 7 - 5 ));
	}
	
	public void bite(PlayerAvatar playerAvatar) {
		dealDamage(playerAvatar, damage);
	}
	
	public void spitWeb(PlayerAvatar playerAvatar) {
		
	}
}
