// Seth Knights
// UNFINISHED
package RushHourPuzzle;

import PuzzleInterfaces.Action;

public class RushHourAction implements Action {

    public Car car;
    public int shift;

    public RushHourAction(Car car, int shift) {
        this.car = car;
        this.shift = shift;
    }

    @Override
    public void display() {
        System.out.println("Moves car at " + car.position + " " + (car.orientation == Car.Orientation.HORIZONTAL ? "HORIZONTALLY" : "VERTICALLY") + " by " + shift + " units.");
    }

    @Override
    public int getCost() {
        return 0;
    }
}
