package fr.dut.info.cards.colorless;

import java.util.ArrayList;

import fr.dut.info.cards.AbstractCard;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

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
