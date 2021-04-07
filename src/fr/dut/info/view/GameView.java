package fr.dut.info.view;

import java.awt.Graphics2D;
import fr.umlv.zen5.ApplicationContext;

/**
 * 
 * Cette interface est reprise de celle qui est fournie avec le projet Slay The Spire. 
 * 
 * Elle reste très simplifiée, et modélise le comportement visible de la classe SimpleGameView.
 * 
 * Vous n'avez pas à modifier cette interface dans le cadre du TD.
 * 
 *	@author Christophe Morvan
 */
public interface GameView {
	/**
	 * Transforms a real pair of x and y coordinates into the index of visual element.
	 * 
	 * @param x a float x-coordinate
	 * @param y a float y-coordinate
	 * @return the index of the active element, null if outside
	 * @throws IllegalArgumentException if the float coordinate doesn't fit in the
	 *                                  game board.
	 */
	public int areaFromCoordinates(float x, float y);

	/**
	 * Draws the game board from its data, using an existing Graphics2D object.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
	public void draw(Graphics2D graphics);

	/**
	 * Draws the game board from its data, using an existing
	 * {@code ApplicationContext}.
	 * 
	 * @param context the {@code ApplicationContext} of the game
	 * @param data    the GameData containing the game data.
	 */
	public default void draw(ApplicationContext context) {
		context.renderFrame(graphics -> draw(graphics));
	}
}
