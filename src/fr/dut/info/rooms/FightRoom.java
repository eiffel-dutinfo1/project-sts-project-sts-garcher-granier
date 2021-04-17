package fr.dut.info.rooms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import fr.dut.info.cards.Card;
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
	
	public int getSelectedCard() {
		return selectedCard;
	}
	
	public int getSelectedTarget() {
		return selectedTarget;
	}

	public void playSelected() throws IOException {
		Card card = avatar.getHand().get(selectedCard);
		card.playCard(opponents, avatar, selectedTarget);
		avatar.removeCard(card);
	}

	private void resetSelected() {
		selectedTarget = -1;
		selectedCard = -1;
	}
	
	private void statsUpdate() {
		avatar.getStats().turnUpdate();
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().getStats().turnUpdate();
		}
	}
	
	public void roomEvent(int index) throws IOException {
		if (index >= 0 && index <= avatar.getHand().size()-1) {
			selectedCard = index;
		}
		else if (index >= 5 && index <= opponents.size() + 4) {
			selectedTarget = index - 4;
		}
		else if (index == 9) {
			avatar.emptyHand();
			for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
				entry.getValue().executeMove(entry.getValue(), avatar);
			}
			avatar.drawFiveCards();
			resetSelected();
			statsUpdate();
		}
		if (cardSelected() && !(avatar.getHand().get(selectedCard).getNeedTarget())) {
			if (avatar.useEnergy(avatar.getHand().get(selectedCard).energyCost())) {
				playSelected();
			}
			resetSelected();
		}
		else if (cardSelected() && targetSelected()) {
			if (avatar.useEnergy(avatar.getHand().get(selectedCard).energyCost())) {
				playSelected();
			}
			resetSelected();
		}
	}
	
	public void deadOpponent() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			if (entry.getValue().isDead()) {
				list.add(entry.getKey());
			}
		}
		for(Integer integer : list) {
			opponents.remove(integer);
			for(int i = integer; i <= opponents.size(); i++) {
				opponents.put(i, opponents.get(i + 1));
				opponents.remove(i+1);
			}
		}
	}
	
	public boolean victory() {
		if (opponents.size() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean defeat() {
		if (avatar.isDead()) {
			return true;
		}
		return false;
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
