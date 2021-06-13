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
	
	private CardBuilder(String heroType) throws IOException {
		Path colorlessPath = FileSystems.getDefault().getPath("resources", "colorless_cards.txt");
		Path ironcladPath = FileSystems.getDefault().getPath("resources", "ironclad_cards.txt");
		Path silentPath = FileSystems.getDefault().getPath("resources", "silent_cards.txt");
		starterCards = new ArrayList<Card>();
		commonCards = new ArrayList<Card>();
		uncommonCards = new ArrayList<Card>();
		rareCards = new ArrayList<Card>();
		specialCards = new ArrayList<Card>();
		this.cardExtractor(colorlessPath);
		if (heroType.equals("IronClad")) {
			this.cardExtractor(ironcladPath);
		} else {
			this.cardExtractor(silentPath);
		}
	}
	
	public ArrayList<Card> cardExtractor(Path path) throws IOException {
		ArrayList<Card> cards = new ArrayList<Card>();
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split("#");
				String name = data[0];
				String picturePath = "resources/pictures/" + data[1];
				String rarity = data[2];
				int cost = Integer.valueOf(data[3]);
				boolean exhaustable = Boolean.valueOf(data[4]);
				boolean needTarget = Boolean.valueOf(data[5]);
				String cardType = data[6];
				Card card = new Card(name, cost, rarity, picturePath, exhaustable, needTarget, cardType);
				int numberOfStrats = (data.length-7);
				for (int i = 0; i < numberOfStrats; i = i + 2) {
					Strat strat = StratBuilder.createStrat(data[i + 7], Integer.valueOf(data[i + 8]));
					card.addStrat(strat);
				}
				switch (rarity) {
				case "starter":
					starterCards.add(card);
					break;
				case "common":
					commonCards.add(card);
					break;
				case "uncommon":
					uncommonCards.add(card);
					break;
				case "rare":
					rareCards.add(card);
					break;
				case "special":
					specialCards.add(card);
					break;
				default:
					throw new IllegalArgumentException("Rarity is not recognized, please verify the .txt file");
				}
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
	
	public Card giveRandomUncommonCard() {
		return uncommonCards.get(Randomizer.randomInt(0, commonCards.size()));
	}
	
	public Card giveRandomRareCard() {
		return rareCards.get(Randomizer.randomInt(0, commonCards.size()));
	}
	
	public ArrayList<Card> getStarter() {
		return starterCards;
	}
	
	public static CardBuilder getCardBuilder() throws IOException {
		if (instance == null) {
			throw new IllegalArgumentException("CardBuilder should have been initialized by now.");
		}
		return instance;
	}
	
	public static CardBuilder getCardBuilder(String heroType) throws IOException {
		if (instance == null) {
			instance = new CardBuilder(heroType);
		}
		return instance;
	}
}
