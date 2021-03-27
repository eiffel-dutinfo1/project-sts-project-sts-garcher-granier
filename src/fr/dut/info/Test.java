package fr.dut.info;

import java.util.Iterator;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.Deck;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;

public class Test {
	public static void main(String[] args) {
		Card c0 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c1 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c2 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c3 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c4 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c5 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c6 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c7 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c8 = new Card("Basic attack", "common", 1, "attack", 5);
		Card c9 = new Card("Basic attack", "common", 1, "attack", 5);
		Deck d1 = new Deck();
		d1.addToDeck(c0);
		d1.addToDeck(c1);
		d1.addToDeck(c2);
		d1.addToDeck(c3);
		d1.addToDeck(c4);
		d1.addToDeck(c5);
		d1.addToDeck(c6);
		d1.addToDeck(c7);
		d1.addToDeck(c8);
		d1.addToDeck(c9);
		Player p1 = new Player(100, 100, d1);
		PlayerAvatar a1 = new PlayerAvatar(p1, 3, d1);
		
		System.out.println(a1);
		a1.drawFiveCards();
		System.out.println(a1);
	}
}
