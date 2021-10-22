package SillyPuzzle;

import PuzzleInterfaces.Action;

public class SillyPuzzleAction implements Action {

    int delta;

    public SillyPuzzleAction(int delta) {
        this.delta = delta;
    }

    @Override
    public void display() {
        System.out.println("change by " + delta);
    }

    @Override
    public int getCost() {
        return 1;
    }

}