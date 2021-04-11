package fr.dut.info;

public class Randomizer {
	//min inclusif, max exclusif
	public static int randomInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
