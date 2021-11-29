package games.connect4;

// Author: Andrew Merrill

import main.*;

public class Connect4Evaluator0 implements Evaluator {

    public int evaluate(State state) {
        Connect4State board = (Connect4State) state;
        if (board.blackWon())
            return 1000000;
        else if (board.redWon())
            return -1000000;
        else
            return 0;
    }

    public String toString() {
        return "C4-0";
    }

}
