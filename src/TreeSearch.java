import java.util.LinkedList;

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

        // insert the first node into our frontier
        frontier.insert(new SearchNode(startState, null, null));

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
                    solvedPath.add(nodeOnPath);
                    nodeOnPath = nodeOnPath.parent;
                }

                // return the path we found
                return new Solution(solvedPath);
            }

            //
            for (int i = 0; i < currentNode.children.size(); i++) {
                SearchNode newNode = new SearchNode(currentNode.children.get(i), currentNode, currentNode.actions.get(i));
                if (!shouldPruneNode(newNode)) {
                    frontier.insert(newNode);
                }
            }
        }

        return null;
    }
}
