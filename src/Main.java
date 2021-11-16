// Seth Knights

import PuzzleInterfaces.Action;
import RushHourPuzzle.Car;
import RushHourPuzzle.RushHourAction;
import RushHourPuzzle.RushHourState;
import SearchAlgorithms.BreadthFirstSearch;
import SearchAlgorithms.DepthFirstSearch;
import SearchAlgorithms.IDAStarSearch;
import SearchAlgorithms.IDDepthFirstSearch;
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


//        RushHourState rushHourState = new RushHourState("H3-0,V2-3,X2-14");
//        RushHourState rushHourState = new RushHourState("H2-0,V3-5,V3-6,V3-9,V2-24,H2-28,H3-32,X2-13");
//        rushHourState.display();

//        for (Action action : rushHourState.listActions()) {
//            action.display();
//        }
//
//        rushHourState.performAction(rushHourState.listActions().get(0));
//        rushHourState.display();
//        System.out.println(rushHourState.isGoalState());

//        Solution solution = Solver.solve(rushHourState, new IDDepthFirstSearch());
//        for (SearchNode node : solution.path) {
//            node.state.display();
//        }

//        RushHourAction rushHourAction = new RushHourAction(new Car(Car.Orientation.HORIZONTAL, Car.Length.LONG, 0), 2);
//        rushHourState.performAction(rushHourAction);
//        rushHourState.display();
//
//        rushHourAction = new RushHourAction(new Car(Car.Orientation.VERTICAL, Car.Length.LONG, 5), -2);
//        rushHourState.performAction(rushHourAction);
//        rushHourState.display();
//
//        for (Action action : rushHourState.listActions()) {
//            action.display();
//        }
//
//        rushHourAction = new RushHourAction(new Car(Car.Orientation.HORIZONTAL, Car.Length.LONG, 2), -2);
//        rushHourState.performAction(rushHourAction);
//        rushHourState.display();
//
//        for (Action action : rushHourState.listActions()) {
//            action.display();
//        }

        // 9 3,14,9,11,5,4,8,2,13,12,6,7,10,1,15,0 32 36 46
        TilePuzzleState tilesTestGoal = new TilePuzzleState(0xfedcba9876543210L);
        TilePuzzleState tilesTest = new TilePuzzleState(Long.parseUnsignedLong("0000111100011010011101101100110100101000010001011011100111100011", 2));

        tilesTestGoal.display();
        tilesTest.display();
        System.out.println(tilesTest.heuristic() + " <-- should be 36");

//        Solution test = Solver.solve(tilesTest, new IDAStarSearch());
//        System.out.println("Found path of length: " + (test.path.size()-1) + " when should have been 46");
//        for (SearchNode action : test.path) {
//            if (action.creatingAction != null) {
//                action.creatingAction.display();
//            }
//            action.state.display();
//            System.out.println("Heuristic of above state: " + action.state.heuristic());
//        }
    }
}
