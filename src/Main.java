// Seth Knights

import CircularSwitcherPuzzle.CircularSwitcherState;
import PuzzleInterfaces.Action;
import RushHourPuzzle.Car;
import RushHourPuzzle.RushHourAction;
import RushHourPuzzle.RushHourState;
import SearchAlgorithms.*;
import SearchUtils.SearchNode;
import SearchUtils.Solution;
import SearchUtils.Solver;
import TilePuzzle.TilePuzzleState;

public class Main {
    public static void main(String[] args) {
        // FOR ANDREW!!!!!!
        // All major sections of the project have been completed.
        // Read the write-up here:
        // https://docs.google.com/document/d/1qDYUTbxP1Zpg6NPEXp4r22-eAD91nJdGigYY0feXySM/edit


        CircularSwitcherState switcherState2 = new CircularSwitcherState(new int[]{1, 4, 17, 18, 6, 13, 20, 10, 11, 12, 16, 8, 7, 5, 9, 14, 3, 19, 15, 2});
//        CircularSwitcherState switcherState2 = new CircularSwitcherState(new int[]{12,5,20,4,19,6,10,8,9,7,14,15,2,11,18,13,17,1,16,3});
//        switcherState2.randomize(1);
        switcherState2.display();
        System.out.println("\n Path \n");

        Solution switcherSolution = Solver.solve(switcherState2, new IDAStarSearch());
        for (SearchNode node : switcherSolution.path) {
            if (node.creatingAction != null) {
                node.creatingAction.display();
            }
            node.state.display();
        }

//        Various testing code.

//        RushHourState rushHourState = new RushHourState("H3-0,V2-3,X2-14");
//        RushHourState rushHourState = new RushHourState("H2-0,V3-5,V3-6,V3-9,V2-24,H2-28,H3-32,X2-13");
//        rushHourState.display();
//
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
//
//        TilePuzzleState tilesTestGoal = new TilePuzzleState(0xfedcba9876543210L);
//        TilePuzzleState tilesTest = new TilePuzzleState(Long.parseUnsignedLong("0000111100011010011101101100110100101000010001011011100111100011", 2));
//
//        tilesTestGoal.display();
//        tilesTest.display();
//        System.out.println(tilesTest.heuristic() + " <-- should be 36");
//
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
