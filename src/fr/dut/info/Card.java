package fr.dut.info;

public class Card {
	private final String name;
	private final String rarity;
	private final int energyCost;
	private final String type;
	public Card(String name, String rarity, int energyCost, String type) {
		this.name = name;
		this.rarity = rarity;
		this.energyCost = energyCost;
		this.type = type;
	}
	
}
