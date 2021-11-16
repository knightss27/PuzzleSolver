// Seth Knights

import SearchAlgorithms.IDAStarSearch;
import SearchUtils.SearchNode;
import SearchUtils.Solution;
import SearchUtils.Solver;
import TilePuzzle.TilePuzzleState;

public class Main {
    public static void main(String[] args) {
        // In its current state, I have searches working up to IDA*.
        // This is solely for the 15 puzzle, as I have yet to finish Rush Hour.
        // I will be working more on Rush Hour on Thur. 11/4
        // So if you are reading this, I probably didn't update the files.

        // Lastly, there are some odd things about my algorithm.
        // Notably, that for 2 specific states the puzzle finds a path 2 moves long
        // The state below is one of them, which should take 32 moves to complete, but takes 34 instead.
        // I am currently unsure how to debug and fix this, I will get back to you if I find anything.

        TilePuzzleState tilesTest = new TilePuzzleState(-4684461953157345151L);
        tilesTest.display();
        System.out.println(tilesTest.heuristic());

        Solution test = Solver.solve(tilesTest, new IDAStarSearch());
        System.out.println("Found path of length: " + (test.path.size()-1) + " when should have been 32");
//        for (SearchNode action : test.path) {
//            if (action.creatingAction != null) {
//                action.creatingAction.display();
//            }
//            action.state.display();
//            System.out.println("Heuristic of above state: " + action.state.heuristic());
//        }
    }
}
