// Seth Knights
package SearchAlgorithms;

import SearchUtils.Frontiers.FrontierPriorityQueue;

public class AStarSearch extends GrandparentPruningTreeSearch {
    public AStarSearch() {
        super(new FrontierPriorityQueue());
    }
}
