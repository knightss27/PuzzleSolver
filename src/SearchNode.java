import java.util.LinkedList;
import java.util.List;

public class SearchNode {
    public State state;
    public SearchNode parent;
    public Action creatingAction;

    public List<State> children = new LinkedList<State>();
    public List<Action> actions;

    public int cost;
    public int depth;

    public SearchNode(State _state, SearchNode _parent, Action _creatingAction) {
        state = _state;
        parent = _parent;
        creatingAction = _creatingAction;

        actions = state.listActions();
        for (Action action : actions) {
            State newState = state.duplicate();
            newState.performAction(action);

            children.add(newState);
        }
    }

    @Override
    public String toString() {
        return "SearchNode{" +
                "state=" + state +
                ", parent=" + parent +
                ", creatingAction=" + creatingAction +
                ", children=" + children +
                ", actions=" + actions +
                ", cost=" + cost +
                ", depth=" + depth +
                '}';
    }
}
