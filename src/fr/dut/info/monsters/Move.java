package fr.dut.info.monsters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import fr.dut.info.player.PlayerAvatar;

public class Move {
	//a move is what a monster does in a single turn
	private final String name;
	private final int probability;
	private final int maxStreak;
	private int currentStreak;
	//one or multiple actions can compose a move
	private final ArrayList<MonsterAction> actions;
	
	public Move(String name, int probability, int maxStreak, ArrayList<String> actionNames, ArrayList<Integer> actionValues) {
		this.name = name;
		this.probability = probability;
		this.maxStreak = maxStreak;
		currentStreak = 0;
		actions = new ArrayList<MonsterAction>();
		for (int i = 0; i < actionNames.size(); i++) {
			actions.add(ActionBuilder.createAction(actionNames.get(i), actionValues.get(i)));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getCurrentStreak() {
		return currentStreak;
	}
	
	public int getProbability() {
		return probability;
	}
	
	//returns true if the move is legal
	public boolean isLegal() {
		return currentStreak < maxStreak;
	}
	
	public void resetStreak() {
		currentStreak = 0;
	}
	
	// here the opponent is the monster itself (hence the "self" argument)
	public void executeActions(Opponent self, PlayerAvatar avatar) throws IOException {
		for (MonsterAction action : actions) {
			action.doAction(self, avatar);
		}
		if (!(maxStreak == -1)) {
			currentStreak++;
		}
	}
	
	//gives a list of actions to display in the view as a preview to inform the player of the monster intentions
	public ArrayList<String> actionsPreview() {
		ArrayList<String> preview = new ArrayList<String>();
		for (MonsterAction action : actions) {
			preview.add(action.actionPreview());
		}
		return preview;
	}
	
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
}
