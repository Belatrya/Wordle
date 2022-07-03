package model.gamestates;

import base.Game;

/**
 * Represents the game in "in process" state.
 */
public class InProcess implements State {
    /**
     * Increased count of played rounds and changed game state if it's needed.
     *
     * @param game              to play round.
     * @param hiddenWordGuessed shows is hidden word guessed.
     */
    @Override
    public void playRound(Game game, boolean hiddenWordGuessed) {
        game.increaseRoundsPlayed();

        if (hiddenWordGuessed) {
            game.setGameState(game.getWon());
        } else if (!game.doesUserHaveGameTries()) {
            game.setGameState(game.getLost());
        }
    }

    /**
     * Returns false because the game is in process.
     *
     * @return false because the game is in process.
     */
    @Override
    public boolean isGameEnd() {
        return false;
    }

    /**
     * Returns false because the game is still in process.
     *
     * @return false because the game is still in process.
     */
    @Override
    public boolean isGameWon() {
        return false;
    }
}
