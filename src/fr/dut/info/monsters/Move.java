package fr.dut.info.monsters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import fr.dut.info.player.PlayerAvatar;

public class Move {
	private final String name;
	private final int probability;
	private final int maxStreak;
	private int currentStreak;
	private final ArrayList<MonsterAction> actions;
	
	public Move(String name, int probability, int maxStreak, ArrayList<String> actionNames, int value) {
		this.name = name;
		this.probability = probability;
		this.maxStreak = maxStreak;
		currentStreak = 0;
		actions = new ArrayList<MonsterAction>();
		for (String string : actionNames) {
			actions.add(ActionBuilder.createAction(string, value));
		}
	}
	
	public int getProbability() {
		return probability;
	}
	
	//return true si le move est illégal
	public boolean isIllegal() {
		return currentStreak == maxStreak;
	}
	
	public void resetStreak() {
		currentStreak = 0;
	}
	
	// ici l'opponent correspond au monstre qui effectue le move
	public void executeActions(Opponent self, PlayerAvatar avatar) throws IOException {
		for (MonsterAction action : actions) {
			action.doAction(self, avatar);
		}
		if (!(maxStreak == -1)) {
			currentStreak++;
		}
	}
	
	/* inutiles pour le moment
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Move))
			return false;
		Move other = (Move) obj;
		return name.equals(other.name);
	}
	*/
}
