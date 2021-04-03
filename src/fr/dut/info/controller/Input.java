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
		try(Scanner targetScan = new Scanner(System.in)) {
			for(Entry<Integer, Opponent> entry : opponents.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
			System.out.println("Choose a opponent :");
			int target = 0;
			while (targetScan.hasNextInt()) {
				target = targetScan.nextInt();
				if (target >= 1 && target <= opponents.size()) {
					break;
				}
			}
			return target;
		}
	}

	
	public static int getCard(ArrayList<Card> hand) throws IOException {
		try(Scanner cardScan = new Scanner(System.in)) {
			for(Card card : hand) {
				System.out.println(card.toString());
			}
			System.out.println("Choose a card (1 to 5) :");
			int card = 0;
			while (cardScan.hasNextInt()) {
				card = cardScan.nextInt();
				if (card >= 1 && card <= 5) {
					break;
				}
			}
			return card;
		}
	}
	
	
	public static int endTurn() {
		try(Scanner turnScan = new Scanner(System.in)) {
			System.out.println("End of your turn (0(no) / 1(yes)) :");
			int turn = 0;
			while (turnScan.hasNextInt()) {
				turn = turnScan.nextInt();
				if (turn == 0 || turn == 1) {
					break;
				}
			}
			return turn;
		}
	}
}
