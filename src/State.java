import java.util.List;

public interface State {
    // public constructor (maybe with inputs) that starts at a legal initial state

    // returns a list of the legal actions possible from this state
    List<Action> listActions();

    // returns true if this state is a Goal State, and false otherwise
    boolean isGoalState();

    // displays a human-readable form of this state
    void display();

    // returns a new state that exactly duplicates the current one
    State duplicate();

    // applies the given action to the current state
    void performAction(Action action);

    // rolls back the given action from the current state
    // commented out for now, but documented in case we need it later
    // public void undoAction(Action action);

    // returns true if the otherState is the same as this one
    //   it is safe to cast otherState to your state class
    boolean equals(Object otherState);
}