package model.gamestates;

import base.Game;

/**
 * Represents states for the game.
 */
public interface State {
    /**
     * Represents game round depends on the game's state.
     *
     * @param game              to play round.
     * @param hiddenWordGuessed shows is hidden word guessed.
     */
    void playRound(Game game, boolean hiddenWordGuessed);

    /**
     * Returns true if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    boolean isGameEnd();

    /**
     * Returns true if the game is won.
     *
     * @return true if the game is won, false otherwise.
     */
    boolean isGameWon();
}
