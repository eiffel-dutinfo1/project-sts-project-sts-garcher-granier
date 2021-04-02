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
	private final boolean exhaustable;
	private boolean exhausted;
	private final ArrayList<Strat> strategies;
	
	public Card(String name, int energyCost, String rarity, boolean exhaustable) {
		this.name = Objects.requireNonNull(name);
		this.energyCost = energyCost;
		this.rarity = Objects.requireNonNull(rarity);
		this.exhaustable = exhaustable;
		//par défaut, une carte commence non exhausted car non jouée
		exhausted = false;
		strategies = new ArrayList<Strat>();
	}
	
	public void addStrat(Strat strat) {
		strategies.add(strat);
	}
	
	public int energyCost() {
		return energyCost;
	}
	
	public Card makeCopy() throws CloneNotSupportedException {
		return (Card) this.clone();
	}
	
	public void playCard(TreeMap<Integer, Opponent> opponents, PlayerAvatar avatar) throws IOException {
		for (Strat strat : strategies) {
			strat.useStrat(opponents, avatar);
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
}