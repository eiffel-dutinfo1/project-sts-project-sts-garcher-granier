package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class AcidSlimeS extends AbstractOpponent{
	private final Move lick;
	private final Move tackle;
	
	public AcidSlimeS() {
		super("Acid Slime S", Randomizer.randomInt(8, 13), "resources/pictures/AcidSlimeS.png");
		lick = new Move("Lick", 100, 1, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("1"));
		tackle = new Move("Tackle", 100, 1, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("3"));
		if (Randomizer.randomInt(0, 2) == 1) {
			super.firstMove(lick);
		} else {
			super.firstMove(tackle);
		}
		super.addMove(lick);
		super.addMove(tackle);
	}
	
	//force rotation
	@Override
	public void getNextMove() {
		if (super.nextMove().getName().equals("Lick")) {
			super.setNextMove(tackle);
		} else {
			super.setNextMove(lick);
		}
	}
}