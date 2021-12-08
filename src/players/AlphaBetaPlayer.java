package players;

import games.connect4.Connect4State;
import main.*;

public class AlphaBetaPlayer extends Player {
    final Evaluator evaluator;
    final int maxDepth;

    int alpha;
    int beta;

    public AlphaBetaPlayer(int maxDepth, Evaluator evaluator) {
        this.evaluator = evaluator;
        this.maxDepth = maxDepth;
    }

    public SearchNode pickMove(State currentState) {
        Connect4State connect4State = (Connect4State) currentState;

        boolean isMaxi = connect4State.getSideToPlay() == Side.ONE;
        int optimized = isMaxi ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        SearchNode bestNode = connect4State.listChildren().get(0);

        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;

        for (SearchNode child : connect4State.listChildren()) {
            int evaluated = childEvaluation(child.state, 0, !isMaxi);
            if ((!isMaxi && evaluated < optimized) || (isMaxi && evaluated > optimized)) {
                optimized = evaluated;
                bestNode = child;
            }
        }
        return bestNode;
    }

    private int childEvaluation(State state, int depth, boolean maximize) {
        if (depth >= maxDepth || state.isGameOver()) {
            return evaluator.evaluate(state);
        } else {
            int optimized = maximize ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (SearchNode child : state.listChildren()) {
                int evaluated = childEvaluation(child.state, depth+1, !maximize);
                if ((maximize && evaluated > optimized) || (!maximize && evaluated < optimized)) {
                    optimized = evaluated;
                }

                if (maximize && optimized > alpha) {
                    alpha = optimized;
                } else if (!maximize && optimized < beta) {
                    beta = optimized;
                }

                if (maximize && optimized >= beta) {
                    break;
                } else if (!maximize && optimized <= alpha) {
                    break;
                }
            }

            return optimized;
        }
    }

    public String toString() {
        return "AB:" + maxDepth + " " + evaluator;
    }
}
