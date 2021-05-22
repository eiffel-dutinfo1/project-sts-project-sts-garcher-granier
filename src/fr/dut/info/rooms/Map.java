package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.player.Player;

public class Map {
	private Room[] rooms = new Room[9];
	private Player player;
	private static String hero;
	private int currentRoom;
	//passe a true si le jouer perd tous ses pv
	private static boolean isGameOver;
	
	
	public Map() throws IOException {
		isGameOver = false;
		
		currentRoom = 0;
		
		CardBuilder cardBuilder = CardBuilder.getCardBuilder();
		
		player = new Player(100, 100);
		
		rooms[0] = new StartRoom();
		
		rooms[1] = new FightRoom("basic");
		
		int index = 2;
		int remainingBasicFightRooms = 2;
		int remainingEliteFightRooms = 1;
		int remainingFireCamps = 2;
		int remainingMerchants = 1;
		while (index < 8) {
			int number = Randomizer.randomInt(0, 4);
			switch (number) {
			case 0:
				if (remainingBasicFightRooms != 0) {
					rooms[index] = new FightRoom("basic");
					index++;
				}
				break;
			case 1:
				if (remainingEliteFightRooms != 0) {
					rooms[index] = new FightRoom("elite");
					index++;
				}
				break;
			case 2:
				if (remainingFireCamps != 0) {
					rooms[index] = new FireCamp();
					index++;
				}
				break;
			case 3:
				if (remainingMerchants != 0) {
					rooms[index] = new Merchant(cardBuilder);
					index++;
				}
				break;
			default:
				throw new IllegalArgumentException("Error in map generation.");
			}
		}
		rooms[8] = new FightRoom("boss");
	}
	
	public static void setHero(String heroType) {
		hero = heroType;
	}
	
	public void gameOver() {
		isGameOver = true;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Room getCurrentRoom() {
		return rooms[currentRoom];
	}
	
	public int getCurrentRoomIndex() {
		return currentRoom;
	}
	
	public void nextRoom() {
		currentRoom++;
	}
}
