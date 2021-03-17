package fr.dut.info;

public class PlayerAvatar {
	private final Player player;
	private int maxEnergy;
	private int energy;
	private final Discard discard;
	private final Hand hand;
	private final Draw draw;
	
	public PlayerAvatar(Player player, int maxEnergy) {
		this.player = player;
		this.maxEnergy = maxEnergy;
		this.energy = maxEnergy;
	}
	
}
