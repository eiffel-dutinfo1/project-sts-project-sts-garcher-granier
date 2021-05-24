package fr.dut.info.rooms;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.player.Player;

public class Merchant implements Room{
	
	private ArrayList<Card> shop;
	private ArrayList<Card> selectedCard;
	private int totalPrice;
	
	public Merchant() {
		this.shop = new ArrayList<Card>();
		this.selectedCard = new ArrayList<Card>();
		for(int i = 0; i < 4; i++) {
			shop.add(CardBuilder.getCardBuilder().giveRandomCommonCard());
		}
		shop.add(CardBuilder.getCardBuilder().giveRandomCommonCard());
		shop.add(CardBuilder.getCardBuilder().giveRandomUncommonCard());
		shop.add(CardBuilder.getCardBuilder().giveRandomRareCard());
		this.totalPrice = 0;
	}

	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		if(index >= 0 && index <= shop.size() - 1) {
			Card card = shop.get(index);
			if(getCardPrice(card) <= player.getGold()) {
				selectedCard.add(card);
				totalPrice += getCardPrice(card);
				shop.remove(card);
			}
		}
		if(index == 8) {
			for(Card card : selectedCard) {
				player.getDeck().add(card);
			}
			player.useGold(totalPrice);
			return true;
		}
		return false;
	}
	
	public static int getCardPrice(Card card) {
		int price = 0;
		if(card.getRarity() == "common") {
			price = 45;
		}
		else if(card.getRarity() == "uncommon") {
			price = 68;
		}
		else if(card.getRarity() == "rare") {
			price = 135;
		}
		return price;
	}
	
	public ArrayList<Card> getShop() {
		return shop;
	}
	
}
