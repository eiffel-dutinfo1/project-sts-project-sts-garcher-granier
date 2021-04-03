package fr.dut.info.rooms;

import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dut.info.UI;
import fr.dut.info.controller.Input;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;

public class FightRoom extends Room {
	private final TreeMap<Integer, Opponent> opponents;
	private int numberOfOpponents;
	
	public FightRoom(Player player) {
		super(player);
		this.opponents = new TreeMap<Integer, Opponent>();
		numberOfOpponents = 1;
	}
	
	public void addOpponent(Opponent opponent) {
		opponents.put(numberOfOpponents, opponent);
		numberOfOpponents++;
	}
	
	public void startCombat() throws IOException {
		PlayerAvatar playerAvatar = new PlayerAvatar(getPlayer(), 3);
		while(opponents.size() != 0 || !(playerAvatar.isDead())) {
			for (Entry<Integer, Opponent> entry : opponents.entrySet()) {
				System.out.println(entry.getValue().toString());
			}
			playerAvatar.drawFiveCards();
			while(Input.endTurn() != 1) {
				System.out.println(playerAvatar.getEnergy());
				playerAvatar.selectCard().playCard(opponents, playerAvatar);
				for (Entry<Integer, Opponent> entry : opponents.entrySet()) {
					System.out.println(entry.getValue().toString());
				}
			}
			playerAvatar.emptyHand();
			for (Entry<Integer, Opponent> entry : opponents.entrySet()) {
				if (entry.getValue().getHp() <= 0) {
					opponents.remove(entry.getKey());
				}
				else {
					entry.getValue().executeMove(entry.getValue(), playerAvatar);
				}
			}
		}
		if (!(playerAvatar.isDead())) {
			System.out.println("You win !");
		}
		else {
			System.out.println("You lose !");
		}
	}
}
