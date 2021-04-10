package fr.dut.info.rooms;

import java.io.IOException;
import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;

public class FightRoom implements Room {
	private final TreeMap<Integer, Opponent> opponents;
	private int numberOfOpponents;
	private PlayerAvatar avatar;
	private int selectedCard;
	private int selectedTarget;

	public FightRoom(Player player) {
		this.opponents = new TreeMap<Integer, Opponent>();
		numberOfOpponents = 0;
		avatar = new PlayerAvatar(player);
		resetSelected();
	}

	public void addOpponent(Opponent opponent) {
		numberOfOpponents++;
		opponents.put(numberOfOpponents, opponent);
	}

	public TreeMap<Integer, Opponent> getOpponents() {
		return opponents;
	}

	public PlayerAvatar getAvatar() {
		return avatar;
	}

	public void selectTarget(int i) {
		selectedTarget = i;
	}

	public void selectCard(int i) {
		selectedCard = i;
	}

	public boolean targetSelected() {
		return selectedTarget >= 0;
	}

	public boolean cardSelected() {
		return selectedCard >= 0;
	}

	public void playSelected() throws IOException {
		avatar.getHand().get(selectedCard).playCard(opponents, avatar, selectedTarget);
		resetSelected();
	}

	private void resetSelected() {
		selectedTarget = -1;
		selectedCard = -1;
	}

	/*
	 * public void startCombat() throws IOException { PlayerAvatar playerAvatar =
	 * new PlayerAvatar(getPlayer(), 3); while(opponents.size() != 0 ||
	 * !(playerAvatar.isDead())) { for (Entry<Integer, Opponent> entry :
	 * opponents.entrySet()) { System.out.println(entry.getValue().toString()); }
	 * playerAvatar.drawFiveCards(); while(Input.endTurn() != 1) {
	 * System.out.println(playerAvatar.getEnergy());
	 * playerAvatar.selectCard().playCard(opponents, playerAvatar); for
	 * (Entry<Integer, Opponent> entry : opponents.entrySet()) {
	 * System.out.println(entry.getValue().toString()); } }
	 * playerAvatar.emptyHand(); for (Entry<Integer, Opponent> entry :
	 * opponents.entrySet()) { if (entry.getValue().getHp() <= 0) {
	 * opponents.remove(entry.getKey()); } else {
	 * entry.getValue().executeMove(entry.getValue(), playerAvatar); } } } if
	 * (!(playerAvatar.isDead())) { System.out.println("You win !"); } else {
	 * System.out.println("You lose !"); } }
	 */
}
