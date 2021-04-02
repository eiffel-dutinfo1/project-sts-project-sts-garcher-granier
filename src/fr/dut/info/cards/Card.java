package fr.dut.info.cards;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public abstract class AbstractCard implements Card{
	private final String name;
	private final int energyCost;
	private final String rarity;
	private final boolean exhaustable;
	private boolean exhausted;
	private final HashMap<String, Integer> strategies;
	
	public AbstractCard(String name, int energyCost, String rarity, boolean exhaustable) {
		this.name = Objects.requireNonNull(name);
		this.energyCost = energyCost;
		this.rarity = Objects.requireNonNull(rarity);
		this.exhaustable = exhaustable;
		//par défaut, une carte commence non exhausted car non jouée
		exhausted = false;
		
	}
	
	@Override
	public int energyCost() {
		return energyCost;
	}
	
	public void playCard(TreeMap<Integer, Opponent> opponents, PlayerAvatar avatar) {
		for (Entry<String, Integer> entry : strategies.entrySet()) {
			CardStrat.useCardStrat(entry.getKey(), entry.getValue(), opponents, avatar);
		}
	};
}