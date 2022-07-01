package model.gamestates;

import base.Game;

public interface State {
    void playRound(Game game, boolean hiddenWordGuessed);
}
