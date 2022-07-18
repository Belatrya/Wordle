package model.gamestates;

import org.springframework.stereotype.Component;

/**
 * Creates the states for the game.
 */
@Component
public class StateFactory {
    public State createStateWon() {
        return new Won();
    }

    public State createStateLost() {
        return new Lost();
    }

    public State createStateInProcess() {
        return new InProcess();
    }
}
