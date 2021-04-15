package fr.dut.info.stats;

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
	
	public int applyAttackerModifiers(int damageDealt) {
		damageDealt += strength;
		if (weak > 0) {
			damageDealt = (int) (damageDealt * 0.75);
		}
		return damageDealt;
	}
	
	public int applyDefenderModifiers(int damageTaken) {
		if (block > 0) {
			int temp = damageTaken;
			damageTaken -= block;
			block -= temp;
		}
		if (damageTaken <= 0) {
			return 0;
		}
		if (vulnerability > 0) {
			damageTaken = (int) (damageTaken *1.5);
		}
		return damageTaken;
	}
	
	public void turnUpdate() {
		block = 0;
		strength += ritual;
		ritual = 0;
		if (weak > 0) {
			weak--;
		}
		if (vulnerability > 0) {
			vulnerability--;
		}
	}
}
