package SearchAlgorithms;

import PuzzleInterfaces.Search;
import PuzzleInterfaces.State;
import SearchUtils.Solution;
import SearchUtils.Solver;

public class IDAStarSearch implements Search {

    public IDAStarSearch(){}

    @Override
    public Solution search(State startState) {
        int currentLimit = startState.heuristic();
        Solution solution = null;
        while (solution == null) {
            DepthLimitedAStarSearch search = new DepthLimitedAStarSearch(currentLimit);
            solution = Solver.solve(startState, search);
            currentLimit = search.smallestThreshold;
        }
        return solution;
    }

    @Override
    public String toString() {
        return "Iterative Deepening A* Search";
    }
}
