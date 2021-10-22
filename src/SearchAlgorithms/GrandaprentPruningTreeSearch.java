package SearchAlgorithms;

import PuzzleInterfaces.Frontier;
import SearchUtils.SearchNode;

public class GrandaprentPruningTreeSearch extends TreeSearch {
    public GrandaprentPruningTreeSearch(Frontier _frontier) {
        super(_frontier);
    }

    @Override
    boolean shouldPruneNode(SearchNode node) {
        if (node.parent == null || node.parent.parent == null) {
            return false;
        }
        return node.parent.parent.equals(node);
    }
}
