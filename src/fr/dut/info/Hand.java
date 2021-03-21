package fr.dut.info;

import java.util.ArrayList;

public class Hand extends CardPile{
	private final ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public ArrayList<Card> hand() {
		return hand;
	}
	
	public void drawCards(Draw draw, Discard discard) {
		for (int i = 0; i < 5; i++) {
			hand.add(draw.drawOneCard(discard));
		}
	}
	
	public void emptyHand(Discard discard) {
		for (Card card : hand) {
			discard.cardToDiscard(card);
			hand.remove(card);
		}
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
	
	public void playOneCard(Card card, Discard discard) {
		//on part du principe que la carte peut etre jouee et que ça a été vérifié en amont
		discard.cardToDiscard(card);
		hand.remove(card);
	}
}
