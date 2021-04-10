package fr.dut.info.simple;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.IOException;

import fr.dut.info.cards.CardBuilder;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.player.Player;
import fr.dut.info.rooms.FightRoom;
import fr.dut.info.rooms.Room;
import fr.dut.info.view.SimpleGameView;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class GameMainController {

	/**
	 * Notre jeu initial va être totalement simplifié : le contrôleur surveille les clics de souris
	 * et demande le rafraichissement de l'interface à chaque clic de souris.  
	 * Le jeu de termine lorsquon appuie sur une touche du clavier.
	 *  
	 * @param context
	 * @throws IOException 
	 */
	static void simpleGame(ApplicationContext context) throws IOException {
		// Le contexte nous apporte la taille de l'écran. 
		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();
	
		System.out.println("size of the screen (" + width + " x " + height + ")");

		// Pour des questions pratique on définit la taille du terrain de jeu.
		// Ce n'est pas forcément portable ni souhaitable. 
		// Vous pouvez modifier ces valeur selon la taille de votre écran.
		float gameWidth = 1920;
		float gameHeight = 1080;
		
		CardBuilder cardBuilder = CardBuilder.getCardBuilder();
		Player player = new Player(100, 100);
		Opponent opponent1 = new Cultist();
		Opponent opponent2 = new Cultist();
		FightRoom data = new FightRoom(player);
		data.getAvatar().drawFiveCards();
		System.out.println(data.getAvatar());
		data.addOpponent(opponent1);
		data.addOpponent(opponent2);
		
		SimpleGameView view = SimpleGameView.initGameGraphics(height, width, data, gameWidth, gameHeight); 
		view.draw(context);
		
		
		// Pour simplifier la présentation du code on passe dans une fonction auxiliaire. 
		GameMainController.mainLoop(context, data, view);
	}
	public static void main(String[] args) {
		//Application.run(Color.LIGHT_GRAY, GameMainController::simpleGame); // attention, utilisation d'une
																					// lambda.
		
		Application.run(Color.LIGHT_GRAY, t -> {
			try {
				simpleGame(t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		System.out.println("ne doit pas s'afficher");
	}
	
	
	
	
	/**
	 * Fonction utilisée pour clarifier la présentation.
	 * @param context  Le contexte de l'application.
	 * @param data Les données du modèle.
	 * @param view La vue.
	 * @throws IOException 
	 */
	private static void mainLoop(ApplicationContext context, FightRoom data, SimpleGameView view) throws IOException {
		while (true) {
			Event event = context.pollOrWaitEvent(20); // modifier pour avoir un affichage fluide
			if (event == null) { // Rien ne se passe. 
				continue;
			}

			Action action = event.getAction(); 
			// Touche du clavier pour terminer le jeu. 
			if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
				System.out.println("Fin du jeu");
				context.exit(0);
				throw new AssertionError("ne devrait pas arriver");
			}

			if (action != Action.POINTER_DOWN) { // si l'action n'est pas un clic de souris on attend l'événement suivant.
				continue;
			}else {
				Point2D.Float location = event.getLocation();
				int index = view.areaFromCoordinates(location.x, location.y);
				System.out.println("Area : " + index);
				// TODO : Question 5
				// Utiliser les méthodes de sélection de SimpleGameData
				// Pour sélectionner les cartes et les monstres dans le modèle.
				
				if (index >= 0 && index <= 3) {
					//data.selectCard(index-1);
				} else if (index >= 4 && index <= 5) {
					//data.selectTarget(index-4);
				}
				if (data.cardSelected() && data.targetSelected()) {
					//data.playSelected();
				}
				System.out.println("Area : " + index);
				}

			// à la fin on affiche à nouveau toute l'interface. 
			view.draw(context);
		}

	}
}
