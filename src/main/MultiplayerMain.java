package main;

// Author: Andrew Merrill

import games.connect4.Connect4Game;
import games.connect4.Connect4Evaluator0;
import games.connect4.Connect4State;
import players.AlphaBetaPlayer;
import players.MiniMaxPlayer;

public class MultiplayerMain {
    public static void main(String[] args) {
        compareEvaluators();
        //timeEvaluators();
        //timePlayers();
        //comparePlayers();
    }

    static void compareEvaluators() {
        Analysis.compareEvaluatorsForGame(new Connect4Game(), 100);
    }

    static void timeEvaluators() {
        Game game = new Connect4Game();
        Evaluator[] evaluators = game.getEvaluators();
        int maxDepth = 8;

        for (Evaluator evaluator : evaluators) {
            for (int d = 1; d <= maxDepth; d++) {
                Player p = new MiniMaxPlayer(d, evaluator);
                Analysis.averageTime(new Connect4State(), p, 50);
            }
        }
    }

    static void timePlayers() {
        for (int depth=1; depth<=10; depth++) {
            Analysis.averageTimes(new Connect4State(),
                    new Player[]{
                            new MiniMaxPlayer(depth, new Connect4Evaluator0()),
                            new AlphaBetaPlayer(depth, new Connect4Evaluator0())
                    },
                    50);
            System.out.println();
        }
    }

    static void comparePlayers() {
        System.out.println("fraction of games that players agree is: " +
                Analysis.playersAgree(
                    new Connect4State(),
                    new MiniMaxPlayer(4, new Connect4Evaluator0()),
                    new AlphaBetaPlayer(4, new Connect4Evaluator0()),
                    100));
    }
}
