package fr.dut.info.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import fr.dut.info.cards.Card;
import fr.dut.info.monsters.Opponent;

public class Input {

	public static int getTarget(TreeMap<Integer, Opponent> opponents) throws IOException {
		Scanner targetScan = new Scanner(System.in);
		for (Entry<Integer, Opponent> entry : opponents.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println("Choose a opponent :");
		int target = 0;
		target = targetScan.nextInt();
		return target;
	}

	public static int getCard(ArrayList<Card> hand) {
		Scanner cardScan = new Scanner(System.in);
		for (Card card : hand) {
			System.out.println(card.toString());
		}
		System.out.println("Choose a card (1 to 5) :");
		int cardNumber = 0;
		cardNumber = cardScan.nextInt();
		return cardNumber;
	}

	public static int endTurn() {
		Scanner turnScan = new Scanner(System.in);
		System.out.println("End of your turn (0(no) / 1(yes)) :");
		int turn = 0;
		turn = turnScan.nextInt();
		return turn;
	}
}
