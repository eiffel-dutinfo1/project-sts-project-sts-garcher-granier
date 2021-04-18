package fr.dut.info.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.Deck;
import fr.dut.info.stats.Stats;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private final Stats stats;
	private final ArrayList<Card> discard;
	private ArrayList<Card> hand;
	private final ArrayList<Card> draw;
	
	public PlayerAvatar(Player player) {
		this.player = player;
		this.maxEnergy = 3;
		this.energy = maxEnergy;
		stats = new Stats();
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		Objects.requireNonNull(player.getDeck());
		draw.addAll(player.copyDeck());
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getCurrentHP() {
		return player.getCurrentHP();
	}
	
	public int getMaxHP() {
		return player.getMaxHP();
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public Card drawOneCard() {
		//si la pioche est vide, on la remplit avec la defausse puis on vide la defausse
		if (draw.isEmpty()) {
			draw.addAll(discard);
			discard.removeAll(discard);
		}
		//implementer randomitude
		return draw.remove(draw.size() - 1);
	}
	
	public void drawFiveCards() {
		//remplit la main de 5 cartes
		for (int i = 0; i < 5; i++) {
			hand.add(drawOneCard());
		}
		energy = maxEnergy;
	}
	
	public void emptyHand() {
		//permet de vider la main dans la defausse a la fin d'un tour
		for (Card card : hand) {
			discard.add(card);
		}
		hand = new ArrayList<Card>();
	}
	
	public void removeCard(Card card) {
		discard.add(card);
		hand.remove(card);
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
	}
	
	public boolean takeDamage(int damage) {
		return player.takeDamage(damage);
	}
	
	public String showPlayerHP() {
		return super.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder display = new StringBuilder("Cards in hand:\n");
		int counter = 1;
		for (Card card : hand) {
			display.append(counter).append(" - ").append(card.toString()).append("\n");
			counter++;
		}
		return display.toString();
	}
	
	public boolean isDead() {
		if (player.getCurrentHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void useEnergy(int i) {
		energy -= i;
	}
}
