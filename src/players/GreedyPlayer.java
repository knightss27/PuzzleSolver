package players;

// Author: Andrew Merrill

import main.*;

import java.util.List;

public class GreedyPlayer extends Player {
    final Evaluator evaluator;

    public GreedyPlayer(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public SearchNode pickMove(State currentState) {
        List<SearchNode> children = currentState.listChildren();
        SearchNode bestNode = null;
        if (currentState.getSideToPlay() == Side.ONE) { // Maxi side
            int bestVal = Integer.MIN_VALUE;
            for (SearchNode child : children) {
                int eval = evaluator.evaluate(child.state);
                if (eval > bestVal) {
                    bestVal = eval;
                    bestNode = child;
                }
            }
        } else { // Mini side
            int bestVal = Integer.MAX_VALUE;
            for (SearchNode child : children) {
                int eval = evaluator.evaluate(child.state);
                if (eval < bestVal) {
                    bestVal = eval;
                    bestNode = child;
                }
            }
        }
        return bestNode;
    }


    public String toString() {
        return "Greedy:" + evaluator.toString();
    }
}