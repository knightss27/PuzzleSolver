package SearchAlgorithms;

import SearchUtils.Frontiers.FrontierPriorityQueue;

public class AStarSearch extends GrandaprentPruningTreeSearch {
    public AStarSearch() {
        super(new FrontierPriorityQueue());
    }
}
