package fr.dut.info.player;

import fr.dut.info.cards.Deck;

public class Player {
	private int maxhp;
	private int hp;
	private int gold;
	private final Deck deck;
	
	public Player(int maxhp, int gold, Deck deck) {
		this.maxhp = maxhp;
		this.hp = maxhp;
		this.gold = gold;
		this.deck = deck; 
	}
	
	public boolean takeDamage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			//return true si le player meurt de l'attaque
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "HP: " + hp;
	}
}
