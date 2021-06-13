package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class AcidSlimeM extends AbstractOpponent{
	public AcidSlimeM() {
		super("Acid Slime M", Randomizer.randomInt(28, 33), "resources/pictures/AcidSlimeM.png");
		super.addMove(new Move("Corrosive Spit", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("7#1")));
		super.addMove(new Move("Lick", 30, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("1")));
		super.addMove(new Move("Tackle", 40, 3, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("10")));
		super.getNextMove();
	}
	
	public AcidSlimeM(int hp) {
		super("Acid Slime M", hp, "resources/pictures/AcidSlimeM.png");
		super.addMove(new Move("Corrosive Spit", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("7#1")));
		super.addMove(new Move("Lick", 30, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("1")));
		super.addMove(new Move("Tackle", 40, 3, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("10")));
		super.getNextMove();
	}
}
