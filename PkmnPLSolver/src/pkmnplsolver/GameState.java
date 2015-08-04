package pkmnplsolver;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	
	private char board[][];
	private int movesLeft;
	private List<String> movesThusFar;
	
	public int getMovesLeft() { return movesLeft; }
	public List<String> getMovesThusFar() { return movesThusFar; }
	
	public GameState(int numberOfMoves, String[] initialState) {
		movesLeft = numberOfMoves;
		board = new char[12][6];
		movesThusFar = new ArrayList<String>();
		if (initialState.length != 12) {
			System.out.println("Initial state has " + initialState.length + " rows instead of 12");
		}
		for (int i = 0; i < 12; i++) {
			if (initialState[i].length() != 6) {
				System.out.println("Initial state has " + initialState[i].length() + " columns instead of 6");
				System.exit(1);
			}
			for (int j = 0; j < 6; j++) {
				board[i][j] = initialState[11-i].charAt(j);
			}
		}		
	}
	
	public GameState(GameState other) {
		movesLeft = other.movesLeft;
		board = new char[12][6];
		movesThusFar = new ArrayList<String>(other.movesThusFar);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = other.board[i][j];
			}
		}
	}
	
	/**
	 * Makes a move at the given spot on the board.
	 * @return {@code false} if the move would swap two pieces of the same type, {@code true} otherwise
	 */
	public boolean makeMove(int row, int col) {
		if (board[row][col] == board[row][col+1]) return false;
		movesThusFar.add("Row: " + (row+1) + ", Cols: " + (col+1) + "-" + (col+2));
		char temp = board[row][col];
		board[row][col] = board[row][col+1];
		board[row][col+1] = temp;
		movesLeft--;
		return true;
	}
	
	public void applyGravity() {
		boolean moved;
		do {
			moved = false;
			for (int row = 0; row < 11; row++) {
				for (int col = 0; col < 6; col++) {
					if (board[row][col] == ' ' && board[row+1][col] != ' ') {
						board[row][col] = board[row+1][col];
						board[row+1][col] = ' ';
						moved = true;
					}
				}
			}
		} while (moved);
	}
	
	/**
	 * Clears matches from the board.
	 * @return {@code true} if any matches were cleared, {@code false} otherwise
	 */
	public boolean clearPieces() {
		boolean clearCells[][] = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				clearCells[i][j] = false;
			}
		}
		boolean matchesWereMade = false;
		// Check horizontally:
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 4; col++) {
				if (board[row][col] != ' ' && board[row][col] == board[row][col+1] && board[row][col] == board[row][col+2]) {
					clearCells[row][col] = true;
					clearCells[row][col+1] = true;
					clearCells[row][col+2] = true;
					matchesWereMade = true;
				}
			}
		}
		// Check vertically:
		for (int col = 0; col < 6; col++) {
			for (int row = 0; row < 10; row++) {
				if (board[row][col] != ' ' && board[row][col] == board[row+1][col] && board[row][col] == board[row+2][col]) {
					clearCells[row][col] = true;
					clearCells[row+1][col] = true;
					clearCells[row+2][col] = true;
					matchesWereMade = true;
				}
			}
		}
		// Delete cleared cells:
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 6; col++) {
				if (clearCells[row][col]) {
					board[row][col] = ' ';
				}
			}
		}
		return matchesWereMade;
	}
	
	public boolean boardIsCleared() {
		for (int i = 0; i < 6; i++) {
			if (board[0][i] != ' ') {
				return false;
			}
		}
		return true;
	}
	
}
