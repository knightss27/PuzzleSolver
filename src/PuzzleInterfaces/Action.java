// Seth Knights
package PuzzleInterfaces;

public interface Action {

    // displays a human-readable form of this action
    void display();

    // returns the cost of taking this action (often 1)
    int getCost();
}