package fr.dut.info.monsters.act1;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.AbstractOpponent;
import fr.dut.info.monsters.ActionBuilder;
import fr.dut.info.monsters.Move;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class RedLouse extends AbstractOpponent{
	private final Move curlUp;
	private boolean curlUpUsed;
	public RedLouse() {
		super("Red Louse", Randomizer.randomInt(10, 15), "resources/pictures/LouseRed.png");
		super.addMove(new Move("Bite", 75, 3, ActionBuilder.stringsToArray("damage"), ActionBuilder.integersToArray(String.valueOf(Randomizer.randomInt(5, 8)))));
		super.addMove(new Move("Grow", 25, 3, ActionBuilder.stringsToArray("strength"), ActionBuilder.integersToArray("3")));
		curlUp = new Move("Curl up", 100, 1, ActionBuilder.stringsToArray("block"), ActionBuilder.integersToArray(String.valueOf(Randomizer.randomInt(3, 7))));
		curlUpUsed = false;
		super.getNextMove();
	}
	
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
	
	@Override
	public void executeMove(Opponent self, PlayerAvatar avatar) throws IOException {
		curlUpUsed = false;
		super.executeMove(self, avatar);
	}
}
