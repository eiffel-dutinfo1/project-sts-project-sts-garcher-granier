package fr.dut.info.rooms;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.player.Player;

public class RewardRoom implements Room{
	private final ArrayList<Card> rewards;
	private final int gold;
	
	public RewardRoom(String roomDifficulty) throws IOException {
		//give specific gold amounts depeding on the room difficulty
		if (roomDifficulty.equals("elite")) {
			gold = Randomizer.randomInt(30, 46);
		} else {
			gold = Randomizer.randomInt(15, 21);
		}
		rewards = new ArrayList<Card>();
	}
	
	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		if (rewards.size()==0) {
			//grant player his gold
			player.giveGold(gold);
			int random;
			//give a choice of 3 random cards of random rarities, the rarest being... rare
			for (int i = 0; i < 3; i++) {
				random = Randomizer.randomInt(0, 101);
				if (random<70) {
					rewards.add(CardBuilder.getCardBuilder().giveRandomCommonCard());
				} else if (random < 90) {
					rewards.add(CardBuilder.getCardBuilder().giveRandomUncommonCard());
				} else {
					rewards.add(CardBuilder.getCardBuilder().giveRandomRareCard());
				}
			}
		} else {
			//give to the player the selected card and go on to the next room
			if (index == 0) {
				player.getDeck().add(rewards.get(0));
				return true;
			} else if (index == 1) {
				player.getDeck().add(rewards.get(1));
				return true;
			} else if (index == 2) {
				player.getDeck().add(rewards.get(2));
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Card> getRewards() {
		return rewards;
	}

	@Override
	public String getRoomType() {
		return "Reward";
	}

}
