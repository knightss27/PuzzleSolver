package PuzzleInterfaces;

import SearchUtils.Solution;

public interface Search {
    Solution search(State startState);
    String toString();
}
