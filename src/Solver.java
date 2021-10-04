public class Solver {
    public static Solution solve(State start, Search strategy) {
        return strategy.search(start);
    }
}
