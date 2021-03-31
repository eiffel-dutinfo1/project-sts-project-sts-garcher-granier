package fr.dut.info.cards;

import java.util.HashSet;
import java.util.TreeSet;

public class CardBuilder {
	private static CardBuilder instance = null;
	private static TreeSet<Card> commonCards;
	private static TreeSet<Card> uncommonCards;
	private static TreeSet<Card> rareCards;
	
	private CardBuilder() {
		commonCards = new TreeSet<>();
		uncommonCards = new TreeSet<>();
		rareCards = new TreeSet<>();
	}
	
	public static CardBuilder getCardBuilder() {
		if (instance == null) {
			instance = new CardBuilder();
		}
		return instance;
	}
	
}
