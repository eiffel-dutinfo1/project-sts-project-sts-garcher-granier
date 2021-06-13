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
	private TreeMap<Integer, Opponent> opponents;
	private int numberOfOpponents;
	private PlayerAvatar avatar = null;
	private int selectedCard;
	private int selectedTarget;
	private int discardMode;

	public FightRoom(String monsterType) {
		this.opponents = new TreeMap<Integer, Opponent>();
		numberOfOpponents = 0;
		resetSelected();
		FightRoomBuilder.createFightRoom(this, monsterType);
		discardMode = 0;
	}
	
	//discard mode allows a player to discard specific cards in hand
	public void activateDiscardMode(int value) {
		discardMode += value;
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
	
	//play the selected card, removes its energy cost and sends the card to the discard
	public void playSelected() throws IOException {
		Card card = avatar.getHand().get(selectedCard);
		card.playCard(opponents, avatar, selectedTarget);
		avatar.useEnergy(card.energyCost());
		avatar.removeCard(card);
	}
	
	public void discardSelected() {
		Card card = avatar.getHand().get(selectedCard);
		avatar.removeCard(card);
	}

	private void resetSelected() {
		selectedTarget = -1;
		selectedCard = -1;
	}
	
	//update stats each turn
	private void statsUpdate() {
		avatar.getStats().turnUpdate();
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().getStats().turnUpdate();
		}
	}
	
	public void setAvatar(Player player) {
		if (avatar == null) {
			avatar = new PlayerAvatar(player);
			avatar.drawFiveCards();
		}
	}
	
	public boolean roomEvent(int index, Player player) throws IOException {
		if (index >= 0 && index <= avatar.getHand().size()-1) {
			selectedCard = index;
		}
		else if (index >= 5 && index <= opponents.size() + 4) {
			selectedTarget = index - 4;
		}
		//this is when the player clicks on "end turn"
		//it triggers enemy turn then starts the player turn again
		else if (index == 9) {
			avatar.emptyHand();
			for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
				entry.getValue().executeMove(entry.getValue(), avatar);
			}
			avatar.drawFiveCards();
			resetSelected();
			statsUpdate();
		}
		if (discardMode > 0) {
			discardSelected();
			resetSelected();
			discardMode--;
		} else {
			//we check if both a card and an enemy is select before playing a card if the card needs a target
			//if not, the card is played instantly
			if (cardSelected() && !(avatar.getHand().get(selectedCard).getNeedTarget())) {
				if (avatar.getEnergy() >= avatar.getHand().get(selectedCard).energyCost()) {
					playSelected();
				}
				resetSelected();
			}
			else if (cardSelected() && targetSelected()) {
				if (avatar.getEnergy() >= avatar.getHand().get(selectedCard).energyCost()) {
					playSelected();
				}
				resetSelected();
			}
		}
		deadOpponent();
		return victory();
	}
	
	//actualizes the opponents list
	public void deadOpponent() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
			if (entry.getValue().isDead()) {
				list.add(entry.getKey());
			}
		}
		for(Integer integer : list) {
			opponents.remove(integer);
			TreeMap<Integer, Opponent> newMap = new TreeMap<Integer, Opponent>();
			int count = 1;
			for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
				newMap.put(count, entry.getValue());
				count++;
			}
			opponents = newMap;
		}
	}
	
	public boolean victory() {
		if (opponents.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getRoomType() {
		return "FightRoom";
	}
}