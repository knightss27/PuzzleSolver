package SearchAlgorithms;

import SearchUtils.Frontiers.FrontierStack;

public class DepthFirstSearch extends GrandaprentPruningTreeSearch {
    public DepthFirstSearch() {
        super(new FrontierStack());
    }
}
