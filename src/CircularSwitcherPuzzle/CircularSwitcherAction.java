package CircularSwitcherPuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

public class CircularSwitcherAction implements Action {

    // the first index for the four to be switched
    int index;

    CircularSwitcherAction(int index) {
        this.index = index;
    }

    @Override
    public void display() {
        System.out.println("Switch at index " + index);
    }

    @Override
    public int getCost() {
        return 0;
    }
}
