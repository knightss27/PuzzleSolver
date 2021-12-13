package games.connect4;

// Author: Andrew Merrill

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
            // could be good to try and play in the middle to start
            if (board.getCell(0, 4) == board.getSideToPlay() && Connect4Utility.countRuns(board, 7, isBlack ? 1 : 0, isBlack ? 0 : 1, 6) == 1) {
                return 10000000 * (isBlack ? 1 : -1);
            }

            int total = 0;
            int total4s = 0;
            int total3s = 0;
            int total2s = 0;
            total4s += Connect4Utility.countRuns(board, 4, isBlack ? 3 : 0, isBlack ? 0 : 3, 1);
            total3s += Connect4Utility.countRuns(board, 4, isBlack ? 2 : 0, isBlack ? 0 : 2, 2);
            total2s += Connect4Utility.countRuns(board, 3, isBlack ? 2 : 0, isBlack ? 0 : 2, 1);

            return (total4s * 10000 + total3s * 1000 + total2s * 100) * (isBlack ? 1 : -1);
        }
    }

    public String toString() {
        return "C4-Seth";
    }

}