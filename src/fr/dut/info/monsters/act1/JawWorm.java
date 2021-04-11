package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;
import fr.dut.info.player.PlayerAvatar;

public class JawWorm extends AbstractOpponent{
	
	public JawWorm() {
		super("Jaw Worm", Randomizer.randomInt(40, 44));
		super.addMove(new Move("Bellow", 45, 1, ActionBuilder.stringToArray("strength-block"), 6));
		super.addMove(new Move("Dark Strike", 100, -1, ActionBuilder.stringToArray("damage"), 6));
		super.addMove(new Move("Dark Strike", 100, -1, ActionBuilder.stringToArray("damage"), 6));
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
