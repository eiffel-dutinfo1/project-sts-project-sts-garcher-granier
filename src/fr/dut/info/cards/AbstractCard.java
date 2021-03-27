package fr.dut.info.cards;

import java.util.Objects;

public abstract class AbstractCard implements Card{
	private final String name;
	private final int energyCost;
	private final String rarity;
	
	public AbstractCard(String name, int energyCost, String rarity) {
		this.name = Objects.requireNonNull(name);
		this.energyCost = energyCost;
		this.rarity = Objects.requireNonNull(rarity);
	}
	
	@Override
	public int energyCost() {
		return energyCost;
	}
}
