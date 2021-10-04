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
        frontier.add(node);
    }

    @Override
    public SearchNode removeNext() {
        return frontier.poll();
    }
}
