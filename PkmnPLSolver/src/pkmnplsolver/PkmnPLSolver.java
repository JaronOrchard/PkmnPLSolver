package pkmnplsolver;

import java.io.IOException;
import java.util.List;

public class PkmnPLSolver {

	public static void main(String[] argv) throws IOException {
		solve(Puzzles.class3Puzzle29());
	}
	
	private static void solve(GameState state) throws IOException {
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 5; col++) {
				GameState newState = new GameState(state);
				if (newState.makeMove(row, col)) {				// Make a move if possible
					do {
						newState.applyGravity();				// Apply gravity
					} while (newState.clearPieces());			// Clear matches (repeat gravity)
					if (newState.boardIsCleared()) {			// If solved, yay
						List<String> moves = newState.getMovesThusFar();
						System.out.println("** Solution found!  Press Enter to step through...");
						for (int i = 0; i < moves.size(); i++) {
							System.in.read(); System.in.read(); // (Twice because CR-LF on Windows)
							System.out.println("  Move " + (i+1) + ": " + moves.get(i));
						}
						System.out.println("** End solution");
					} else if (newState.getMovesLeft() > 0) {	// If moves are left, do DFS
						solve(newState);
					}
				}
			}
		}
	}
	
}
