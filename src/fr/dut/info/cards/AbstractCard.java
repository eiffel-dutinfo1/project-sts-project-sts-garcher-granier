package fr.dut.info.cards;

import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

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
	
	public void dealDamage(Opponent opponent, int damageNumber, PlayerAvatar playerAvatar) {
		opponent.takeDamage((damageNumber + playerAvatar.getStrength()) * playerAvatar.weakModifier());
	}
	
	public void dealGroupDamage(TreeMap<Integer, Opponent> opponents, int damageNumber) {
		for (Entry<Integer, Opponent> entry : opponents.entrySet()) {
			entry.getValue().takeDamage();
		}
	}
	
	public void dealSingleTargetDamage() {
		
	}
}
