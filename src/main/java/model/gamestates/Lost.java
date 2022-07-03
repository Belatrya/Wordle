package model.gamestates;

import base.Game;

/**
 * Represents the game in the "lost" state.
 */
public class Lost implements State {
    /**
     * There is nothing to play if the game has been already lost.
     *
     * @param game              to play round.
     * @param hiddenWordGuessed shows is hidden word guessed.
     */
    @Override
    public void playRound(Game game, boolean hiddenWordGuessed) {
    }

    /**
     * Returns true because the game is over.
     *
     * @return true because the game is over.
     */
    @Override
    public boolean isGameEnd() {
        return true;
    }

    /**
     * Returns false because the game is lost.
     *
     * @return false because the game is lost.
     */
    @Override
    public boolean isGameWon() {
        return false;
    }
}
