package fr.dut.info;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import fr.dut.info.cards.Card;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.cards.Deck;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.player.Player;
import fr.dut.info.player.PlayerAvatar;
import fr.dut.info.rooms.FightRoom;

public class Test {
	public static void main(String[] args) throws IOException {
		/*
		CardBuilder cardBuilder = CardBuilder.getCardBuilder();
		//cardBuilder.printCards();
		Player player = new Player(100, 100);
		//player.printDeck();
		PlayerAvatar avatar = new PlayerAvatar(player, 3);
		avatar.drawFiveCards();
		TreeMap<Integer, Opponent> opponents = new TreeMap<Integer, Opponent>();
		Opponent cultist = new Cultist();
		opponents.put(1, cultist);
		System.out.println(cultist);
		avatar.selectCard().playCard(opponents, avatar);
		System.out.println(cultist);
		avatar.selectCard().playCard(opponents, avatar);
		System.out.println(cultist);
		*/
		
		CardBuilder cardBuilder = CardBuilder.getCardBuilder();
		Player player = new Player(100, 100);
		Opponent cultist = new Cultist();
		FightRoom room = new FightRoom(player);
		room.addOpponent(cultist);
		room.startCombat();
		
	}
}
