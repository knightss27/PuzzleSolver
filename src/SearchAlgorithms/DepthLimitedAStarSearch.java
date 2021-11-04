package SearchAlgorithms;

import SearchUtils.SearchNode;

public class DepthLimitedAStarSearch extends DepthFirstSearch {

    public int limit;
    protected int smallestThreshold = Integer.MAX_VALUE;

    public DepthLimitedAStarSearch(int _heuristicLimit) {
        super();
        limit = _heuristicLimit;
    }

    @Override
    boolean shouldPruneNode(SearchNode node) {
        int heuristic = node.evaluate();
        if (heuristic < smallestThreshold && heuristic > limit) {
            smallestThreshold = heuristic;
        }

         return super.shouldPruneNode(node) || heuristic > limit;
    }
}
