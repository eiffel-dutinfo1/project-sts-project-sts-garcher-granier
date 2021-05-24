package fr.dut.info.cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.strategies.Strat;
import fr.dut.info.cards.strategies.StratBuilder;

public class CardBuilder {
	private static CardBuilder instance = null;
	private static ArrayList<Card> starterCards;
	private static ArrayList<Card> commonCards;
	private static ArrayList<Card> uncommonCards;
	private static ArrayList<Card> rareCards;
	private static ArrayList<Card> specialCards;
	
	private CardBuilder() throws IOException {
		Path commonPath = FileSystems.getDefault().getPath("resources", "colorless_cards.txt");
		//commonCards = this.cardExtractor(commonPath, "common");
		commonCards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> cardExtractor(Path path, String rarity) throws IOException {
		ArrayList<Card> cards = new ArrayList<Card>();
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("#");
				String name = data[0];
				String picturePath = "resources/pictures/" + data[1];
				int cost = Integer.valueOf(data[2]);
				boolean exhaustable = Boolean.valueOf(data[3]);
				boolean needTarget = Boolean.valueOf(data[4]);
				Card card = new Card(name, cost, rarity, picturePath, exhaustable, needTarget);
				int numberOfStrats = (data.length-5);
				for (int i = 0; i < numberOfStrats; i = i + 2) {
					Strat strat = StratBuilder.createStrat(data[i + 5], Integer.valueOf(data[i + 6]));
					card.addStrat(strat);
				}
				cards.add(card);
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
		return commonCards.get(Randomizer.randomInt(0, commonCards.size()));
	}
	
	public static CardBuilder getCardBuilder() throws IOException {
		if (instance == null) {
			instance = new CardBuilder();
		}
		return instance;
	}
}
