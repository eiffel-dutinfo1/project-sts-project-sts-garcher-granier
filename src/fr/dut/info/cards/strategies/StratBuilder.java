package fr.dut.info.cards.strategies;

import fr.dut.info.cards.strategies.cardstrats.BlockStrat;
import fr.dut.info.cards.strategies.cardstrats.MultiAttackStrat;
import fr.dut.info.cards.strategies.cardstrats.SingleTargetAttackStrat;
import fr.dut.info.cards.strategies.cardstrats.VulnerableStrat;

public class StratBuilder {
	public static Strat createStrat(String name, int value) {
		switch (name) {
		case "single_damage":
			return new SingleTargetAttackStrat(value);
		case "block":
			return new BlockStrat(value);
		case "multi_damage":
			return new MultiAttackStrat(value);
		case "vulnerable":
			return new VulnerableStrat(value);
		default:
			throw new IllegalArgumentException("Strat name is not recognized, please verify the .txt file");
		}
	}
}
