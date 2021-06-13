package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.Randomizer;
import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.player.Player;

public class Map {
	private static Room[] rooms = new Room[14];
	private static Player player;
	private static String hero;
	private static int currentRoom;
	//passe a true si le jouer perd tous ses pv
	private static boolean isGameOver;
	
	
	public Map() throws IOException {
		isGameOver = false;
		
		currentRoom = 0;
		
		//"room" where the player selects its hero
		rooms[0] = new StartRoom();
		
		//hard coded as first room because a merchant or a firecamp would be awkward
		rooms[1] = new FightRoom("basic");
		
		//after each FightRoom we put a RewardRoom
		rooms[2] = new RewardRoom("basic");
		
		int index = 3;
		//we list all other rooms remaining waiting to be placed inside the map
		int remainingBasicFightRooms = 2;
		int remainingEliteFightRooms = 1;
		int remainingFireCamps = 2;
		int remainingMerchants = 1;
		//we randomize the map sequence
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
		//boss fight is hardcoded as last true room
		rooms[12] = new FightRoom("boss");
		rooms[13] = new WinScreen();
	}
	
	//used to generate the correct CardBuilder depending on the selected hero
	public static void setHero(String heroType) throws IOException {
		hero = heroType;
		CardBuilder cardBuilder = CardBuilder.getCardBuilder(heroType);
		if (hero.equals("IronClad")) {
			//these values are for testing purpose, game is far too hard otherwise
			player = new Player(800, 10000, "IronClad");
		} else {
			player = new Player(700, 10000, "Silent");
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
