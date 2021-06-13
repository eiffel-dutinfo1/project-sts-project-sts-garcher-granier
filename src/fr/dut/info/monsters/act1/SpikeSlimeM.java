package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class SpikeSlimeM extends AbstractOpponent{
	public SpikeSlimeM() {
		super("Spike Slime M", Randomizer.randomInt(28, 33), "resources/pictures/SpikeSlimeM.png");
		super.addMove(new Move("Lick", 70, 3, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("1")));
		super.addMove(new Move("Flame Tackle", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("8#1")));
		super.getNextMove();
	}
	
	public SpikeSlimeM(int hp) {
		super("Spike Slime M", hp, "resources/pictures/SpikeSlimeM.png");
		super.addMove(new Move("Lick", 70, 3, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("1")));
		super.addMove(new Move("Flame Tackle", 30, 3, ActionBuilder.stringsToArray("damage#slime"), ActionBuilder.integersToArray("8#1")));
		super.getNextMove();
	}
}
