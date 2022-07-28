package com.belatry.model.gamestates;

import com.belatry.base.Game;
import org.springframework.stereotype.Component;

/**
 * Represents the game in "in process" state.
 */
@Component
public class InProcess implements State {
    /**
     * Increased count of played rounds and changed game state if it's needed.
     *
     * @param game to play round.
     */
    @Override
    public void playRound(Game game) {
        game.increaseRoundsPlayed();
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
