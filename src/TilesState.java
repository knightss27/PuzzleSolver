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

            if (useRandomState) {
                for (int i = 0; i < 16; i++) {
                    int place = (int) (Math.random() * 15);
                    int current = getPlace(place);
                    int replacing = getPlace(place+1);
                    setPlace(place+1, current);
                    setPlace(place, replacing);
                }
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

    @Override
    public List<Action> listActions() {
        return null;
    }

    @Override
    public boolean isGoalState() {
        boolean isGoal = true;
        for (int i = 0; i < 16; i++) {
            isGoal = i == state.getPlace(i);
        }
        return isGoal;
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

        if (state.getPlace(newAction.place) != 0) {
            return;
        }

        state.setPlace(newAction.place, newAction.num);
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
