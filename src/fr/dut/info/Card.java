package fr.dut.info;

public class Card {
	private final String name;
	private final String rarity;
	private final int energyCost;
	private final String type;
	private final int attackDamage;
	
	public Card(String name, String rarity, int energyCost, String type, int attackDamage) {
		this.name = name;
		this.rarity = rarity;
		this.energyCost = energyCost;
		this.type = type;
		this.attackDamage = attackDamage;
	}
	
	public int energyCost() {
		return energyCost;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + " - Energy: " + energyCost + " - Damage: " + attackDamage;
	}
}
