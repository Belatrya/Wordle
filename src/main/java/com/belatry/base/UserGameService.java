package com.belatry.base;

import com.belatry.model.gamestates.GameState;

public interface UserGameService {
    void addUserGame(String userId, String hiddenWord);

    String getHiddenWord(String userId);

    boolean isUserGameExist(String userId);

    int getCurrentRound(String userId);

    void setCurrentRound(String userId, int roundNumber);

    GameState getUserGameState(String userId);

    void setUserGameState(String userId, GameState gameState);
}
