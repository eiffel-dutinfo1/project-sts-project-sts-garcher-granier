package fr.dut.info.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.Deck;
import fr.dut.info.controller.Input;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private int block;
	private int strength;
	private int weak;
	private final ArrayList<Card> discard;
	private final ArrayList<Card> hand;
	private final ArrayList<Card> draw;
	
	public PlayerAvatar(Player player) {
		this.player = player;
		this.maxEnergy = 3;
		this.energy = maxEnergy;
		this.strength = 0;
		this.weak = 0;
		this.block = 0;
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		Objects.requireNonNull(player.getDeck());
		draw.addAll(player.copyDeck());
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public int getCurrentHP() {
		return player.getCurrentHP();
	}
	
	public int getMaxHP() {
		return player.getMaxHP();
	}
	
	public int getCurrentBlock() {
		return block;
	}
	
	public Card drawOneCard() {
		//si la pioche est vide, on la remplit avec la defausse puis on vide la defausse
		if (draw.isEmpty()) {
			draw.addAll(discard);
			discard.removeAll(discard);
		}
		//implementer randomitude
		return draw.remove(draw.size() - 1);
	}
	
	public void drawFiveCards() {
		//remplit la main de 5 cartes
		for (int i = 0; i < 5; i++) {
			hand.add(drawOneCard());
		}
	}
	
	public void emptyHand() {
		//permet de vider la main dans la defausse a la fin d'un tour
		for (Card card : hand) {
			discard.add(card);
			hand.remove(card);
		}
	}
	
	public Card selectCard() throws IOException {
		int numCard = Input.getCard(hand);
		Card card = hand.get(numCard-1);
		if (card.energyCost() > energy) {
			return null;
		}
		energy -= card.energyCost();
		discard.add(card);
		hand.remove(card);
		return card;
	}
	
	public boolean takeDamage(int damage) {
		return player.takeDamage(damage);
	}
	
	public void applyWeak(int value) {
		weak += value;
	}
	
	public void giveBlock(int value) {
		block += value;
	}
	
	public String showPlayerHP() {
		return super.toString();
	}
	
	public double weakModifier() {
		return weak > 0 ? 0.75 : 1;
	}
	
	public int getStrength() {
		return strength;
	}
	
	@Override
	public String toString() {
		StringBuilder display = new StringBuilder("Cards in hand:\n");
		int counter = 1;
		for (Card card : hand) {
			display.append(counter).append(" - ").append(card.toString()).append("\n");
			counter++;
		}
		return display.toString();
	}
	
	public boolean isDead() {
		if (player.getCurrentHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
