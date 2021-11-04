package TilePuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TilePuzzleState implements State {

    public BitState state = new BitState();
    private int calculatedHeuristic = -1;

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
        LinkedList<Action> actions = new LinkedList<>();
        int zeroIndex = 0;
        for (int i = 0; i < 16; i++) {
            if (state.getPlace(i) == 0) {
                zeroIndex = i;
                break;
            }
        }

        if (zeroIndex % 4 != 0) {
            actions.add(new TilePuzzleAction(zeroIndex-1, zeroIndex));
        }

        if (zeroIndex % 4 != 3) {
            actions.add(new TilePuzzleAction(zeroIndex+1, zeroIndex));
        }

        if (zeroIndex > 3) {
            actions.add(new TilePuzzleAction(zeroIndex-4, zeroIndex));
        }

        if (zeroIndex < 12) {
            actions.add(new TilePuzzleAction(zeroIndex+4, zeroIndex));
        }

        return actions;
    }

    @Override
    public boolean isGoalState() {
        return state.getLong() == 0xfedcba9876543210L;
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
    }

    @Override
    public int heuristic() {
        if (calculatedHeuristic >= 0) {
            return calculatedHeuristic;
        }
        int total = 0;
        int place1 = 0;
        int place2 = 0;
        for (int placeLookingAt = 0; placeLookingAt < 16; placeLookingAt++) {

            int placeToGo = state.getPlace(placeLookingAt);
            if (placeToGo == 0) {
                continue;
            }

            total += Math.abs(placeToGo % 4 - placeLookingAt % 4) + Math.abs(placeToGo / 4 - placeLookingAt / 4);


            if (placeToGo == 1) {
                place1 = placeLookingAt;
            } else if (placeToGo == 4) {
                place2 = placeLookingAt;
            }
        }

        // Last move rule
//        if (place1 % 4 > 0 && place2 / 4 > 0) {
//            total += 2;
//        }


        // Linear conflict: Need to ask jeffrey
        // For ever square:
        // If it's on the correct column
            // check all the other squares on the column to see if they need to be moved beyond our current one
            // if they do then total += 2
        // If it's on the correct row
            // check all the other squares on the row to see if they need to be moved beyond the current one
            // if they do then total += 2

        // Linear conflict rule
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int placeToGo = state.getPlace(y * 4 + x);
                if (placeToGo == 0) {
                    continue;
                }

                // Same y value == same row
                if (placeToGo / 4 == y) {
                    for (int x2 = x+1; x2 < 4; x2++) {
                        int conflictTile = state.getPlace(y * 4 + x2);
                        if (conflictTile != 0 && conflictTile / 4 == y && conflictTile % 4 < x) {
                            total += 2;
                        }
                    }
                }

                // Same x value == same column
                if (placeToGo % 4 == x) {
                    for (int y2 = y+1; y2 < 4; y2++) {
                        int conflictTile = state.getPlace(y2 * 4 + x);
                        if (conflictTile != 0 && conflictTile % 4 == x && conflictTile / 4 < y) {
                            total += 2;
                        }
                    }
                }
            }
        }

        calculatedHeuristic = total;
        return total;
    }

    @Override
    public boolean equals(Object o) {
        TilePuzzleState that = (TilePuzzleState) o;
        return that.state.getLong() == state.getLong();
    }
}
