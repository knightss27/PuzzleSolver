package main;

// Author: Andrew Merrill

import graphics.GameThread;

public class Referee {
    private boolean stopGame = false;

    public void stopGame() {
        stopGame = true;
    }

    public Side runGame(State gameState, Player playerOne, Player playerTwo, GameThread gameThread) {
        stopGame = false;
        playerOne.setSide(Side.ONE);
        playerTwo.setSide(Side.TWO);
        if (gameThread != null)
            gameThread.update(gameState, null);

        while (!gameState.isGameOver()) {
            Side sideToPlay = gameState.getSideToPlay();
            SearchNode selectedNode;
            if (sideToPlay == playerOne.getSide())
                selectedNode = playerOne.pickMove(gameState);
            else
                selectedNode = playerTwo.pickMove(gameState);
            gameState = selectedNode.state;
            if (gameThread != null) {
                gameThread.update(gameState, selectedNode.action);
            }
            if (stopGame) return null;
        }
        return gameState.getWinner();
    }
}