package fr.dut.info;

import java.util.Scanner;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private final Discard discard;
	private final Hand hand;
	private final Draw draw;
	
	public PlayerAvatar(Player player, int maxEnergy, Deck deck) {
		this.player = player;
		this.maxEnergy = maxEnergy;
		this.energy = maxEnergy;
		discard = new Discard();
		hand = new Hand();
		draw = new Draw();
		draw.createDraw(deck);
	}
	
	public Hand hand() {
		return hand;
	}
	
	public Card selectCardToPlay() {
		hand.toString();
		Scanner sc = new Scanner(System.in);
		int numCard = sc.nextInt();
		Card card = hand.hand().get(numCard-1);
		if (card.energyCost() > energy) {
			return null;
		}
		hand.playOneCard(card, discard);
		energy -= card.energyCost();
		return card;
	}
}
