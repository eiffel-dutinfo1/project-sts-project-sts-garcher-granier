package fr.dut.info.monsters.act1;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class GreenLouse extends AbstractOpponent{
	private final Move curlUp;
	private boolean curlUpUsed;
	public GreenLouse() {
		super("Green Louse", Randomizer.randomInt(10, 15), "resources/pictures/LouseGreen.png");
		super.addMove(new Move("Bite", 75, 3, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray(String.valueOf(Randomizer.randomInt(5, 8)))));
		super.addMove(new Move("Spit web", 25, 3, ActionBuilder.stringsToArray("weak"), ActionBuilder.integersToArray("3")));
		curlUp = new Move("Bite", 100, 1, ActionBuilder.stringsToArray("block"), ActionBuilder.integersToArray(String.valueOf(Randomizer.randomInt(3, 7))));
		curlUpUsed = false;
		super.getNextMove();
	}
	
	//modified takeDamage() method to give shield to the Louse once a turn after taking damage
	@Override
	public boolean takeDamage(int value) {
		if (!curlUpUsed) {
			try {
				curlUp.executeActions(this, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			curlUpUsed = true;
		}
		return super.takeDamage(value);
	}
	
	//prevent curlUp to be used more than once per turn
	@Override
	public void executeMove(Opponent self, PlayerAvatar avatar) throws IOException {
		curlUpUsed = false;
		super.executeMove(self, avatar);
	}
}
