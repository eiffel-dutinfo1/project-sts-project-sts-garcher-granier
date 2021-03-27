package fr.dut.info.cards;

import java.util.ArrayList;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public interface Card {
	//boolean pas definitif, reste pour tester des trucs
	boolean playCard(PlayerAvatar avatar, ArrayList<Opponent> opponents);
	int energyCost();
}
