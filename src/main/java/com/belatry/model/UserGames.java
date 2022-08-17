package com.belatry.model;

import com.belatry.base.UserGameService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@Data
public class UserGames implements UserGameService {
    private final Map<String, String> userGames;
    private final Map<String, Integer> userRounds;

    @Override
    public void addUserGame(String userName, String hiddenWord) {
        userGames.put(userName, hiddenWord);
    }

    @Override
    public String getHiddenWord(String userName) {
        return userGames.get(userName);
    }

    @Override
    public boolean isUserGameExist(String userName) {
        return userGames.containsKey(userName);
    }

    @Override
    public int getCurrentRound(String userName) {
        return userRounds.get(userName);
    }
}
