// Seth Knights
package SearchAlgorithms;

import SearchUtils.SearchNode;

public class DepthLimitedSearch extends DepthFirstSearch {

    int limit;

    public DepthLimitedSearch(int depthLimit) {
        super();
        limit = depthLimit;
    }

    @Override
    boolean shouldPruneNode(SearchNode node) {
        return super.shouldPruneNode(node) || node.depth >= limit;
    }
}
