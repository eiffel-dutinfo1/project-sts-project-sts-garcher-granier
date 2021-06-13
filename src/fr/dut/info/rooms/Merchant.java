package fr.dut.info.rooms;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.player.Player;

public class Merchant implements Room{
	
	private ArrayList<Card> shop;
	private ArrayList<Card> selectedCard;
	private boolean loaded;
	
	public Merchant() throws IOException {
		this.shop = new ArrayList<Card>();
		this.selectedCard = new ArrayList<Card>();
		loaded = false;
	}
	
	@Override
	public boolean roomEvent(int index, Player player) throws IOException {
		//this is necessary because when we generate the map and the rooms, the CardBuilder is not yet generated
		//so we must populate the cards of the merchant on entering the room (after the first click actually)
		if (loaded == false) {
			for(int i = 0; i < 8; i++) {
				shop.add(CardBuilder.getCardBuilder().giveRandomCommonCard());
			}
			shop.add(CardBuilder.getCardBuilder().giveRandomUncommonCard());
			shop.add(CardBuilder.getCardBuilder().giveRandomRareCard());
			loaded = true;
		} else {
			if(index >= 0 && index <= shop.size() - 1) {
				Card card = shop.get(index);
				if(getCardPrice(card) <= player.getGold()) {
					selectedCard.add(card);
					player.useGold(getCardPrice(card));
					shop.remove(card);
				}
			}
			if(index == 10) {
				for(Card card : selectedCard) {
					player.getDeck().add(card);
				}
				return true;
			}
		}
		return false;
	}
	
	public static int getCardPrice(Card card) {
		int price = 0;
		if(card.getRarity().equals("common")) {
			price = 45;
		}
		else if(card.getRarity().equals("uncommon")) {
			price = 68;
		}
		else if(card.getRarity().equals("rare")) {
			price = 135;
		}
		return price;
	}
	
	public ArrayList<Card> getShop() {
		return shop;
	}
	
	public boolean isLoaded() {
		return loaded;
	}

	@Override
	public String getRoomType() {
		return "Merchant";
	}
	
}
