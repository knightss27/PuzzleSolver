package SearchAlgorithms;

import PuzzleInterfaces.Search;
import PuzzleInterfaces.State;
import SearchUtils.Solution;
import SearchUtils.Solver;

public class IDDepthFirstSearch implements Search {

    int limit;

    IDDepthFirstSearch(int depthLimit) {
        limit = depthLimit;
    }

    public IDDepthFirstSearch() {
        limit = -1;
    }

    @Override
    public Solution search(State startState) {
        int currentLimit = 0;
        Solution solution = null;
        while (currentLimit <= limit || solution == null) {
            solution = Solver.solve(startState, new DepthLimitedSearch(currentLimit));
            currentLimit++;
        }
        return solution;
    }

    @Override
    public String toString() {
        return "Iterative Deepening DFS";
    }
}
