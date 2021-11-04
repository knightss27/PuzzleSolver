// Seth Knights
package SearchUtils;

import PuzzleInterfaces.Search;
import PuzzleInterfaces.State;

public class Solver {
    public static Solution solve(State start, Search strategy) {
        return strategy.search(start);
    }
}
