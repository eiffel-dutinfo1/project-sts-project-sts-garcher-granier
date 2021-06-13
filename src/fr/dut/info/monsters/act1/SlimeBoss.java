package fr.dut.info.monsters.act1;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class SlimeBoss extends AbstractOpponent{
	private final int splitThreshold;
	private final Move goop;
	private final Move preparing;
	private final Move slam;
	
	public SlimeBoss() {
		super("Slime Boss", 140, "resources/pictures/SlimeBoss.png");
		goop = new Move("Goop Spray", 100, 1, ActionBuilder.stringsToArray("slime"), ActionBuilder.integersToArray("3"));
		preparing = new Move("Preparing", 100, 1, ActionBuilder.stringsToArray("preparing"), ActionBuilder.integersToArray("1"));
		slam = new Move("Slam", 100, 1, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("35"));
		super.firstMove(goop);
		splitThreshold = 70;
	}
	
	@Override
	public void getNextMove() {
		String moveName = super.nextMove().getName();
		switch (moveName) {
		case "Goop Spray":
			super.setNextMove(preparing);
			break;
		case "Preparing":
			super.setNextMove(slam);
			break;
		case "Slam":
			super.setNextMove(goop);
			break;
		default:
			throw new IllegalArgumentException("Unrecognized Slime Boss move");
		}
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
