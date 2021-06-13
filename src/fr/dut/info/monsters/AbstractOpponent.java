package fr.dut.info.monsters;

import java.io.IOException;
import java.util.ArrayList;

import fr.dut.info.Randomizer;
import fr.dut.info.player.PlayerAvatar;
import fr.dut.info.stats.Stats;

public abstract class AbstractOpponent implements Opponent{
	//all useful and general information about a monster are here
	private final String name;
	private final String picturePath;
	private final int maxhp;
	private int hp;
	private final Stats stats;
	//a monsters has a list of moves, a move being what a monster does in a single turn
	private final ArrayList<Move> moves;
	private Move nextMove;
	
	public AbstractOpponent(String name, int maxhp, String picturePath) {
		this.name = name;
		this.picturePath = picturePath;
		this.maxhp = maxhp;
		this.hp = maxhp;
		stats = new Stats();
		moves = new ArrayList<Move>();
	}
	
	public Move nextMove() {
		return nextMove;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Move> getMoves() {
		return moves;
	}
	
	public int getCurrentHP() {
		return hp;
	}
	
	public int getMaxHP() {
		return maxhp;
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	
	public void firstMove(Move move) {
		nextMove = move;
	}
	
	public void addMove(Move move) {
		moves.add(move);
	}
	
	//to execute the move, you execute each action the move is composed of
	//for example, a monster can in a single move in a single turn, deal damage and weakness to the player
	public void executeMove(Opponent self, PlayerAvatar avatar) throws IOException {
		nextMove.executeActions(self, avatar);
		for (Move move : moves) {
			if (!move.equals(nextMove)) {
				move.resetStreak();
			}
		}
		getNextMove();
	}
	
	//probabilistic method to select the next legal move from the list of moves
	//each move has a set weight and a maximum amount of consecutive appearances
	//from these factors, we determines the legal moves available, then from
	//those legal moves we randomly select one
	public void getNextMove() {
		int maxProbability = 0;
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		for (Move move : moves) {
			if (move.isLegal()) {
				legalMoves.add(move);
				maxProbability += move.getProbability();
			}
		}
		int randomNumber = Randomizer.randomInt(0, maxProbability);
		int cumulativeProbability = 0;
		for (Move move : legalMoves) {
			if (move.getProbability() + cumulativeProbability > randomNumber) {
				nextMove = move;
				return;
			} else {
				cumulativeProbability += move.getProbability();
			}
		}
	}
	
	//forcibly set the next move to bypass getNextMove()
	public void setNextMove(Move move) {
		this.nextMove = move;
	}
	
	@Override
	public boolean takeDamage(int value) {
		hp -= value;
		return hp <= 0;
	}
	
	public boolean isDead() {
		if (hp <= 0) {
			return true;
		}
		return false;
	}
}
