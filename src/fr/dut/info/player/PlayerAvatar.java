package fr.dut.info.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.Card;
import fr.dut.info.cards.Deck;
import fr.dut.info.stats.Stats;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private final Stats stats;
	private final ArrayList<Card> discard;
	private ArrayList<Card> hand;
	private final ArrayList<Card> draw;
	
	public PlayerAvatar(Player player) {
		this.player = player;
		this.maxEnergy = 3;
		this.energy = maxEnergy;
		stats = new Stats();
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		draw = new ArrayList<Card>();
		Objects.requireNonNull(player.getDeck());
		draw.addAll(player.copyDeck());
		Collections.shuffle(draw);
	}
	
	public void addToDiscard(Card card) {
		discard.add(card);
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void gainEnergy(int x) {
		energy += x;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public ArrayList<Card> getDiscard() {
		return discard;
	}
	
	public int getCurrentHP() {
		return player.getCurrentHP();
	}
	
	public int getMaxHP() {
		return player.getMaxHP();
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public Card drawOneCard() {
		//if the draw is empty, we fill it with the discard then we empty the discard
		if (draw.isEmpty()) {
			draw.addAll(discard);
			discard.removeAll(discard);
		}
		return draw.remove(draw.size() - 1);
	}
	
	public void drawFiveCards() {
		Collections.shuffle(discard);
		//fill the hand with 5 random cards from draw
		for (int i = 0; i < 5; i++) {
			hand.add(drawOneCard());
		}
		energy = maxEnergy;
	}
	
	public void emptyHand() {
		//empties hand into discard at the end of a turn
		for (Card card : hand) {
			discard.add(card);
		}
		hand = new ArrayList<Card>();
	}
	
	public void removeCard(Card card) {
		//remove a card from the hand and puts it in the discard
		//if the card is exhaustable, card is sent into the nether instead, never to be seen again in this fightroom
		if (!card.isExhaustable()) {
			discard.add(card);
		}
		hand.remove(card);
	}
	
	//specific card removal in case of player initiated discard (example : Silent several cards) to not send the card to the nether
	public void removeCardNoExhaust(Card card) {
		discard.add(card);
		hand.remove(card);
	}
	
	//remove from discard to give to hand
	public void reverseRemoveCard(Card card) {
		draw.add(card);
		discard.remove(card);
	}
	
	//removes a card from hand to give to draw
	public Card cardHandToDraw() {
		int nb = Randomizer.randomInt(0, hand.size());
		Card card = hand.get(nb);
		draw.add(card);
		hand.remove(nb);
		return card;
	}
	
	//this shuffles the cards, surprisingly
	public void shuffleHand() {
		Collections.shuffle(hand);
	}
	
	//ouch
	public boolean takeDamage(int damage) {
		return player.takeDamage(damage);
	}
	
	public String showPlayerHP() {
		return super.toString();
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
	
	//returns true if the player is dead (hp < 0)
	public boolean isDead() {
		if (player.getCurrentHP() <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void useEnergy(int i) {
		energy -= i;
	}
	
	public void heal(int x) {
		player.heal(x);
	}
	
	public void gainGold(int x) {
		player.giveGold(x);
	}
	
	public void gainMaxHp(int x) {
		player.gainMaxHp(x);
	}
}
