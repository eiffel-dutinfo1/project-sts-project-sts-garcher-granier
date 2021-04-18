package fr.dut.info.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.TreeSet;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.cards.strategies.StratBuilder;

public class CardBuilder {
	private static CardBuilder instance = null;
	//important !!! determiner a l'avance le nombre de cartes dans le tableau
	//a priori �a bouge pas
	private static final int totalCommonCards = 5;
	private static Card[] commonCards = new Card[totalCommonCards];
	
	private CardBuilder() throws IOException {
		Path commonPath = FileSystems.getDefault().getPath("resources", "common_cards.txt");
		commonCards = this.cardExtractor(commonPath, "common");
	}
	
	public Card[] cardExtractor(Path path, String rarity) throws IOException {
		Card[] cards = new Card[totalCommonCards];
		int cardNumber = 0;
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("#");
				String name = data[0];
				String picturePath = "resources/pictures/" + data[1];
				int cost = Integer.valueOf(data[2]);
				boolean exhaustable = Boolean.valueOf(data[3]);
				boolean needTarget = Boolean.valueOf(data[4]);
				Card card = new Card(name, cost, "common", picturePath, exhaustable, needTarget);
				int numberOfStrats = (data.length-5);
				for (int i = 0; i < numberOfStrats; i = i + 2) {
					Strat strat = StratBuilder.createStrat(data[i + 5], Integer.valueOf(data[i + 6]));
					card.addStrat(strat);
				}
				cards[cardNumber] = card;
				cardNumber++;
			}
		}
		return cards;
	}
	
	public void printCards() {
		for (Card card : commonCards) {
			System.out.println(card);
		}
	}
	
	public Card giveRandomCommonCard() {
		return commonCards[Randomizer.randomInt(0, totalCommonCards)];
	}
	
	public static CardBuilder getCardBuilder() throws IOException {
		if (instance == null) {
			instance = new CardBuilder();
		}
		return instance;
	}
}
