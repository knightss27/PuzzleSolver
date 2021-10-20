public class Main {
    public static void main(String[] args) {
        TilesState tilesTest = new TilesState();
        tilesTest.randomize();
        tilesTest.display();
        System.out.println(tilesTest.heuristic());

        Solution test = Solver.solve(tilesTest, new AStarSearch());
        for (SearchNode action : test.path) {
            if (action.creatingAction != null) {
                action.creatingAction.display();
            }
            action.state.display();
        }
    }
}
