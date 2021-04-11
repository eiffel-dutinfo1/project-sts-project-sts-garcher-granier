package fr.dut.info.monsters;

import java.util.ArrayList;

import fr.dut.info.monsters.actions.BlockAction;
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
		case "block":
			return new BlockAction(value);
		default:
			throw new IllegalArgumentException("Action name is not recognized");
		}
	}
	
	public static ArrayList<String> stringsToArray(String string) {
		ArrayList<String> array = new ArrayList<String>();
		for (String word : string.split("#")) {
			array.add(word);
		}
		return array;
	}
	
	public static ArrayList<Integer> integersToArray(String string) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (String str : string.split("#")) {
			array.add(Integer.valueOf(str));
		}
		return array;
	}
}
