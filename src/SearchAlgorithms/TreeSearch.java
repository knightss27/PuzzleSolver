package SearchAlgorithms;

import PuzzleInterfaces.Search;
import PuzzleInterfaces.State;
import SearchUtils.SearchNode;
import SearchUtils.Solution;
import PuzzleInterfaces.Frontier;

import java.util.LinkedList;

// Seth Knights
public abstract class TreeSearch implements Search {

    public Frontier frontier;

    public TreeSearch(Frontier _frontier) {
        frontier = _frontier;
    }

    boolean shouldPruneNode(SearchNode node) {
        return false;
    }

    @Override
    public Solution search(State startState) {
        // make sure our frontier is clear if re-using solver
        frontier.clear();

        // insert the first node into our frontier
        frontier.insert(new SearchNode(startState, null, null, 0));

        while (!frontier.isEmpty()) {
            // get the next node in the frontier
            SearchNode currentNode = frontier.removeNext();

            // check if we are the goal state
            if (currentNode.state.isGoalState()) {
                // create a list for our path
                LinkedList<SearchNode> solvedPath = new LinkedList<SearchNode>();
                SearchNode nodeOnPath = currentNode;

                // look upwards through the tree to find parents
                while (nodeOnPath != null) {
                    solvedPath.addFirst(nodeOnPath);
                    nodeOnPath = nodeOnPath.parent;
                }

                // return the path we found
                return new Solution(solvedPath);
            }

            // add new children to the tree
            for (int i = 0; i < currentNode.children.size(); i++) {
                SearchNode newNode = new SearchNode(currentNode.children.get(i), currentNode, currentNode.actions.get(i), currentNode.depth + 1);
                if (!shouldPruneNode(newNode)) {
                    frontier.insert(newNode);
                }
            }
        }

        return null;
    }
}
