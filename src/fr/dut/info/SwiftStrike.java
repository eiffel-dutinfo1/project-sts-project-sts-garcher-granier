package fr.dut.info;

import java.util.ArrayList;

public class SwiftStrike extends AbstractCard {
	private final int damage;
	
	public SwiftStrike() {
		super("Swift Strike", 0, "uncommon");
		damage = 6;
	}
	
	@Override
	public boolean playCard(PlayerAvatar playerAvatar, ArrayList<Opponent> opponents) {
		return playerAvatar.takeDamage(damage);
	}
}
