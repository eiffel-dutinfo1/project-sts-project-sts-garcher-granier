package fr.dut.info;

import java.io.IOException;
import java.util.Iterator;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.cards.Deck;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;

public class Test {
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		CardBuilder cardBuilder = CardBuilder.getCardBuilder();
		//cardBuilder.printCards();
		Player player = new Player(100, 100);
		player.printDeck();
		PlayerAvatar avatar = new PlayerAvatar(player, 3);
		
	}
}
