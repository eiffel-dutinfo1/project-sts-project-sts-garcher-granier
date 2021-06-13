package fr.dut.info.cards;

import java.util.ArrayList;
import java.util.Objects;

//a deck is simply a list of cards possessed by the Player class
public class Deck {
	private final ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>();
	}
	
	public void addToDeck(Card card) {
		Objects.requireNonNull(card);
		deck.add(card);
	}
	
	//unused
	public ArrayList<Card> copyDeck() {
		ArrayList<Card> copy = new ArrayList<>();
		for (Card card : deck) {
			copy.add(card);
		}
		return copy;
	}
}
