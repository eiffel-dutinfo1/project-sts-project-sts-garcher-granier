package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class Cultist extends AbstractOpponent{
	
	public Cultist() {
		super("Cultist", Randomizer.randomInt(48, 55), "resources/pictures/cultist.png");
		super.firstMove(new Move("Incantation", 100, 1, ActionBuilder.stringsToArray("ritual"), ActionBuilder.integersToArray("3")));
		super.addMove(new Move("Dark Strike", 100, 1, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("6")));
	}
}
