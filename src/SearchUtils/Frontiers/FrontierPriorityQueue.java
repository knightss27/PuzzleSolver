// Seth Knights
package SearchUtils.Frontiers;

import SearchUtils.SearchNode;
import PuzzleInterfaces.Frontier;

import java.util.PriorityQueue;

public class FrontierPriorityQueue implements Frontier {

    private PriorityQueue<SearchNode> frontier = new PriorityQueue<>();

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
        return frontier.poll();
    }
}
