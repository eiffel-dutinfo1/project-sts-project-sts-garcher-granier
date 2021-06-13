package fr.dut.info.monsters.actions;

import java.io.IOException;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.monsters.MonsterAction;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class SlimeAction implements MonsterAction {
	private final int slimeNumber;
	
	public SlimeAction(int value) {
		slimeNumber = value;
	}
	
	@Override
	public void doAction(Opponent opponent, PlayerAvatar avatar) throws IOException {
		for (int i = 0; i < slimeNumber; i++) {
			avatar.addToDiscard(new Card("Slimed", 1, "status", "resources/pictures/Slimed.png", true, false, "status"));
		}
		Log.getLog().addLog(opponent.getName() + " shuffles " + slimeNumber + " Slimed into the discard pile");
	}
	
	@Override
	public String actionPreview() {
		return "Slimed";
	}
}
