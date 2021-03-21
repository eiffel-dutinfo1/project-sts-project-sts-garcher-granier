package fr.dut.info;

import java.util.ArrayList;
import java.util.Objects;

public class Discard extends CardPile{
	private final ArrayList<Card> discard;
	
	public Discard() {
		discard = new ArrayList<Card>();
	}
	
	public ArrayList<Card> emptyDiscard() {
		ArrayList<Card> copy = new ArrayList<>();
		for (Card card : discard) {
			copy.add(card);
		}
		discard.removeAll(discard);
		return copy;
	}
	
	public void cardToDiscard(Card card) {
		Objects.requireNonNull(card);
		discard.add(card);
	}
}
