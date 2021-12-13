// Seth Knights
package games.connect4;

import main.*;

public class SethsConnect4Evaluator implements Evaluator {

    public int evaluate(State state) {
        Connect4State board = (Connect4State) state;

        boolean isBlack = board.getSideToPlay() == Connect4State.BLACK;
        if (board.blackWon())
            return 1000000;
        else if (board.redWon())
            return -1000000;
        else {
            // In multiple 100-game trials, this algorithm appears to beat C4-0 100% of the time.

            int total4s = Connect4Utility.countRuns(board, 4, isBlack ? 3 : 0, isBlack ? 0 : 3, 1);
            int total3s = Connect4Utility.countRuns(board, 4, isBlack ? 2 : 0, isBlack ? 0 : 2, 2);
            int total2s = Connect4Utility.countRuns(board, 4, isBlack ? 1 : 0, isBlack ? 0 : 1, 3);

            // I am only using these separate counts so I can weigh them differently
            int total4sOpp = Connect4Utility.countRuns(board, 4, isBlack ? 0 : 3, isBlack ? 3 : 0, 1);
            int total3sOpp = Connect4Utility.countRuns(board, 4, isBlack ? 0 : 2, isBlack ? 2 : 0, 2);
            int total2sOpp = Connect4Utility.countRuns(board, 4, isBlack ? 0 : 1, isBlack ? 1 : 0, 3);

            return (total4s * 10000 + total3s * 1000 + total2s * 100 - total4sOpp * 12000 - total3sOpp * 1200 - total2sOpp * 100) * (isBlack ? 1 : -1);
        }
    }

    public String toString() {
        return "C4-Seth";
    }

}