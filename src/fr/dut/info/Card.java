package fr.dut.info;

import java.util.ArrayList;

public interface Card {
	//boolean pas definitif, reste pour tester des trucs
	boolean playCard(PlayerAvatar avatar, ArrayList<Opponent> opponents);
	int energyCost();
}
