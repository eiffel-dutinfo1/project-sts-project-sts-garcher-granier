package fr.dut.info.cards.strategies;

import fr.dut.info.cards.strategies.cardstrats.ApplyAllWeak;
import fr.dut.info.cards.strategies.cardstrats.ApplyAllVulnerable;
import fr.dut.info.cards.strategies.cardstrats.ApplyWeak;
import fr.dut.info.cards.strategies.cardstrats.BlockStrat;
import fr.dut.info.cards.strategies.cardstrats.DamageNbStrikeStrat;
import fr.dut.info.cards.strategies.cardstrats.DamageRandomTimeStrat;
import fr.dut.info.cards.strategies.cardstrats.DiscardAllDrawAllStrat;
import fr.dut.info.cards.strategies.cardstrats.DiscardRandomStrat;
import fr.dut.info.cards.strategies.cardstrats.DiscardStrat;
import fr.dut.info.cards.strategies.cardstrats.DoubleBlockStrat;
import fr.dut.info.cards.strategies.cardstrats.DrawCardStrat;
import fr.dut.info.cards.strategies.cardstrats.EndTurnBlock;
import fr.dut.info.cards.strategies.cardstrats.GainEnergyStrat;
import fr.dut.info.cards.strategies.cardstrats.GainStrength;
import fr.dut.info.cards.strategies.cardstrats.IfVulnerableGainEnergyDrawStrat;
import fr.dut.info.cards.strategies.cardstrats.LastCardDiscardToDrawStrat;
import fr.dut.info.cards.strategies.cardstrats.LoseHpStrat;
import fr.dut.info.cards.strategies.cardstrats.MultiAttackStrat;
import fr.dut.info.cards.strategies.cardstrats.ShuffleDiscardIntoDraw;
import fr.dut.info.cards.strategies.cardstrats.SingleTargetAttackBlockStrat;
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
		case "single_damage_block":
			return new SingleTargetAttackBlockStrat(value);
		case "vulnerable":
			return new VulnerableStrat(value);
		case "apply_weak":
			return new ApplyWeak(value);
		case "last_card_discard_to_draw":
			return new LastCardDiscardToDrawStrat(value);
		case "damage_nb_strike":
			return new DamageNbStrikeStrat(value);
		case "draw_card":
			return new DrawCardStrat(value);
		case "damage_random_time":
			return new DamageRandomTimeStrat(value);
		case "apply_all_vulnerable":
			return new ApplyAllVulnerable(value);
		case "lose_hp":
			return new LoseHpStrat(value);
		case "gain_energy":
			return new GainEnergyStrat(value);
		case "if_vunerable_gain_energy_draw":
			return new IfVulnerableGainEnergyDrawStrat(value);
		case "double_block":
			return new DoubleBlockStrat(value);
		case "gain_strength":
			return new GainStrength(value);
		case "end_turn_block":
			return new EndTurnBlock(value);
		case "apply_all_weak":
			return new ApplyAllWeak(value);
		case "shuffle_discard_into_draw":
			return new ShuffleDiscardIntoDraw(value);
		case "discard":
			return new DiscardStrat(value);
		case "discard_random":
			return new DiscardRandomStrat(value);
		case "discard_all_draw_all":
			return new DiscardAllDrawAllStrat();
		default:
			throw new IllegalArgumentException("Strat name is not recognized, please verify the .txt file. Input : " + name);
		}
	}
}
