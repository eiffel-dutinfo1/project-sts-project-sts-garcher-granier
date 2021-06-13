package fr.dut.info.cards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class Card implements Comparable<Card> {
	private final String name;
	private final int energyCost;
	private final String rarity;
	private final String cardType;
	private final String picturePath;
	private final boolean exhaustable;
	private boolean exhausted;
	private final ArrayList<Strat> strategies;
	private final boolean needTarget;
	
	public Card(String name, int energyCost, String rarity, String picturePath, boolean exhaustable, boolean needTarget, String cardType) {
		this.name = Objects.requireNonNull(name);
		this.energyCost = energyCost;
		this.rarity = Objects.requireNonNull(rarity);
		this.picturePath = Objects.requireNonNull(picturePath);
		this.exhaustable = exhaustable;
		this.needTarget = needTarget;
		this.cardType = cardType;
		//par d�faut, une carte commence non exhausted car non jou�e
		exhausted = false;
		strategies = new ArrayList<Strat>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	
	public void addStrat(Strat strat) {
		Objects.requireNonNull(strat);
		strategies.add(strat);
	}
	
	public int energyCost() {
		return energyCost;
	}
	
	public boolean getNeedTarget() {
		return needTarget;
	}
	
	public boolean isExhaustable() {
		return exhaustable;
	}
	
	//unused
	public Card makeCopy() throws CloneNotSupportedException {
		return (Card) this.clone();
	}
	
	//plays each strategy of the card subsequently
	public void playCard(TreeMap<Integer, Opponent> opponents, PlayerAvatar avatar, int target) throws IOException {
		for (Strat strat : strategies) {
			strat.useStrat(opponents, avatar, target);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("---------\n");
		sb.append(name).append("\n");
		sb.append(energyCost).append(" energy\n");
		sb.append(rarity).append("\n");
		for (Strat strat : strategies) {
			sb.append(strat.toString()).append("\n");
		}
		sb.append("---------\n");
		return sb.toString();
	}
	
	@Override
	public int compareTo(Card card) {
		return name.compareTo(card.name);
	}
	
	public String getRarity() {
		return rarity;
	}
	
	public String getCardType() {
		return cardType;
	}
}