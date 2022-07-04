package model.gamestates;

/**
 * Creates the states for the game.
 */
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
