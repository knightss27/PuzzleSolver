// Seth Knights
// UNFINISHED
package RushHourPuzzle;

import PuzzleInterfaces.Action;

public class RushHourAction implements Action {

    public int car;
    public int shift;
    public int orientation;

    public RushHourAction(int car, int orientation, int shift) {
        this.car = car;
        this.orientation = orientation;
        this.shift = shift;
    }

    @Override
    public void display() {

    }

    @Override
    public int getCost() {
        return 0;
    }
}
