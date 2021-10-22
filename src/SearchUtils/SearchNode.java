package SearchUtils;

import java.util.LinkedList;
import java.util.List;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

public class SearchNode implements Comparable {
    public State state;
    public SearchNode parent;
    public Action creatingAction;

    public List<State> children = new LinkedList<State>();
    public List<Action> actions;

    public int cost;
    public int depth;

    public SearchNode(State _state, SearchNode _parent, Action _creatingAction, int _depth) {
        state = _state.duplicate();
        parent = _parent;
        creatingAction = _creatingAction;
        depth = _depth;

        actions = state.listActions();
        for (Action action : actions) {
            State newState = state.duplicate();
            newState.performAction(action);

            children.add(newState);
        }
    }

    public int evaluate() {
        return state.heuristic() + depth;
    }

    @Override
    public int compareTo(Object o) {
        SearchNode node = (SearchNode) o;
        if (node.evaluate() < evaluate()) {
            return 1;
        } else if (node.evaluate() == evaluate()) {
            return 0;
        }
        return -1;
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
