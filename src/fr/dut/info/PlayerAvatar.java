package fr.dut.info;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private final ArrayList<Card> discard;
	private final ArrayList<Card> hand;
	private final ArrayList<Card> draw;
	
	public PlayerAvatar(Player player, int maxEnergy, Deck deck) {
		this.player = player;
		this.maxEnergy = maxEnergy;
		this.energy = maxEnergy;
		discard = new ArrayList<>();
		hand = new ArrayList<>();
		draw = new ArrayList<>();
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
