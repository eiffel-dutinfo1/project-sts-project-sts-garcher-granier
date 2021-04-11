package fr.dut.info.cards;

import java.util.ArrayList;
import java.util.Objects;

//classe qui sert a rien pour le moment
public class Deck {
	private final ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>();
	}
	
	public void addToDeck(Card card) {
		Objects.requireNonNull(card);
		deck.add(card);
	}
	
	public ArrayList<Card> copyDeck() {
		ArrayList<Card> copy = new ArrayList<>();
		for (Card card : deck) {
			copy.add(card);
		}
		return copy;
	}
}
