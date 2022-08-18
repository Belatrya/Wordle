package com.belatry.model;

import com.belatry.base.UserGameService;
import com.belatry.model.exceptions.GameIsNotFoundException;
import com.belatry.model.gamestates.GameState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class UserGames implements UserGameService {
    private Map<String, String> userGameHiddenWord;
    private Map<String, Integer> userGameRound;
    private Map<String, GameState> userGameState;

    public void checkUserGameExist(String userId) {
        if (!isUserGameExist(userId)) {
            throw new GameIsNotFoundException();
        }
    }

    @Override
    public void addUserGame(String userId, String hiddenWord) {
        userGameHiddenWord.put(userId, hiddenWord);
        setCurrentRound(userId, 1);
        setUserGameState(userId, GameState.IN_PROCESS);
    }

    @Override
    public String getHiddenWord(String userId) {
        checkUserGameExist(userId);
        return userGameHiddenWord.get(userId);
    }

    @Override
    public boolean isUserGameExist(String userId) {
        return userGameHiddenWord.containsKey(userId);
    }

    @Override
    public int getCurrentRound(String userId) {
        checkUserGameExist(userId);
        return userGameRound.get(userId);
    }

    @Override
    public void setCurrentRound(String userId, int roundNumber) {
        checkUserGameExist(userId);
        userGameRound.put(userId, roundNumber);
    }

    @Override
    public GameState getUserGameState(String userId) {
        checkUserGameExist(userId);
        return userGameState.get(userId);
    }

    @Override
    public void setUserGameState(String userId, GameState gameState) {
        checkUserGameExist(userId);
        userGameState.put(userId, gameState);
    }
}
