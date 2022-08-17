package com.belatry.base;

public interface UserGameService {
    void addUserGame(String userName, String hiddenWord);

    String getHiddenWord(String userName);

    boolean isUserGameExist(String userName);

    int getCurrentRound(String userName);
}
