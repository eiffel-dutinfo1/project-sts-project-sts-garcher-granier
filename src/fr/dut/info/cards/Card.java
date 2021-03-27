package fr.dut.info.cards;

import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public interface Card {
	//boolean pas definitif, reste pour tester des trucs
	void playCard(PlayerAvatar avatar, TreeMap<Integer, Opponent> opponents);
	int energyCost();
}
