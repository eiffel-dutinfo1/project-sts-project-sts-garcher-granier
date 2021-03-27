package fr.dut.info.player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.Deck;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private int strength;
	private int weak;
	private final ArrayList<Card> discard;
	private final ArrayList<Card> hand;
	private final ArrayList<Card> draw;
	
	public PlayerAvatar(Player player, int maxEnergy, Deck deck) {
		this.player = player;
		this.maxEnergy = maxEnergy;
		this.energy = maxEnergy;
		this.strength = 0;
		this.weak = 0;
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		Objects.requireNonNull(deck);
		draw.addAll(deck.copyDeck());
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
	}
	
	public void emptyHand() {
		//permet de vider la main dans la defausse a la fin d'un tour
		for (Card card : hand) {
			discard.add(card);
			hand.remove(card);
		}
	}
	
	public Card selectCard() {
		this.toString();
		Scanner sc = new Scanner(System.in);
		int numCard = sc.nextInt();
		Card card = hand.get(numCard-1);
		if (card.energyCost() > energy) {
			return null;
		}
		energy -= card.energyCost();
		discard.add(card);
		hand.remove(card);
		return card;
	}
	
	public boolean takeDamage(int damage) {
		return player.takeDamage(damage);
	}
	
	public String showPlayerHP() {
		return super.toString();
	}
	
	public double weakModifier() {
		return weak > 0 ? 0.75 : 1;
	}
	
	public int getStrength() {
		return strength;
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
}
