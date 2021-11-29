package players;

import main.*;

public class AlphaBetaPlayer extends Player {
    final Evaluator evaluator;
    final int maxDepth;

    public AlphaBetaPlayer(int maxDepth, Evaluator evaluator) {
        this.evaluator = evaluator;
        this.maxDepth = maxDepth;
    }

    public SearchNode pickMove(State currentState) {
        return null;
    }

    public String toString() {
        return "AB:" + maxDepth + " " + evaluator;
    }
}
