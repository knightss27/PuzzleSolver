package players;

import main.*;

import java.util.List;

public class MiniMaxPlayer extends Player {
    final Evaluator evaluator;
    final int maxDepth;

    public MiniMaxPlayer(int maxDepth, Evaluator evaluator) {
        this.evaluator = evaluator;
        this.maxDepth = maxDepth;
    }

    public SearchNode pickMove(State currentState) {
       return null;
    }

    public String toString() {
        return "MM:" + maxDepth + " " + evaluator;
    }
}
