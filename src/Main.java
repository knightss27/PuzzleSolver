import RushHourPuzzle.RushHourState;
import SearchAlgorithms.AStarSearch;
import SearchUtils.SearchNode;
import SearchUtils.Solution;
import SearchUtils.Solver;
import TilePuzzle.TilePuzzleState;

public class Main {
    public static void main(String[] args) {
        RushHourState rushTest = new RushHourState();
        rushTest.display();


//        TilePuzzleState tilesTest = new TilePuzzleState();
//        tilesTest.randomize();
//        tilesTest.display();
//        System.out.println(tilesTest.heuristic());
//
//        Solution test = Solver.solve(tilesTest, new AStarSearch());
//        for (SearchNode action : test.path) {
//            if (action.creatingAction != null) {
//                action.creatingAction.display();
//            }
//            action.state.display();
//        }
    }
}
