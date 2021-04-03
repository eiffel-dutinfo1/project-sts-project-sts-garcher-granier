package fr.dut.info.monsters.act1;

import java.util.ArrayList;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;
import fr.dut.info.player.PlayerAvatar;

public class Cultist extends AbstractOpponent{
	public static final String name = "Cultist";
	
	public Cultist() {
		super("Cultist", Randomizer.randomInt(54, 59));
		super.firstMove(new Move("Incantation", 1, 1, ActionBuilder.stringToArray("damage"), 3));
		super.addMove(new Move("Dark Strike", 1, -1, ActionBuilder.stringToArray("damage"), 6));
	}
	
	@Override
	public Move getNextMove() {
		ArrayList<Move> moves = super.getMoves();
		
	}
	
	@Override
	public void doAction() {
	}
	
	@Override
	public String toString() {
		return name + "\n" + super.toString();
	}
}
