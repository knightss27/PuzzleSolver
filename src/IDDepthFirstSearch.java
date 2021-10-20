public class IDDepthFirstSearch implements Search {

    int limit;

    IDDepthFirstSearch(int depthLimit) {
        limit = depthLimit;
    }

    IDDepthFirstSearch() {
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
