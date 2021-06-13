package fr.dut.info.controller;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;

import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.player.Player;
import fr.dut.info.rooms.FightRoom;
import fr.dut.info.rooms.Map;
import fr.dut.info.rooms.Room;
import fr.dut.info.view.SimpleGameView;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class GameMainController {

	static void simpleGame(ApplicationContext context) throws IOException {
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
	
		System.out.println("size of the screen (" + width + " x " + height + ")");

		float gameWidth = width;
		float gameHeight = height;
		
		Map data = new Map();
		
		SimpleGameView view = SimpleGameView.initGameGraphics(height, width, data, gameWidth, gameHeight); 
		view.draw(context);

		GameMainController.mainLoop(context, data, view);
	}
	public static void main(String[] args) {
		Application.run(Color.LIGHT_GRAY, t -> {
			try {
				simpleGame(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		System.out.println("ne doit pas s'afficher");
	}
	
	private static void mainLoop(ApplicationContext context, Map data, SimpleGameView view) throws IOException {
		while (true) {
			Event event = context.pollOrWaitEvent(20); // modify for better framerate
			if (event == null) { // nothing happens 
				continue;
			}

			Action action = event.getAction(); 
			// press and keyboard key to end the game 
			if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
				System.out.println("Fin du jeu");
				context.exit(0);
				throw new AssertionError("ne devrait pas arriver");
			}

			if (action != Action.POINTER_DOWN) { // if the action is not a click, go to next event
				continue;
			}else {
				Point2D.Float location = event.getLocation();
				//collect click coordinates information
				int index = view.areaFromCoordinates(location.x, location.y);
				//if roomEvent returns true, that mean the room is done and the player can go to the next room
				if (data.getCurrentRoom().roomEvent(index, data.getPlayer())) {
					data.nextRoom();
					if (data.getCurrentRoom().getRoomType().equals("FightRoom")) {
						//only really useful once in the program to set the hero type (IronClad or Silent)
						((FightRoom) data.getCurrentRoom()).setAvatar(data.getPlayer());;
					}
				}
				//if the players dies, displays the game over screen
				if (data.getPlayer().getCurrentHP() <= 0) {
					data.gameOver();
				}
			}

			// display UI
			view.draw(context);
		}
	}
}
