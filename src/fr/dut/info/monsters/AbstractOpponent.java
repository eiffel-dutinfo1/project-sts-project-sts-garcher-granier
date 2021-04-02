package fr.dut.info.monsters;

import fr.dut.info.player.PlayerAvatar;

public abstract class AbstractOpponent implements Opponent{
	private int hp;
	private int strength;
	private int weak;
	private int block;
	
	public AbstractOpponent(int hp) {
		this.hp = hp;
		this.strength = 0;
		this.weak = 0;
		this.block = 0;
	}
	
	@Override
	public boolean takeDamage(int value) {
		hp -= value;
		return hp <= 0;
	}

	public void dealDamage(PlayerAvatar playerAvatar, int damage) {
		if (weak == 0) {
			playerAvatar.takeDamage(damage + strength);
			return;
		}
		playerAvatar.takeDamage((int) ((damage + strength) * 0.75));
	}
	
	public void dealBlock(int x) {
		block += x;
	}
	
	public void dealStrength(int x) {
		strength += x;
	}
	
	@Override
	public String toString() {
		return "HP left :" + hp + "\n";
	}
}
