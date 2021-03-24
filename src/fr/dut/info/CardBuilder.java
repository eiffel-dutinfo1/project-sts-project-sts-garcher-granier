package fr.dut.info;

import java.util.HashSet;

public abstract class CardBuilder {
	private static CardBuilder instance = null;
	private static final Card[] commonCards = {};
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
