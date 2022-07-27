package com.belatry.model.gamestates;

import com.belatry.base.Game;

/**
 * Represents states for the game.
 */
public interface State {
    /**
     * Represents game round depends on the game's state.
     *
     * @param game to play round.
     */
    void playRound(Game game);

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
