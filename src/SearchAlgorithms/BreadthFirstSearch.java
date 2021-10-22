package SearchAlgorithms;

import SearchUtils.Frontiers.FrontierQueue;

public class BreadthFirstSearch extends TreeSearch {
    public BreadthFirstSearch() {
        super(new FrontierQueue());
    }
}
