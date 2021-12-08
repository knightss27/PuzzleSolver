package games.connect4;

// Author: Andrew Merrill

import main.*;

public class SethsConnect4Evaluator implements Evaluator {

    public int evaluate(State state) {
        Connect4State board = (Connect4State) state;
        if (board.blackWon())
            return 1000000;
        else if (board.redWon())
            return -1000000;
        else {
            int total = 0;
            int total4s = 0;
            int total3s = 0;
            int total2s = 0;
            for (SearchNode move : board.listChildren()) {
                if (move.state.isGameOver() && move.state.getWinner() == board.getSideToPlay()) {
                    total4s += 1;
                } else {
                    for (SearchNode oppMove : move.state.listChildren()) {
                        if (oppMove.state.isGameOver() && oppMove.state.getWinner() == board.getOtherSide()) {
                            total -= 1000;
                        }
//                        if (((Connect4State) oppMove.state).getCell(((Connect4Action) oppMove.action).col))
                    }
                }
            }
            return total4s * 10000 + total;
        }
    }

    public String toString() {
        return "C4-Seth";
    }

}