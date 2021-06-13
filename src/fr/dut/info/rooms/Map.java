package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.player.Player;

public class Map {
	private static Room[] rooms = new Room[13];
	private static Player player;
	private static String hero;
	private static int currentRoom;
	//passe a true si le jouer perd tous ses pv
	private static boolean isGameOver;
	
	
	public Map() throws IOException {
		isGameOver = false;
		
		currentRoom = 0;
		
		rooms[0] = new StartRoom();
		
		rooms[1] = new FightRoom("basic");
		rooms[1] = new FightRoom("boss");
		
		rooms[2] = new RewardRoom("basic");
		
		int index = 3;
		int remainingBasicFightRooms = 2;
		int remainingEliteFightRooms = 1;
		int remainingFireCamps = 2;
		int remainingMerchants = 1;
		while (index < 12) {
			int number = Randomizer.randomInt(0, 4);
			switch (number) {
			case 0:
				if (remainingBasicFightRooms != 0) {
					rooms[index] = new FightRoom("basic");
					index++;
					rooms[index] = new RewardRoom("basic");
					index++;
				}
				break;
			case 1:
				if (remainingEliteFightRooms != 0) {
					rooms[index] = new FightRoom("elite");
					index++;
					rooms[index] = new RewardRoom("elite");
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
					rooms[index] = new Merchant();
					index++;
				}
				break;
			default:
				throw new IllegalArgumentException("Error in map generation.");
			}
		}
		rooms[12] = new FightRoom("boss");
	}
	
	public static void setHero(String heroType) throws IOException {
		hero = heroType;
		CardBuilder cardBuilder = CardBuilder.getCardBuilder(heroType);
		if (hero.equals("IronClad")) {
			player = new Player(800, 100, "IronClad");
		} else {
			player = new Player(700, 100, "Silent");
		}
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
	
	public static Room getCurrentRoom() {
		return rooms[currentRoom];
	}
	
	public int getCurrentRoomIndex() {
		return currentRoom;
	}
	
	public void nextRoom() {
		currentRoom++;
	}
}
