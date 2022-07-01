package model.gamestates;

import base.Game;

public class InProcess implements State {
    @Override
    public void playRound(Game game, boolean hiddenWordGuessed) {
        game.increaseRoundsPlayed();

        if (hiddenWordGuessed) {
            game.setGameState(game.getWon());
        } else if (!game.doesUserHaveGameTries()) {
            game.setGameState(game.getLost());
        }
    }
}
