package games.othello;

// Author: Andrew Merrill

import main.*;

public class OthelloEvaluator0 implements Evaluator {

    public int evaluate(State state) {
        OthelloState board = (OthelloState) state;
        return board.countBlackPieces() - board.countWhitePieces();
    }

    public String toString() {
        return "O0";
    }

}