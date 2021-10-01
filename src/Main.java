public class Main {

    public static void main(String[] args) {
        TilesState tilesTest = new TilesState();
        TilesAction newAction = new TilesAction(0, 15);

        tilesTest.performAction(newAction);
        tilesTest.display();
    }
}
