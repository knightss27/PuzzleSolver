package SearchAlgorithms;

import SearchUtils.SearchNode;

public class DepthLimitedAStarSearch extends DepthFirstSearch {

    private int limit;
    protected int smallestThreshold = Integer.MAX_VALUE;

    public DepthLimitedAStarSearch(int _heuristicLimit) {
        super();
        limit = _heuristicLimit;
    }

    @Override
    boolean shouldPruneNode(SearchNode node) {
        if (node.evaluate() < smallestThreshold && node.evaluate() > limit) {
            smallestThreshold = node.evaluate();
        }

         return super.shouldPruneNode(node) || node.evaluate() > limit;
    }
}
