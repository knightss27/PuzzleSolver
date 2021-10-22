package SearchUtils.Frontiers;

import SearchUtils.SearchNode;
import PuzzleInterfaces.Frontier;

import java.util.LinkedList;

public class FrontierQueue implements Frontier {

    private LinkedList<SearchNode> frontier = new LinkedList<SearchNode>();

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
        frontier.addLast(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier.removeFirst();
    }
}
