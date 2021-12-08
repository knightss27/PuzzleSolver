package CircularSwitcherPuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircularSwitcherState implements State {

    int[] state = new int[20];
    List<Action> actions = null;

    public CircularSwitcherState() {
        for (int i = 0; i < state.length; i++) {
            state[i] = i+1;
        }

        listActions();
    }

    public CircularSwitcherState(int[] state) {
        this.state = state;
        listActions();
    }

    public void randomize(int depth) {
        int lastIndex = -1;
        for (int i = 0; i < depth; i++) {
            int index = (int) (Math.random() * state.length);
            while (index == lastIndex) {
                index = (int) (Math.random() * state.length);
            }
            performAction(new CircularSwitcherAction(index));
            lastIndex = index;
        }
    }

    @Override
    public List<Action> listActions() {
        if (this.actions != null) {
            return this.actions;
        }

        ArrayList<Action> actions = new ArrayList<>(20);
        for (int i = 0; i < state.length; i++) {
            actions.add(new CircularSwitcherAction(i));
        }
        this.actions = actions;

        return actions;
    }

    @Override
    public boolean isGoalState() {
        for (int i = 0; i < state.length; i++) {
            if (i != state[i]-1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void display() {
        System.out.println(Arrays.toString(state));
    }

    @Override
    public State duplicate() {
        return new CircularSwitcherState(this.state.clone());
    }

    @Override
    public void performAction(Action action) {
        CircularSwitcherAction newAction = (CircularSwitcherAction) action;

//        System.out.println("trying action: ");
//        newAction.display();

        if (newAction.index >= state.length || newAction.index < 0) {
            System.out.println("INVALID ACTION");
            return;
        }

        int secondIndex = newAction.index + 3 >= 20 ? newAction.index + 3 - 20 : newAction.index + 3;
        int old = state[secondIndex];
        state[secondIndex] = state[newAction.index];
        state[newAction.index] = old;

        secondIndex = newAction.index + 2 >= 20 ? newAction.index + 2 - 20 : newAction.index + 2;
        old = state[secondIndex];
        state[secondIndex] = state[newAction.index + 1 >= 20 ? newAction.index + 1 - 20 : newAction.index + 1];
        state[newAction.index + 1 >= 20 ? newAction.index + 1 - 20 : newAction.index + 1] = old;
    }

    @Override
    public int heuristic() {
        int total = 0;

        // Out of place counting heuristic
//        total += outOfPlace();
//        total += outOfPlace()/2;
//        total += outOfPlace()/4;

//        total += consecutiveCount();
        total += consecutiveCount()/2; // Gets to 13 pretty easily



        return total;
    }

    private int outOfPlace() {
        int outOfPlace = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] != i+1) {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    private int consecutiveCount() {
        int consecutive = 0;
        for (int i = 1; i < state.length; i++) {
            if (state[i] == i+1 && state[i-1] == i) {
                consecutive++;
            }
        }

        return 20-consecutive;
    }
}
