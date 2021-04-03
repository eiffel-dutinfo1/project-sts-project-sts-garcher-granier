package fr.dut.info.monsters;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.Randomizer;
import fr.dut.info.player.PlayerAvatar;

public abstract class AbstractOpponent implements Opponent{
	private final String name;
	private int hp;
	private int strength;
	private int weak;
	private int block;
	private final ArrayList<Move> moves;
	private Move nextMove;
	
	public AbstractOpponent(String name, int hp) {
		this.name = name;
		this.hp = hp;
		this.strength = 0;
		this.weak = 0;
		this.block = 0;
		moves = new ArrayList<Move>();
	}
	
	public void firstMove(Move move) {
		nextMove = move;
	}
	
	public void addMove(Move move) {
		moves.add(move);
	}
	
	public void executeMove(Opponent self, PlayerAvatar avatar) throws IOException {
		nextMove.executeActions(self, avatar);
	}
	
	public void getNextMove() {
		int maxProbability = 0;
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		for (Move move : moves) {
			if (!move.isIllegal()) {
				legalMoves.add(move);
				maxProbability += move.getProbability();
			}
		}
		int randomNumber = Randomizer.randomInt(0, maxProbability);
		int cumulativeProbability = 0;
		for (Move move : moves) {
			if (move.getProbability() + cumulativeProbability < randomNumber) {
				nextMove = move;
				return;
			} else {
				cumulativeProbability += move.getProbability();
			}
		}
	}
	
	public ArrayList<Move> getMoves() {
		return moves;
	}
	
	@Override
	public boolean takeDamage(int value) {
		hp -= value;
		return hp <= 0;
	}

	public void dealDamage(PlayerAvatar playerAvatar, int damage) {
		if (weak == 0) {
			playerAvatar.takeDamage(damage + strength);
			return;
		}
		playerAvatar.takeDamage((int) ((damage + strength) * 0.75));
	}
	
	public void applyBlock(int value) {
		block += value;
	}
	
	public void applyStrength(int value) {
		strength += value;
	}
	
	@Override
	public String toString() {
		return "HP left : " + hp + "\n";
	}
	
	public int getHp() {
		return hp;
	}
}
