import RushHourPuzzle.RushHourState;
import SearchAlgorithms.AStarSearch;
import SearchAlgorithms.IDAStarSearch;
import SearchUtils.SearchNode;
import SearchUtils.Solution;
import SearchUtils.Solver;
import TilePuzzle.TilePuzzleState;

public class Main {
    public static void main(String[] args) {
//        RushHourState rushTest = new RushHourState();
//        rushTest.display();


        TilePuzzleState tilesTest = new TilePuzzleState(-5067131031530221471L);
//        tilesTest.randomize();
        tilesTest.display();
        System.out.println(tilesTest.heuristic());

        Solution test = Solver.solve(tilesTest, new IDAStarSearch());
        System.out.println("Found path of length: " + test.path.size());
        for (SearchNode action : test.path) {
            if (action.creatingAction != null) {
                action.creatingAction.display();
            }
            action.state.display();
            System.out.println("Heuristic of above state: " + action.state.heuristic());
        }
    }
}
