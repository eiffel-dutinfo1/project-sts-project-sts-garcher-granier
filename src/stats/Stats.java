package stats;

public class Stats {
	private int block;
	private int strength;
	private int dexterity;
	private int ritual;
	private int weak;
	private int vulnerability;
	
	public Stats() {
		block = 0;
		strength = 0;
		dexterity = 0;
		ritual = 0;
		weak = 0;
		vulnerability = 0 ;
	}
	
	public int getBlock() {
		return block;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getDexterity() {
		return dexterity;
	}
	
	public int getRitual() {
		return ritual;
	}
	
	public int getWeak() {
		return weak;
	}
	
	public int getVulnerability() {
		return vulnerability;
	}
	
	public void addBlock(int value) {
		block += value;
	}
	
	public void addStrength(int value) {
		strength += value;
	}
	
	public void addDexterity(int value) {
		dexterity += value;
	}
	
	public void addRitual(int value) {
		ritual += value;
	}
	
	public void addWeak(int value) {
		weak += value;
	}
	
	public void addVulnerability(int value) {
		vulnerability += value;
	}
	
	public void turnStatsDecrease() {
		block = 0;
		strength += ritual;
		ritual = 0;
		
	}
}
