package fr.dut.info;

public class Randomizer {
	//helper class to easily generate random values
	//min is inclusive, max is exclusive
	public static int randomInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
