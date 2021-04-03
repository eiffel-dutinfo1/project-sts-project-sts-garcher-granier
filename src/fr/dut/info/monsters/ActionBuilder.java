package fr.dut.info.monsters;

import java.util.ArrayList;

import fr.dut.info.monsters.actions.DamageAction;
import fr.dut.info.monsters.actions.StrengthAction;
import fr.dut.info.monsters.actions.WeakAction;

public class ActionBuilder {
	public static MonsterAction createAction(String name, int value) {
		switch (name) {
		case "damage":
			return new DamageAction(value);
		case "weak":
			return new WeakAction(value);
		case "strength":
			return new StrengthAction(value);
		default:
			throw new IllegalArgumentException("Action name is not recognized");
		}
	}
	
	public static ArrayList<String> stringToArray(String string) {
		ArrayList<String> array = new ArrayList<String>();
		for (String word : string.split("-")) {
			array.add(word);
		}
		return array;
	}
}
