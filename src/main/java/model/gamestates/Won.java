package model.gamestates;

import base.Game;

/**
 * Represents the game in the "won" state.
 */
public class Won implements State {
    /**
     * There is nothing to play if the game has been already won.
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
     * Returns true because the game is won.
     *
     * @return true because the game is won.
     */
    @Override
    public boolean isGameWon() {
        return true;
    }
}