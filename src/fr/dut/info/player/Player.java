package fr.dut.info.player;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;

public class Player {
	private int maxhp;
	private int hp;
	private int gold;
	private final ArrayList<Card> deck;
	
	public Player(int maxhp, int gold) throws IOException {
		this.maxhp = maxhp;
		this.hp = maxhp;
		this.gold = gold;
		deck = new ArrayList<Card>(); 
		this.initDeck();
	}
	
	public void initDeck() throws IOException {
		for (int i = 0; i < 10; i++) {
			deck.add(CardBuilder.getCardBuilder().giveRandomCommonCard());
		}
	}
	
	public ArrayList<Card> copyDeck() {
		ArrayList<Card> copy = new ArrayList<>();
		for (Card card : deck) {
			copy.add(card);
		}
		return copy;
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public boolean takeDamage(int damage) {
		hp -= damage;
		if (hp <= 0) {
			//return true si le player meurt de l'attaque
			return true;
		}
		return false;
	}
	
	//pour tester
	public void printDeck() {
		for (Card card : deck) {
			System.out.println(card.toString());
		}
	}
	
	@Override
	public String toString() {
		return "HP: " + hp;
	}
	
	public int getCurrentHP() {
		return hp;
	}
	
	public int getMaxHP() {
		return maxhp;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void useGold(int value) {
		gold -= value;
	}
	
	public void heal() {
		hp = maxhp;
	}
}
