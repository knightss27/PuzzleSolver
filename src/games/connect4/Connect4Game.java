package games.connect4;

// Author: Andrew Merrill

import graphics.GamePanel;
import main.*;

public class Connect4Game implements Game {
    private GamePanel gamePanel;

    private Evaluator[] evaluators = new Evaluator[]{
            new Connect4Evaluator0(),
            new SethsConnect4Evaluator()
    };

    public Connect4Game() {
        gamePanel = new Connect4Panel();
    }

    public String toString() {
        return "connect4";
    }

    public String getNameForSide(Side side) {
        if (side == Side.ONE) return "Black";
        if (side == Side.TWO) return "Red";
        throw new IllegalStateException("No name for side " + side);
    }


    public State getInitialState() {
        return new Connect4State();
    }

    public GamePanel getPanel() {
        return gamePanel;
    }

    public Evaluator[] getEvaluators() {
        return evaluators;
    }
}