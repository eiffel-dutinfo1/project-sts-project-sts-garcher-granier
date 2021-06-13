package fr.dut.info.rooms;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.act1.AcidSlimeL;
import fr.dut.info.monsters.act1.AcidSlimeM;
import fr.dut.info.monsters.act1.AcidSlimeS;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.GreenLouse;
import fr.dut.info.monsters.act1.JawWorm;
import fr.dut.info.monsters.act1.RedLouse;
import fr.dut.info.monsters.act1.SlimeBoss;
import fr.dut.info.monsters.act1.SpikeSlimeL;
import fr.dut.info.monsters.act1.SpikeSlimeM;
import fr.dut.info.monsters.act1.SpikeSlimeS;

public class FightRoomBuilder {
	public static void createFightRoom(FightRoom room, String monsterType) {
		switch (monsterType) {
		case "basic":
			addBasicMonsters(room);
			break;
		case "elite":
			addEliteMonsters(room);
			break;
		case "boss":
			addBoss(room);
			break;
		default:
			throw new IllegalArgumentException("Could not create the FightRoom.");
		}
	}
	
	private static void addBasicMonsters(FightRoom room) {
		// CHANGER LES VALEURS DU RANDOMIZER SI AJOUT D'UN MONSTRE
		int number = Randomizer.randomInt(0, 1);
		switch (number) {
		case 0:
			room.addOpponent(new Cultist());
			break;
		case 1:
			room.addOpponent(new JawWorm());
			break;
		case 2:
			int whichLouse = Randomizer.randomInt(0, 2);
			if (whichLouse == 0) {
				room.addOpponent(new RedLouse());
			} else {
				room.addOpponent(new GreenLouse());
			}
			whichLouse = Randomizer.randomInt(0, 2);
			if (whichLouse == 0) {
				room.addOpponent(new RedLouse());
			} else {
				room.addOpponent(new GreenLouse());
			}
			break;
		case 3:
			int whichSlime = Randomizer.randomInt(0, 2);
			if (whichSlime == 0) {
				room.addOpponent(new AcidSlimeM());
			} else {
				room.addOpponent(new SpikeSlimeM());
			}
			whichSlime = Randomizer.randomInt(0, 2);
			if (whichSlime == 0) {
				room.addOpponent(new AcidSlimeS());
			} else {
				room.addOpponent(new SpikeSlimeS());
			}
			break;
		case 4:
			room.addOpponent(new AcidSlimeL());
			break;
		case 5:
			room.addOpponent(new SpikeSlimeL());
			break;
		default:
			throw new IllegalArgumentException("Could not add basic monsters to the FightRoom");
		}
	}
	
	private static void addEliteMonsters(FightRoom room) {
		// CHANGER LES VALEURS DU RANDOMIZER SI AJOUT D'UN MONSTRE
		int number = Randomizer.randomInt(0, 2);
		switch (number) {
		case 0:
			room.addOpponent(new Cultist());
			break;
		case 1:
			room.addOpponent(new JawWorm());
			break;
		default:
			throw new IllegalArgumentException("Could not add elite monsters to the FightRoom");
		}
	}
	
	private static void addBoss(FightRoom room) {
		room.addOpponent(new SlimeBoss());
	}
}
