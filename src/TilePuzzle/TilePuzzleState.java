package TilePuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TilePuzzleState implements State {

    public BitState state = new BitState();

    public static class BitState {

        private long state;

        public BitState() {
            state = 0L;
            for (int i = 15; i >= 0; i--) {
                setPlace(i, i);
            }
        }

        public BitState(long _state) {
            state = _state;
        }

        public int getPlace(int place) {
            return (int) ((state >> (place * 4)) & 15);
        }

        public void setPlace(long place, long num) {
            if (num < 16 && place < 16) {
                state = ((state & ~(15L << (place * 4))) | (num << (place * 4)));
            }
        }

        public long getLong() {
            return state;
        }
    }

    public TilePuzzleState() {
        display();
    }
    public TilePuzzleState(long _state) {
        state = new BitState(_state);
    }

    public long getLong() {
        return state.getLong();
    }

    public void randomize() {
        for (int i = 0; i < 10; i++) {
            List<Action> actions = listActions();
            if (actions.size() == 0) {
                return;
            }
            performAction(actions.get((int) (Math.random() * actions.size())));
        }
    }

    @Override
    public List<Action> listActions() {
        ArrayList<Action> actions = new ArrayList<>();
        int zeroIndex = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (state.getPlace(y * 4 + x) == 0) {
                    zeroIndex = y * 4 + x;
                }
            }
        }

//        System.out.println(zeroIndex);

        if (zeroIndex % 4 == 0) {
            actions.add(new TilePuzzleAction(zeroIndex+1, zeroIndex));
        } else if (zeroIndex % 4 == 3) {
            actions.add(new TilePuzzleAction(zeroIndex-1, zeroIndex));
        } else {
            actions.add(new TilePuzzleAction(zeroIndex+1, zeroIndex));
            actions.add(new TilePuzzleAction(zeroIndex-1, zeroIndex));
        }

        if (zeroIndex / 4 == 0) {
            actions.add(new TilePuzzleAction(zeroIndex+4, zeroIndex));
        } else if (zeroIndex / 4 == 3) {
            actions.add(new TilePuzzleAction(zeroIndex-4, zeroIndex));
        } else {
            actions.add(new TilePuzzleAction(zeroIndex+4, zeroIndex));
            actions.add(new TilePuzzleAction(zeroIndex-4, zeroIndex));
        }

        return actions;
    }

    @Override
    public boolean isGoalState() {
        for (int i = 15; i >= 0; i--) {
            if (i != state.getPlace(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void display() {
        StringBuilder board = new StringBuilder("------------------\n");
        for (int i = 0; i < 16; i += 4) {
            StringBuilder line = new StringBuilder("|");
            for (int j = i; j < i+4; j++) {
                line.append(String.format("%3s",state.getPlace(j))).append(" ");
            }
            line.append("|\n");
            board.append(line);
        }
        board.append("------------------\n");
        System.out.println(board);
    }

    @Override
    public State duplicate() {
        return new TilePuzzleState(state.getLong());
    }

    @Override
    public void performAction(Action action) {
        TilePuzzleAction newAction = (TilePuzzleAction) action;

        if (state.getPlace(newAction.moveTo) != 0) {
            System.out.println("INVALID ACTION");
            return;
        }

        state.setPlace(newAction.moveTo, state.getPlace(newAction.moveFrom));
        state.setPlace(newAction.moveFrom, 0);
//        actionHistory.add(newAction);
    }

    @Override
    public int heuristic() {
        int total = 0;
        for (int placeLookingAt = 0; placeLookingAt < 16; placeLookingAt++) {

            int placeToGo = state.getPlace(placeLookingAt);
            if (placeToGo == 0) {
                continue;
            }

            int fromX = placeLookingAt % 4;
            int fromY = placeLookingAt / 4;
            int toX = placeToGo % 4;
            int toY = placeToGo / 4;

            total += Math.abs(toX - fromX) + Math.abs(toY - fromY);
        }

        return total;
    }

    @Override
    public boolean equals(Object o) {
        TilePuzzleState that = (TilePuzzleState) o;
        return that.state.getLong() == state.getLong();
    }
}
