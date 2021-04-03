package fr.dut.info.monsters.act1;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;

public class Cultist extends AbstractOpponent{
	public static final String name = "Cultist";
	
	public Cultist() {
		super("Cultist", Randomizer.randomInt(48, 55));
		super.firstMove(new Move("Incantation", 100, 1, ActionBuilder.stringToArray("damage"), 3));
		super.addMove(new Move("Dark Strike", 100, -1, ActionBuilder.stringToArray("damage"), 6));
	}
	
	@Override
	public String toString() {
		return name + "\n" + super.toString();
	}
}
