package fr.dut.info.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Par souci de simplification ce modèle est une sorte de résumé des éléments impliqués dans le combat. 
 * On ajoute un mécanisme de sélection pour simplifier l'interface.
 * 
 * Lorsque une carte a besoin d'une cible (TargetType.MONSTER) on lui attribue le monstre sélectionné. 
 * Sinon (TargetType.NONE) on lui donne le playerAvatar comme paramètre.
 * 
 * @author Christophe Morvan
 *
 */
public class SimpleGameData {
	// Convention : carte absente (jouée) : null, monstre tué : null.
	
	private SimpleCard [] cardHand;
	private Monster [] opponents;
	private PlayerAvatar player;

	// En principe lea valeur de selectedCard ou selectedTarget 
	// correspond à l'indice dans le tableau. 
	// Par convention : -1 = pas de sélection.
	private int selectedCard;
	private int selectedTarget; 
	public SimpleGameData() {
		// Ce sont des tableaux statiques. 
		cardHand = new SimpleCard[3];
		opponents = new Monster[2];
		player = new PlayerAvatar();
		
		// Initialisation de la main puis des adversaires,
		// par accès direct à une case de chaque tableau.
		cardHand[0] = new DamageCard("pictures/Strike_R.png",6);
		cardHand[1] = new BlockCard("pictures/Defend_R.png",5);
		cardHand[2] = new DamageCard("pictures/Strike_R.png",6);
		opponents[0] = new Monster("pictures/Cultist.png", 20);
		opponents[1] = new Monster("pictures/Acid-slime.png", 15);
		
		resetSelected();
	}
	
	public SimpleCard[] getCardHand() {
		return cardHand;
	}

	public Monster[] getOpponents() {
		return opponents;
	}

	public PlayerAvatar getPlayer() {
		return player;
	}
	
	public void selectTarget(int i) {
		selectedTarget = i;
	}
	public void selectCard(int i) {
		selectedCard = i;
	}

	public boolean targetSelected() {
		return selectedTarget >= 0;
	}
	
	public boolean cardSelected() {
		return selectedCard >= 0;		
	}
	
	public void playSelected() {
		System.out.println("playSelected : target " + selectedTarget + " card :" + selectedCard);
		//TODO : Question
		// Il s'agit simplement de jouer la carte sélectionnée sur la cible sélectionnée.
		//Il faut faire attention à vérifier le type de cible, supprimer la carte jouée 
		// et remettre à zéro la sélection.
		if (targetSelected()) {
			cardHand[selectedCard].playCard(opponents[selectedTarget]);
		} else if (cardHand[selectedCard].getTarget() == TargetType.NONE) {
			cardHand[selectedCard].playCard(player);
		}
		resetSelected();
	}
	public boolean isCardSelected(int i) {
		return i == selectedCard;
	}
	public boolean isOpponentSelected(int i) {
		return i == selectedTarget; 
	}
	
	private void resetSelected() {
	selectedTarget = -1;
	selectedCard = -1;
	}	
}
