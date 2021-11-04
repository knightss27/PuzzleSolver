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
        DepthLimitedAStarSearch algo = new DepthLimitedAStarSearch(currentLimit);
        while (solution == null) {
            algo.limit = currentLimit;
            algo.smallestThreshold = Integer.MAX_VALUE;

            solution = Solver.solve(startState, algo);
            currentLimit = algo.smallestThreshold;
        }
        return solution;
    }

    @Override
    public String toString() {
        return "Iterative Deepening A* Search";
    }
}
