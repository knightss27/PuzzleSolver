import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TilesState implements State {

    private BitState state = new BitState(true);
    private LinkedList<TilesAction> actionHistory = new LinkedList<TilesAction>();

    private static class BitState {

        private long state;

        public BitState(boolean useRandomState) {
            state = 0L;
            for (int i = 15; i >= 0; i--) {
                setPlace(i, i);
            }
        }

        public BitState(long _state) {
            state = _state;
        }

        public int getPlace(int place) {
            return (int) ((state >> place * 4) & 15);
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

    public TilesState() {
        display();
    }
    public TilesState(long _state) {
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
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (state.getPlace(y*4 + x) == 0) {
                    int[] possibleMovablePositions = new int[]{ (y-1)*4 + x, (y+1)*4 + x, y*4 + (x-1), y*4 + (x+1) };
                    ArrayList<Action> actions = new ArrayList<Action>();
                    for (int possible : possibleMovablePositions) {
                        if (possible < 16 && possible >= 0) {
                            actions.add(new TilesAction(possible, y*4 + x));
                        }
                    }
                    return actions;
                }
            }
        }
        return new ArrayList<Action>();
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
        return new TilesState(state.getLong());
    }

    @Override
    public void performAction(Action action) {
        TilesAction newAction = (TilesAction) action;

        if (state.getPlace(newAction.moveTo) != 0) {
            return;
        }

        int old = state.getPlace(newAction.moveTo);
        state.setPlace(newAction.moveTo, state.getPlace(newAction.moveFrom));
        state.setPlace(newAction.moveFrom, old);
        actionHistory.add(newAction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TilesState that = (TilesState) o;
        return that.state.getLong() == state.getLong();
    }
}
