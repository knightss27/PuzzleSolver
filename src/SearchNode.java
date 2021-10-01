import java.util.List;

public class SearchNode {
    public State state;
    public SearchNode parent;
    public Action creatingAction;

    public List<State> children;
    public List<Action> actions;

    public int cost;
    public int depth;

    public SearchNode(State _state, SearchNode _parent, Action creatingAction) {
        state = _state;
        parent = _parent;

        actions = state.listActions();
        for (Action action : actions) {
            State newState = state.duplicate();
            newState.performAction(action);

            children.add(newState);
        }
    }

}
