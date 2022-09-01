package com.belatry.model;

import com.belatry.base.UserGameService;
import com.belatry.model.exceptions.GameIsNotFoundException;
import com.belatry.model.gamestates.GameState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Represents the storage for the user's games.
 * Throws GameIsNotFoundException if the game for specified user doesn't exist.
 */
@Component
@AllArgsConstructor
public class UserGames implements UserGameService {
    private Map<String, String> userGameHiddenWord;
    private Map<String, Integer> userGameRound;
    private Map<String, GameState> userGameState;

    /**
     * Searches for the game for specified user.
     * Throws GameIsNotFoundException if it doesn't exist.
     *
     * @param userId unique user's identifier.
     */
    public void checkUserGameExist(String userId) {
        if (!isUserGameExist(userId)) {
            throw new GameIsNotFoundException();
        }
    }

    /**
     * Adds the game hidden word into a storage by user id.
     *
     * @param userId     unique user's identifier.
     * @param hiddenWord the hidden word for the user's game.
     */
    @Override
    public void addUserGame(String userId, String hiddenWord) {
        userGameHiddenWord.put(userId, hiddenWord);
        setCurrentRound(userId, 1);
        setUserGameState(userId, GameState.IN_PROCESS);
    }

    /**
     * Returns the hidden word for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the hidden word for the user's game.
     */
    @Override
    public String getHiddenWord(String userId) {
        checkUserGameExist(userId);
        return userGameHiddenWord.get(userId);
    }

    /**
     * Returns true if the game for specified user exists.
     *
     * @param userId unique user's identifier.
     * @return true if the game for specified user exists, false otherwise.
     */
    @Override
    public boolean isUserGameExist(String userId) {
        return userGameHiddenWord.containsKey(userId);
    }

    /**
     * Returns the current round for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the current round for the user's game.
     */
    @Override
    public int getCurrentRound(String userId) {
        checkUserGameExist(userId);
        return userGameRound.get(userId);
    }

    /**
     * Sets the current round for the user's game.
     *
     * @param userId      unique user's identifier.
     * @param roundNumber the number of round.
     */
    @Override
    public void setCurrentRound(String userId, int roundNumber) {
        checkUserGameExist(userId);
        userGameRound.put(userId, roundNumber);
    }

    /**
     * Returns the current user's game status.
     *
     * @param userId unique user's identifier.
     * @return the current user's game status.
     */
    @Override
    public GameState getUserGameState(String userId) {
        checkUserGameExist(userId);
        return userGameState.get(userId);
    }

    /**
     * Sets the current user's game status.
     *
     * @param userId    unique user's identifier.
     * @param gameState the game status.
     */
    @Override
    public void setUserGameState(String userId, GameState gameState) {
        checkUserGameExist(userId);
        userGameState.put(userId, gameState);
    }
}
