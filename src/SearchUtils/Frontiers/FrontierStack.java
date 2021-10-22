package SearchUtils.Frontiers;

import SearchUtils.SearchNode;
import PuzzleInterfaces.Frontier;

import java.util.Stack;

public class FrontierStack implements Frontier {
    private Stack<SearchNode> frontier = new Stack<SearchNode>();

    @Override
    public void clear() {
        frontier.clear();
    }

    @Override
    public boolean isEmpty() {
        return frontier.isEmpty();
    }

    @Override
    public void insert(SearchNode node) {
        frontier.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier.pop();
    }
}
