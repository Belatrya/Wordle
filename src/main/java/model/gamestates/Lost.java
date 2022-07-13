package model.gamestates;

import base.Game;
import org.springframework.stereotype.Component;

/**
 * Represents the game in the "lost" state.
 */
@Component
public class Lost implements State {
    /**
     * There is nothing to play if the game has been already lost.
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
     * Returns false because the game is lost.
     *
     * @return false because the game is lost.
     */
    @Override
    public boolean isGameWon() {
        return false;
    }
}
