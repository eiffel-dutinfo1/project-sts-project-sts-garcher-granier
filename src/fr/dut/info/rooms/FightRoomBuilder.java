package fr.dut.info.rooms;

import fr.dut.info.Randomizer;
import fr.dut.info.monsters.act1.Cultist;
import fr.dut.info.monsters.act1.JawWorm;

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
		int number = Randomizer.randomInt(0, 2);
		switch (number) {
		case 0:
			room.addOpponent(new Cultist());
			break;
		case 1:
			room.addOpponent(new JawWorm());
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
			throw new IllegalArgumentException("Could not add a boss to the FightRoom");
		}
	}
}
