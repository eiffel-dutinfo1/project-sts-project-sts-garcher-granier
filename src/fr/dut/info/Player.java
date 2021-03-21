package fr.dut.info;

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
}
