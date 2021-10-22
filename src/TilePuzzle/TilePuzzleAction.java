package TilePuzzle;

import PuzzleInterfaces.Action;

public class TilePuzzleAction implements Action {

    public int moveFrom;
    public int moveTo;

    public TilePuzzleAction(int _moveFrom, int _moveTo) {
        moveFrom = _moveFrom;
        moveTo = _moveTo;
    }

    @Override
    public void display() {
        System.out.println("Move tile in place " + moveFrom +  " to " + moveTo);
    }

    @Override
    public String toString() {
        return "TilesAction{" +
                "moveFrom=" + moveFrom +
                ", moveTo=" + moveTo +
                '}';
    }

    @Override
    public int getCost() {
        return 0;
    }
}
