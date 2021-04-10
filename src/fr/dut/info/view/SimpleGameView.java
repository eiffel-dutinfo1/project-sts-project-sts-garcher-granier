package fr.dut.info.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import fr.dut.info.cards.Card;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.rooms.FightRoom;
import fr.dut.info.rooms.Room;


/**
 * Cette classe est une adaptation de celle qui a été fourni avec le projet Slay The Spire. 
 * 
 * Elle contient les éléments de visualisation de l'interface. C'est une _vue_.
 * 
 * Vous aurez quelques modfications à faire dans le cadre de ce TD.
 * 
 * @author Christophe Morvan
 *
 */
@SuppressWarnings("preview")
public record SimpleGameView(float height, float width, float hMargin, float vMargin, FightRoom data, float hSize, float vSize) implements GameView {
	
	/**
	 * C'est une méthode qui fournit comme résultat une SimpleGameView et sert de point d'entrée au contrôleur. 
	 *  
	 * @param height Hauteur totale de l'écran.
	 * @param width Largeur totale de l'écran.
	 * @param data  Point d'entrée au modèle.
	 * @param hSize Taille horizontale utile.
	 * @param vSize Taille verticale utile.
	 * @return Une SimpleGameView correctement initialisée.
	 */
	public static SimpleGameView initGameGraphics(float height, float width, FightRoom data, float hSize, float vSize) {
		float hMargin, vMargin;
		hMargin = (width - hSize)/2;
		vMargin = (height -vSize)/2;		
		return new SimpleGameView(height, width, hMargin, vMargin, data, hSize, vSize);
	}
	

	/**
	 * Construction de l'affichage, utilise un objet Graphics2D déjà initialisé.
	 * 
	 * @param graphics a Graphics2D object provided by the default method
	 *                 {@code draw(ApplicationContext, GameData)}
	 * @param data     the GameData containing the game data.
	 */
	@Override
	public void draw(Graphics2D graphics) {
		// Interface de base : rectangle de hSize x vSize centré.
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.setColor(Color.WHITE);
		graphics.fill(new Rectangle2D.Float(hMargin, vMargin, hSize, vSize));

		graphics.setColor(Color.WHITE);
		graphics.setColor(Color.LIGHT_GRAY);
		
		// Fonction auxiliaire de l'affichage du contenu. 
		drawLayout(graphics);
	}


	@Override
	public int areaFromCoordinates(float x, float y) {
		// Todo : Question 1
		if (y < vSize/2) {
			if (x < hSize/2) {
				return 6;
			} else {
				return 7;
			}
		}
		if (y > (vSize/2) + 40) {
			if (x < hSize/5) {
				return 1;
			} else if (x < 2*hSize/5) {
				return 2;
			} else if (x < 3*hSize/5) {
				return 3;
			} else if (x < 4*hSize/5) {
				return 4;
			} else {
				return 5;
			}
		}
		return 0;
	}
	
	/**
	 * Fonction auxiliaire de la construction des éléments de l'interface.
	 * 
	 * @param graphics
	 */
	public void drawLayout(Graphics2D graphics) {
		float yLow = vSize/2 + vMargin, ySpace =40; 
		float [] steps = {0, ySpace};
		// On reste en gris pour la première ligne verticale.
		graphics.draw(new Line2D.Float(960+hMargin, vMargin, hSize/2 + hMargin, yLow));

		// On passe en noir pour le reste de l'interface.
		graphics.setColor(Color.BLACK);
		// 2 lignes horizontales
		for (float step : steps) {
			graphics.draw(new Line2D.Float(0+hMargin, yLow + step, hSize + hMargin, yLow + step));
		}
		float [] xLimits = {hSize/5, 2*hSize/5, 3*hSize/5, 4*hSize/5};
		yLow += ySpace ; 
		// Les lignes verticales de la partie basse.
		for (float xLimit : xLimits) {
			graphics.draw(new Line2D.Float(xLimit+hMargin, yLow, xLimit + hMargin, vMargin + vSize));
		}
		
		// Ensuite affichage des autres éléments : infos du joueur,
		// cartes puis adversaires. 
		drawPlayerInfo(data,graphics);
		graphics.setColor(Color.LIGHT_GRAY);
		drawCards(data, graphics);
		drawOpponents(data, graphics);
	}

	private void drawPlayerInfo(FightRoom data, Graphics2D graphics) {
		int playerHP = data.getAvatar().getCurrentHP();
		int playerMaxHP = data.getAvatar().getMaxHP();
		int playerBlock = data.getAvatar().getCurrentBlock();
		writeStringAtCoords("Player HP : " + playerHP + " / " + playerMaxHP, graphics, hMargin, vMargin + vSize/2 + 30);
		writeStringAtCoords("Player Block : " + playerBlock, graphics, hMargin + hSize/4, vMargin + vSize/2 + 30);
	}
	private void drawCards(FightRoom data, Graphics2D graphics) {
		ArrayList<Card> hand = data.getAvatar().getHand();
		float xUpL = hSize/50;
		float xLowR = 9*hSize/50;
		float yUpL = vSize/2 + 80;
		float yLowR = vSize - 60;
		for (Card card : hand) {
			drawImageInArea(graphics, card.getPicturePath(), xUpL, yUpL, xLowR, yLowR);
			xUpL += hSize/5;
			xLowR += hSize/5;
		}
		//writeStringAtCoords("Jouer cette carte", graphics, *hSize/3, vSize - 40);
	}
	private void drawOpponents(FightRoom data,Graphics2D graphics) {
		// TODO : Question 4 
		// Utiliser la fonction drawImageInArea et writeStringAtCoords
		// Pour afficher les informations de l'adversaire.
		TreeMap<Integer, Opponent> opponents = data.getOpponents();
		float xUpL = hSize/8;
		float yUpL =  vSize - 11*vSize/12;
		float xLowR = 3*hSize/8;
		float yLowR = yUpL + 4*vSize/12;
		for (Entry<Integer, Opponent> opponent : opponents.entrySet()) {
			drawImageInArea(graphics, opponent.getValue().getPicturePath(), xUpL, yUpL, xLowR, yLowR);
			writeStringAtCoords("HP : " + opponent.getValue().getCurrentHP() + " / " + opponent.getValue().getMaxHP(), graphics, xUpL + hSize/12, yLowR+20);
			xUpL += hSize/2;
			xLowR += hSize/2;
		}
	}
	private void writeStringAtCoords(String letter, Graphics2D graphics, float x, float y) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, 24);
		graphics.setColor(Color.BLACK);
	    graphics.setFont(font);
		graphics.drawString(letter, x,y);
		graphics.setColor(Color.LIGHT_GRAY);				
	}
	
	/**
	 * Cette méthode permet d'afficher une image correctement mise à l'échelle 
	 * entre les points (xUpL, yUpL) et (xLowR, yLowR).
	 * @param graphics  Le support d'affichage.
	 * @param imagePath  Le chemin de l'image.
	 * @param xUpL  Coordonnée en x du coin supérieur gauche.
	 * @param yUpL  Coordonnée en y du coin supérieur gauche.
	 * @param xLowR  Coordonnée en x du coin inférieur droit.
	 * @param yLowR  Coordonnée en y du coin inférieur droit.
	 */
	private void drawImageInArea(Graphics2D graphics, String imagePath, float xUpL, float yUpL, float xLowR, float yLowR) {
		float width = xLowR - xUpL, height = yLowR - yUpL;
		Path path = Path.of(imagePath);
		try (InputStream in = Files.newInputStream(path)) {
			BufferedImage img = ImageIO.read(in);
			AffineTransformOp scaling = new AffineTransformOp(AffineTransform
					.getScaleInstance(width / (double) img.getWidth(), height / (double) img.getHeight()),
					AffineTransformOp.TYPE_BILINEAR);
			graphics.drawImage(img, scaling, (int)xUpL, (int)yUpL);
		} catch (IOException e) {
			throw new RuntimeException("problème d'affichage : " + imagePath);
		}
	}
	
}
