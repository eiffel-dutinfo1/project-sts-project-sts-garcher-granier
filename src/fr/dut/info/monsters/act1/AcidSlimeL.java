package fr.dut.info.monsters.act1;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class AcidSlimeL extends AbstractOpponent{
	private final int splitThreshold;

	public AcidSlimeL() {
		super("Acid Slime L", Randomizer.randomInt(65, 70), "resources/pictures/AcidSlimeL.png");
		splitThreshold = (super.getMaxHP()/2);
		super.addMove(new Move("Corrosive Spit", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("11#2")));
		super.addMove(new Move("Lick", 30, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("2")));
		super.addMove(new Move("Tackle", 40, 2, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("16")));
		super.getNextMove();
	}
	
	public AcidSlimeL(int hp) {
		super("Acid Slime L", hp, "resources/pictures/AcidSlimeL.png");
		splitThreshold = (hp/2);
		super.addMove(new Move("Corrosive Spit", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("11#2")));
		super.addMove(new Move("Lick", 30, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("2")));
		super.addMove(new Move("Tackle", 40, 2, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("16")));
		super.getNextMove();
	}
	
	@Override
	public boolean takeDamage(int value) {
		if (super.getCurrentHP()-value < splitThreshold) {
			try {
				Move split = new Move("Split", 100, 1, ActionBuilder.stringsToArray("split"), ActionBuilder.integersToArray(String.valueOf(super.getCurrentHP()-value)));
				split.executeActions(this, null);
				return super.takeDamage(999);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.takeDamage(value);
	}
}
