package fr.dut.info.rooms;

import java.io.IOException;

import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.player.Player;

public class Map {
	private Room[] rooms = new Room[8];
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
		
		Opponent opponent1 = new JawWorm();
		Opponent opponent2 = new Cultist();
		FightRoom fr = new FightRoom();
		fr.addOpponent(opponent1);
		fr.addOpponent(opponent2);
		rooms[1] = fr;
	}
	
	public static void setHero(String heroType) {
		hero = heroType;
	}
	
	public static void gameOver() {
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
