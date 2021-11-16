// Seth Knights
package TilePuzzle;

import PuzzleInterfaces.Action;
import PuzzleInterfaces.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TilePuzzleState implements State {

    public BitState state;
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

    public TilePuzzleState(long _state) {
        state = new BitState(_state);
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

    private int measureNumConflictsInRow(boolean[] arr) {
        int count = 0;
        for (boolean member : arr) {
            if (member) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int heuristic() {
//        if (calculatedHeuristic >= 0) {
//            return calculatedHeuristic;
//        }
        int total = 0;

        for (int placeLookingAt = 0; placeLookingAt < 16; placeLookingAt++) {

            int placeToGo = state.getPlace(placeLookingAt);
            if (placeToGo == 0) {
                continue;
            }

            total += Math.abs(placeToGo % 4 - placeLookingAt % 4) + Math.abs(placeToGo / 4 - placeLookingAt / 4);
        }

        boolean[][] conflicts = new boolean[4][4];
        for (int x = 0; x < 4; x++) {
            // calculate row conflicts
            for (int y = 0; y < 4; y++) {
                int placeToGo = state.getPlace(y * 4 + x);
                if (placeToGo == 0 || placeToGo % 4 != x || placeToGo / 4 == 3) {
                    continue;
                }

                for (int y2 = y + 1; y2 < 4; y2++) {
                    int conflictTile = state.getPlace(y2 * 4 + x);
                    if (conflictTile != 0 && conflictTile % 4 == x && conflictTile / 4 < y) {
                        conflicts[x][y2] = true;
                        conflicts[y2][x] = true;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(conflicts));

        // calculate conflicts;
        int greatestIndex = 0;
        int[] numConflicts = new int[4];
        while (true) {
            for (int i = 0; i < conflicts.length; i++) {
                numConflicts[i] = measureNumConflictsInRow(conflicts[i]);
            }
            for (int i = 0; i < numConflicts.length; i++) {
                if (numConflicts[i] > numConflicts[greatestIndex]) {
                    greatestIndex = i;
                }
            }
            if (numConflicts[greatestIndex] == 0) {
                break;
            }

            total += 2;
            numConflicts[greatestIndex] = 0;
            conflicts[greatestIndex] = new boolean[]{false, false, false, false};
            for (int i = 0; i < 4; i++) {
                conflicts[i][greatestIndex] = false;
            }
        }

        conflicts = new boolean[4][4];
        for (int y = 0; y < 4; y++) {
            // calculate row conflicts
            for (int x = 0; x < 4; x++) {
                int placeToGo = state.getPlace(y * 4 + x);
                if (placeToGo == 0 || placeToGo / 4 != y || placeToGo % 4 == 3) {
                    continue;
                }

                for (int x2 = x + 1; x2 < 4; x2++) {
                    int conflictTile = state.getPlace(y * 4 + x2);
                    System.out.println("looking at " + conflictTile + " from " + placeToGo);
                    if (conflictTile != 0 && conflictTile / 4 == y && conflictTile % 4 < x) {
                        conflicts[x][x2] = true;
                        conflicts[x2][x] = true;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(conflicts));

        // calculate conflicts;
        greatestIndex = 0;
        numConflicts = new int[4];
        while (true) {
            for (int i = 0; i < conflicts.length; i++) {
                numConflicts[i] = measureNumConflictsInRow(conflicts[i]);
            }
            for (int i = 0; i < numConflicts.length; i++) {
                if (numConflicts[i] > numConflicts[greatestIndex]) {
                    greatestIndex = i;
                }
            }
            if (numConflicts[greatestIndex] == 0) {
                break;
            }

            total += 2;
            numConflicts[greatestIndex] = 0;
            conflicts[greatestIndex] = new boolean[]{false, false, false, false};
            for (int i = 0; i < 4; i++) {
                conflicts[i][greatestIndex] = false;
            }
        }


        // Linear conflict rule
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                int placeToGo = state.getPlace(y * 4 + x);
//                if (placeToGo == 0) {
//                    continue;
//                }
//
//                // my code counts 2 conflicts when there should only be one, i.e. (2 3 1) which counts
//                // a conflict between 2, 1 and 3, 1
//
//                // If we are in correct row
//                if (placeToGo / 4 == y) {
//                    for (int x2 = x+1; x2 < 4; x2++) {
//                        int conflictTile = state.getPlace(y * 4 + x2);
//                        if (conflictTile != 0 && conflictTile / 4 == y && conflictTile % 4 < x) {
////                            total += 2;
//                            conflicts[x]++;
//                            conflicts[x2]++;
//                        }
//                    }
//                }
//
//                int greatestIndex = 0;
//                while (true) {
//                    for (int i = 0; i < conflicts.length; i++) {
//                        if (conflicts[i] > conflicts[greatestIndex]) {
//                            greatestIndex = i;
//                        }
//                    }
//                    if (conflicts[greatestIndex] == 0) {
//                        break;
//                    }
//                    total += 2;
//                    conflicts[greatestIndex] = 0;
//                    for (int i = 0; i < conflicts.length; i++) {
//                        if (i != greatestIndex) {
//                            conflicts[i]--;
//                        }
//                    }
//                }
//
//
//                conflicts = new int[]{ 0, 0, 0, 0 };
//                // Same x value == same column
//                if (placeToGo % 4 == x) {
//                    for (int y2 = y+1; y2 < 4; y2++) {
//                        int conflictTile = state.getPlace(y2 * 4 + x);
//                        if (conflictTile != 0 && conflictTile % 4 == x && conflictTile < placeToGo) {
////                            total += 2;
//                            conflicts[y]++;
//                            conflicts[y2]++;
//                        }
//                    }
//                }
//
//                greatestIndex = 0;
//                while (true) {
//                    for (int i = 0; i < conflicts.length; i++) {
//                        if (conflicts[i] > conflicts[greatestIndex]) {
//                            greatestIndex = i;
//                        }
//                    }
//                    if (conflicts[greatestIndex] == 0) {
//                        break;
//                    }
//                    total += 2;
//                    conflicts[greatestIndex] = 0;
//                    for (int i = 0; i < conflicts.length; i++) {
//                        if (i != greatestIndex) {
//                            conflicts[i]--;
//                        }
//                    }
//                }
//            }
//        }

        calculatedHeuristic = total;
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TilePuzzleState that = (TilePuzzleState) o;
        return that.state.getLong() == state.getLong();
    }
}
