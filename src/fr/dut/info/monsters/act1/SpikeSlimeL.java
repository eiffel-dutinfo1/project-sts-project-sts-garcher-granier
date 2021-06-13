package fr.dut.info.monsters.act1;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class SpikeSlimeL extends AbstractOpponent{
	private final int splitThreshold;

	public SpikeSlimeL() {
		super("Spike Slime L", Randomizer.randomInt(65, 70), "resources/pictures/SpikeSlimeL.png");
		splitThreshold = (super.getMaxHP()/2);
		super.addMove(new Move("Lick", 70, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("2")));
		super.addMove(new Move("Flame Tackle", 30, 2, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("16#2")));
		super.getNextMove();
	}
	
	public SpikeSlimeL(int hp) {
		super("Spike Slime L", hp, "resources/pictures/SpikeSlimeL.png");
		splitThreshold = (hp/2);
		super.addMove(new Move("Lick", 70, 2, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("2")));
		super.addMove(new Move("Flame Tackle", 30, 2, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("16#2")));
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
