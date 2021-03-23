package fr.dut.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Draw extends CardPile{
	private final ArrayList<Card> draw;
	
	public Draw() {
		draw = new ArrayList<Card>();
	}
	
	//fait
	public void createDraw(Deck deck) {
		Objects.requireNonNull(deck);
		draw.addAll(deck.copyDeck());
	}
	
	//fait
	public Card drawOneCard(Discard discard) {
		if (draw.isEmpty()) {
			draw.addAll(discard.emptyDiscard());
		}
		//implementer randomitude
		return draw.remove(draw.size() - 1);
	}
}
