package RushHourPuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;
import TilePuzzle.TilePuzzleAction;
import TilePuzzle.TilePuzzleState;

import java.util.List;

public class RushHourState implements State {

    public RushHourBitState state = new RushHourBitState();
    public RushHourBitState horizontalState = new RushHourBitState();
    public RushHourBitState verticalState = new RushHourBitState();

    public RushHourState() {
        setVehicle(0, 3, Car.Orientation.HORIZONTAL);
        setVehicle(5, 3, Car.Orientation.VERTICAL);
    }

    void setVehicle(int position, int length, int orientation) {
        if (orientation == Car.Orientation.HORIZONTAL) {
            for (int i = 0; i < length; i++) {
                state.setBit(position + i);
                horizontalState.setBit(position + i);
            }
        } else {
            for (int i = 0; i < length; i++) {
                state.setBit(position + 6 * i);
                verticalState.setBit(position + 6 * i);
            }
        }
    }

    public static class RushHourBitState {

        // 6x6 board
        private long state;

        public RushHourBitState() {
            state = 0L;
        }

        public RushHourBitState(long _state) {
            state = _state;
        }

        void setBit(int position) {
            state = state | (1 << position);
        }

        int getBit(int position) {
            return (int) ((state >> position) & 1);
        }

        public long getLong() {
            return state;
        }
    }

    @Override
    public List<Action> listActions() {
        return null;
    }

    @Override
    public boolean isGoalState() {
        return false;
    }

    @Override
    public void display() {
        StringBuilder board = new StringBuilder("--------------------------\n");
        for (int i = 0; i < 36; i += 6) {
            StringBuilder line = new StringBuilder("|");
            for (int j = i; j < i+6; j++) {
                line.append(String.format("%3s",state.getBit(j))).append(" ");
            }
            line.append("|\n");
            board.append(line);
        }
        board.append("--------------------------\n");
        System.out.println(board);
    }

    @Override
    public State duplicate() {
        return null;
    }

    @Override
    public void performAction(Action action) {
        RushHourAction newAction = (RushHourAction) action;

        if (newAction.car <= 0 || newAction.car >= 36 || state.getBit(newAction.car) != 1) {
            return;
        }

//        if (newAction.orientation == CarOrientation.HORIZONTAL) {
//
//        }
    }

    @Override
    public int heuristic() {
        return 0;
    }
}
