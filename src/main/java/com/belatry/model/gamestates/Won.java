package com.belatry.model.gamestates;

import com.belatry.base.Game;
import org.springframework.stereotype.Component;

/**
 * Represents the game in the "won" state.
 */
@Component
public class Won implements State {
    /**
     * There is nothing to play if the game has been already won.
     *
     * @param game to play round.
     */
    @Override
    public void playRound(Game game) {
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
