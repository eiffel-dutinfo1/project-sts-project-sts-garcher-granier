package fr.dut.info.cards;

import java.util.HashSet;

import fr.dut.info.cards.colorless.*;

public abstract class CardBuilder {
	private static CardBuilder instance = null;
	private static final Card[] commonCards = {new SwiftStrike()};
	private static final HashSet<Card> uncommonCards;
	private static final HashSet<Card> rareCards;
	
	private CardBuilder() {}
	
	public static CardBuilder getCardBuilder() {
		if (instance == null) {
			instance = new CardBuilder();
		}
		return instance;
	}
	
	public abstract Card createCard();
}
