package fr.dut.info.cards;

import java.util.TreeMap;

import fr.dut.info.monsters.Opponent;
import fr.dut.info.player.PlayerAvatar;

public class CardStrat {
	public static void useCardStrat(String stratName, Integer value, TreeMap<Integer, Opponent> opponents,
			PlayerAvatar playerAvatar) {
		switch (stratName) {
		case "single_damage":
			singleDamageStrat(value, opponents, playerAvatar);
			break;
		case "block":
			singleDamageStrat(value, opponents, playerAvatar);
			break;
		default:
			throw new IllegalArgumentException("Strat name is not recognized");
		}
	}

	public static void singleDamageStrat(Integer value, TreeMap<Integer, Opponent> opponents,
			PlayerAvatar playerAvatar) {
		if (opponents.size() == 1) {
			opponents.get(opponents.firstKey()).takeDamage(value);
		} else {
			opponents.get(Input.getTarget(opponents)).takeDamage(value);
		}
	}

	public static void blockStrat(Integer value, TreeMap<Integer, Opponent> opponents, PlayerAvatar playerAvatar) {
		
	}
}
