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
	private final String heroType;
	
	public Player(int maxhp, int gold, String heroType) throws IOException {
		this.maxhp = maxhp;
		this.hp = maxhp;
		this.gold = gold;
		this.heroType = heroType;
		deck = new ArrayList<Card>(); 
		this.initDeck();
	}
	
	public void initDeck() throws IOException {
		ArrayList<Card> starter = CardBuilder.getCardBuilder().getStarter();
		if (heroType.equals("IronClad")) {
			for (Card card : starter) {
				switch (card.getName()) {
				case "Strike":
					for (int i = 0; i < 5; i++) {
						deck.add(card);
					}
					break;
				case "Defend":
					for (int i = 0; i < 4; i++) {
						deck.add(card);
					}
					break;
				case "Bash":
					deck.add(card);
					break;
				default:
					throw new IllegalArgumentException("Error in starter, check .txt file.");
				}
			}
		} else {
			for (Card card : starter) {
				switch (card.getName()) {
				case "Survivor":
					deck.add(card);
					break;
				case "Strike":
					for (int i = 0; i < 5; i++) {
						deck.add(card);
					}
					break;
				case "Defend":
					for (int i = 0; i < 5; i++) {
						deck.add(card);
					}
					break;
				case "Neutralize":
					deck.add(card);
					break;
				default:
					throw new IllegalArgumentException("Error in starter, check .txt file.");
				}
			}
		}
		//deck.add(CardBuilder.getCardBuilder().fetchCard(""));
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
	
	public void giveGold(int value) {
		gold += value;
	}
	
	public void heal() {
		hp = maxhp;
	}
	
	public void heal(int x) {
		hp += x;
	}
	
	public void gainMaxHp(int x) {
		maxhp += x;
	}
}
