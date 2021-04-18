package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class JawWorm extends AbstractOpponent{
	
	public JawWorm() {
		super("Jaw Worm", Randomizer.randomInt(40, 44), "resources/pictures/jawworm.png");
		Move chomp = new Move("Chomp", 25, 1, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("11"));
		super.firstMove(chomp);
		super.addMove(chomp);
		super.addMove(new Move("Bellow", 45, 1, ActionBuilder.stringsToArray("strength#block"), ActionBuilder.integersToArray("3#6")));
		super.addMove(new Move("Thrash", 30, 2, ActionBuilder.stringsToArray("damage#block"), ActionBuilder.integersToArray("7#5")));
	}
}
