package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class SpikeSlimeS extends AbstractOpponent{
	
	public SpikeSlimeS() {
		super("Spike Slime S", Randomizer.randomInt(10, 16), "resources/pictures/SpikeSlimeS.png");
		super.addMove(new Move("Tackle", 100, 1, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray("5")));
		super.getNextMove();
	}
}