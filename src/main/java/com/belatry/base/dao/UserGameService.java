package com.belatry.base.dao;

import com.belatry.model.gamestates.GameState;

/**
 * Provides access to the main game parameters.
 */
public interface UserGameService {
    /**
     * Adds the game hidden word into a storage by user id.
     *
     * @param userId     unique user's identifier.
     * @param hiddenWord the hidden word for the user's game.
     */
    void addUserGame(String userId, String hiddenWord);

    /**
     * Returns the hidden word for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the hidden word for the user's game.
     */
    String getHiddenWord(String userId);

    /**
     * Returns true if the game for specified user exists.
     *
     * @param userId unique user's identifier.
     * @return true if the game for specified user exists, false otherwise.
     */
    boolean isUserGameExist(String userId);

    /**
     * Returns the current round for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the current round for the user's game.
     */
    int getCurrentRound(String userId);

    /**
     * Sets the current round for the user's game.
     *
     * @param userId      unique user's identifier.
     * @param roundNumber the number of round.
     */
    void setCurrentRound(String userId, int roundNumber);

    /**
     * Returns the current user's game status.
     *
     * @param userId unique user's identifier.
     * @return the current user's game status.
     */
    GameState getUserGameState(String userId);

    /**
     * Sets the current user's game status.
     *
     * @param userId    unique user's identifier.
     * @param gameState the game status.
     */
    void setUserGameState(String userId, GameState gameState);
}
