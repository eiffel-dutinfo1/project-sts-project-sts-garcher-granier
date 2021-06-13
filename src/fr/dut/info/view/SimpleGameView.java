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
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import fr.dut.info.Log;
import fr.dut.info.cards.Card;
import fr.dut.info.monsters.Opponent;
import fr.dut.info.rooms.FightRoom;
import fr.dut.info.rooms.RewardRoom;
import fr.dut.info.rooms.Map;
import fr.dut.info.rooms.Merchant;
import fr.dut.info.rooms.Room;


//where magic happens

@SuppressWarnings("preview")
public record SimpleGameView(float height, float width, float hMargin, float vMargin, Map data, float hSize, float vSize) implements GameView {
	public static SimpleGameView initGameGraphics(float height, float width, Map data2, float hSize, float vSize) {
		float hMargin, vMargin;
		hMargin = (width - hSize)/2;
		vMargin = (height -vSize)/2;		
		return new SimpleGameView(height, width, hMargin, vMargin, data2, hSize, vSize);
	}
	

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.setColor(Color.WHITE);
		graphics.fill(new Rectangle2D.Float(hMargin, vMargin, hSize, vSize));

		graphics.setColor(Color.WHITE);
		graphics.setColor(Color.LIGHT_GRAY);
		
		//if the game is over, displays game over screen, otherwise displays room layouts
		if (data.isGameOver()) {
			drawEndScreen(graphics);
		} else {
			drawLayout(graphics);
		}
	}

	
	//returns correct area input pattern depending on the room
	@Override
	public int areaFromCoordinates(float x, float y) {
		switch (data.getCurrentRoom().getRoomType()) {
		case "FightRoom":
			return areaFromCoordinatesFightRoom(x, y);
		case "StartRoom":
			return areaFromCoordinatesStartRoom(x, y);
		case "Merchant":
			return areaFromCoordinatesMerchant(x, y);
		case "FireCamp":
			return areaFromCoordinatesFireCamp(x, y);
		case "Reward":
			return areaFromCoordinatesReward(x, y);
		case "WinScreen":
			return areaFromCoordinatesWin(x, y);
		default:
			return -1;
		}
	}
	
	public int areaFromCoordinatesWin(float x, float y) {
		return -1;
	}
	
	public int areaFromCoordinatesFireCamp(float x, float y) {
		if (x < hSize/2) {
			return 0;
		} else if (x > hSize/2) {
			return 1;
		}
		return -1;
	}
	
	public int areaFromCoordinatesReward(float x, float y) {
		if (x < hSize/3) {
			return 0;
		} else if (x < 2*hSize/3) {
			return 1;
		} else if (x < 3*hSize/3) {
			return 2;
		}
		return -1;
	}
	
	public int areaFromCoordinatesFightRoom(float x, float y) {
		if (y < vSize/2) {
			if (x < hSize/5) {
				return 5;
			} else if (x < 2*hSize/5) {
				return 6;
			} else if (x < 3*hSize/5) {
				return 7;
			} else if (x < 4*hSize/5) {
				return 8;
			} else {
				return -1;
			}
		}
		if ((y > vSize/2) && (y < (vSize/2) + 40) && (x > 4*hSize/5)) {
			return 9;
		}
		if (y > (vSize/2) + 40) {
			if (x < hSize/5) {
				return 0;
			} else if (x < 2*hSize/5) {
				return 1;
			} else if (x < 3*hSize/5) {
				return 2;
			} else if (x < 4*hSize/5) {
				return 3;
			} else {
				return 4;
			}
		}
		return -1;
	}
	
	public int areaFromCoordinatesMerchant(float x, float y) {
		if ((y > vSize/2) && (y < (vSize/2) + 40) && (x > 4*hSize/5)) {
			return 10;
		}
		if (y < (vSize/2)) {
			if (x < hSize/5) {
				return 0;
			} else if (x < 2*hSize/5) {
				return 1;
			} else if (x < 3*hSize/5) {
				return 2;
			} else if (x < 4*hSize/5) {
				return 3;
			} else {
				return 4;
			}
		}
		if (y > (vSize/2) + 40) {
			if (x < hSize/5) {
				return 5;
			} else if (x < 2*hSize/5) {
				return 6;
			} else if (x < 3*hSize/5) {
				return 7;
			} else if (x < 4*hSize/5) {
				return 8;
			} else {
				return 9;
			}
		}
		return -1;
	}
	
	public int areaFromCoordinatesStartRoom(float x, float y) {
		if (x < hSize/2) {
			return 0;
		} else if (x > hSize/2) {
			return 1;
		}
		return -1;
	}
	
	//returns the correct layout to display depending on the room
	public void drawLayout(Graphics2D graphics) {
		switch (data.getCurrentRoom().getRoomType()) {
		case "FightRoom":
			drawFightRoomLayout(graphics);
			break;
		case "StartRoom":
			drawStartRoomLayout(graphics);
			break;
		case "Merchant":
			drawMerchantLayout(graphics);
			break;
		case "FireCamp":
			drawFireCampLayout(graphics);
			break;
		case "Reward":
			drawRewardLayout(graphics);
			break;
		case "WinScreen":
			drawWinScreen(graphics);
			break;
		default:
			break;
		}
	}
	
	public void drawFireCampLayout(Graphics2D graphics) {
		writeStringAtCoords("Heal", graphics, 2*hSize/8, vSize/2, 24);
		writeStringAtCoords("Forgeron", graphics, 6*hSize/8, vSize/2, 24);
		graphics.draw(new Line2D.Float(hSize/2, 0, hSize/2, vSize));
	}
	
	public void drawRewardLayout(Graphics2D graphics) {
		ArrayList<Card> rewards = ((RewardRoom) data.getCurrentRoom()).getRewards();
		//because of bad implementations, the user must click once to make the shop items appear
		if (rewards.size()==0) {
			//this is to make it a little bit more user-friendly
			writeStringAtCoords("Découvrir les récompenses", graphics, hSize/2-200, vSize/2, 40);
		} else {
			graphics.draw(new Line2D.Float(hSize/3, 0, hSize/3, vSize));
			graphics.draw(new Line2D.Float(2*hSize/3, 0, 2*hSize/3, vSize));
			float xUpL = 2*hSize/30;
			float xLowR = 8*hSize/30;
			float yUpL = 2*vSize/8;
			float yLowR = 6*vSize/8;
			for (Card card : rewards) {
				drawImageInArea(graphics, card.getPicturePath(), xUpL, yUpL, xLowR, yLowR);
				xUpL += hSize/3;
				xLowR += hSize/3;
			}
		}
	}
	
	public void drawFightRoomLayout(Graphics2D graphics) {
		float yLow = vSize/2 + vMargin, ySpace =40; 
		float [] steps = {0, ySpace};
		graphics.setColor(Color.BLACK);
		for (float step : steps) {
			graphics.draw(new Line2D.Float(0+hMargin, yLow + step, hSize + hMargin, yLow + step));
		}
		float [] xLimits = {hSize/5, 2*hSize/5, 3*hSize/5, 4*hSize/5};
		for (float xLimit : xLimits) {
			graphics.draw(new Line2D.Float(xLimit+hMargin, vMargin, xLimit + hMargin, yLow));
		}
		graphics.draw(new Line2D.Float(4*hSize/5, 0, 4*hSize/5, vSize));
		writeStringAtCoords("END TURN", graphics, 87*hSize/100, vMargin + vSize/2 + 30, 24);
		yLow += ySpace ;
		for (float xLimit : xLimits) {
			graphics.draw(new Line2D.Float(xLimit+hMargin, yLow, xLimit + hMargin, vMargin + vSize));
		}
		
		drawPlayerInfo(graphics);
		graphics.setColor(Color.LIGHT_GRAY);
		drawCards(graphics);
		drawOpponents(graphics);
		drawLogs(graphics);
		drawSelectedEntities(graphics);
	}
	
	private void drawMerchantLayout(Graphics2D graphics) {
		if (!((Merchant) data.getCurrentRoom()).isLoaded()) {
			writeStringAtCoords("Click to reveal cards", graphics, hSize/2, vMargin + vSize/2 + 30, 24);
		}
		int gold = data.getPlayer().getGold();
		ArrayList<Card> shop = ((Merchant) data.getCurrentRoom()).getShop();
		int count = 0;
		float xUpL = hSize/50;
		float xLowR = 9*hSize/50;
		float yUpL = 80;
		float yLowR = vSize/2 - 60;
		for (Card card : shop) {
			drawImageInArea(graphics, card.getPicturePath(), xUpL, yUpL, xLowR, yLowR);
			writeStringAtCoords(String.valueOf(Merchant.getCardPrice(card)), graphics, xLowR-((hSize/5)/2), vMargin + yLowR + 30, 24);
			xUpL += hSize/5;
			xLowR += hSize/5;
			if (count == 4) {
				yUpL = vSize/2 + 80;
				yLowR = vSize - 60;
				xUpL = hSize/50;
				xLowR = 9*hSize/50;
			}
			count++;
		}
		float yLow = vSize/2 + vMargin, ySpace =40; 
		float [] steps = {0, ySpace};
		graphics.setColor(Color.BLACK);
		for (float step : steps) {
			graphics.draw(new Line2D.Float(0+hMargin, yLow + step, hSize + hMargin, yLow + step));
		}
		writeStringAtCoords("LEAVE", graphics, 87*hSize/100, vMargin + vSize/2 + 30, 24);
		writeStringAtCoords("Gold : " + gold, graphics, hMargin, vMargin + vSize/2 + 30, 24);
	}
	
	private void drawEndScreen(Graphics2D graphics) {
		writeStringAtCoords("You lose!", graphics, hSize/2, vSize/2, 24);
	}
	
	private void drawWinScreen(Graphics2D graphics) {
		writeStringAtCoords("You win!", graphics, hSize/2, vSize/2, 24);
	}
	
	private void drawStartRoomLayout(Graphics2D graphics) {
		writeStringAtCoords("IronClad", graphics, 2*hSize/8, vSize/2, 24);
		writeStringAtCoords("Silent", graphics, 6*hSize/8, vSize/2, 24);
		graphics.draw(new Line2D.Float(hSize/2, 0, hSize/2, vSize));
	}

	private void drawPlayerInfo(Graphics2D graphics) {
		int playerHP = ((FightRoom) data.getCurrentRoom()).getAvatar().getCurrentHP();
		int playerMaxHP = ((FightRoom) data.getCurrentRoom()).getAvatar().getMaxHP();
		int playerBlock = ((FightRoom) data.getCurrentRoom()).getAvatar().getStats().getBlock();
		int playerEnergy = ((FightRoom) data.getCurrentRoom()).getAvatar().getEnergy();
		writeStringAtCoords("Player HP : " + playerHP + " / " + playerMaxHP, graphics, hMargin, vMargin + vSize/2 + 30, 24);
		writeStringAtCoords("Block : " + playerBlock, graphics, hMargin + hSize/4, vMargin + vSize/2 + 30, 24);
		writeStringAtCoords("Energy : " + playerEnergy, graphics, hMargin + hSize/4 + 200, vMargin + vSize/2 + 30, 24);
	}
	private void drawCards(Graphics2D graphics) {
		ArrayList<Card> hand = ((FightRoom) data.getCurrentRoom()).getAvatar().getHand();
		float xUpL = hSize/50;
		float xLowR = 9*hSize/50;
		float yUpL = vSize/2 + 80;
		float yLowR = vSize - 60;
		for (Card card : hand) {
			drawImageInArea(graphics, card.getPicturePath(), xUpL, yUpL, xLowR, yLowR);
			xUpL += hSize/5;
			xLowR += hSize/5;
		}
	}
	private void drawOpponents(Graphics2D graphics) {
		TreeMap<Integer, Opponent> opponents = ((FightRoom) data.getCurrentRoom()).getOpponents();
		float xUpL = hSize/50;
		float xLowR = 9*hSize/50;
		float yUpL = 30;
		float yLowR = vSize/3;
		for (Entry<Integer, Opponent> opponent : opponents.entrySet()) {
			drawImageInArea(graphics, opponent.getValue().getPicturePath(), xUpL, yUpL, xLowR, yLowR);
			writeStringAtCoords("HP : " + opponent.getValue().getCurrentHP() + " / " + opponent.getValue().getMaxHP(), graphics, xUpL + hSize/20, yLowR+20, 24);
			int lines = 1;
			for (String preview : opponent.getValue().nextMove().actionsPreview()) {
				writeStringAtCoords(preview, graphics, xUpL + hSize/20, yLowR+20+(lines*30), 24);
				lines++;
			}
			xUpL += hSize/5;
			xLowR += hSize/5;
		}
	}
	private void drawLogs(Graphics2D graphics) {
		float yCoord = 30;
		int numberOfLogs = (int) (vSize/2)/27;
		for (String log : Log.getLog().logDisplay(numberOfLogs)) {
			writeStringAtCoords(log, graphics, hMargin + 4*hSize/5 + 10, yCoord, hSize/120);
			yCoord+=26;
		}
	}
	private void drawSelectedEntities(Graphics2D graphics) {
		if (((FightRoom) data.getCurrentRoom()).targetSelected()) {
			drawCross(graphics, ((FightRoom) data.getCurrentRoom()).getSelectedTarget()*hSize/5-hSize/10, vSize/2-40);
		}
		if (((FightRoom) data.getCurrentRoom()).cardSelected()) {
			drawCross(graphics, ((FightRoom) data.getCurrentRoom()).getSelectedCard()*hSize/5+hSize/10, vSize/2+80);
		}
	}
	private void drawCross(Graphics2D graphics, float x, float y) {
		graphics.setColor(Color.BLACK);
		graphics.draw(new Line2D.Float(x-20, y-20, x+20, y+20));
		graphics.draw(new Line2D.Float(x+20, y-20, x-20, y+20));
	}
	private void writeStringAtCoords(String letter, Graphics2D graphics, float x, float y, double fontWeight) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.PLAIN, (int) fontWeight);
		graphics.setColor(Color.BLACK);
	    graphics.setFont(font);
		graphics.drawString(letter, x,y);
		graphics.setColor(Color.LIGHT_GRAY);				
	}
	
	//draw an image using a path and specified coordinates
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
