// Seth Knights
package SearchAlgorithms;

import SearchUtils.Frontiers.FrontierStack;

public class DepthFirstSearch extends GrandparentPruningTreeSearch {
    public DepthFirstSearch() {
        super(new FrontierStack());
    }
}
