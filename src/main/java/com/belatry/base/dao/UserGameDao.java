package com.belatry.base.dao;

import com.belatry.model.dto.UserGameDto;
import com.belatry.model.exceptions.GameIsNotFoundException;
import com.belatry.model.gamestates.GameState;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Represents the DAO for user's games.
 * Throws GameIsNotFoundException if the game for specified user doesn't exist.
 */
@AllArgsConstructor
@Repository
public class UserGameDao implements UserGameService {
    private JdbcTemplate jdbcTemplate;

    private UserGameDto getGameByUserId(String userId) {
        return jdbcTemplate.query("SELECT userid, hiddenword, round, gamestate FROM usergames WHERE userid = ?",
                new BeanPropertyRowMapper<>(UserGameDto.class), userId).stream().findFirst().orElse(null);
    }

    private UserGameDto getUserGameIfExist(String userId) {
        UserGameDto game = getGameByUserId(userId);
        if (game != null) {
            return game;
        } else {
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
        UserGameDto game = new UserGameDto(userId, hiddenWord, 1, GameState.IN_PROCESS);
        jdbcTemplate.update("INSERT INTO usergames (userid, hiddenword, round, gamestate) VALUES (?, ?, ?, ?)",
                game.getUserId(), game.getHiddenWord(), game.getRound(), game.getGameState().toString());
    }

    /**
     * Returns the hidden word for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the hidden word for the user's game.
     */
    @Override
    public String getHiddenWord(String userId) {
        return getUserGameIfExist(userId).getHiddenWord();
    }

    /**
     * Returns true if the game for specified user exists.
     *
     * @param userId unique user's identifier.
     * @return true if the game for specified user exists, false otherwise.
     */
    @Override
    public boolean isUserGameExist(String userId) {
        return getGameByUserId(userId) != null;
    }

    /**
     * Returns the current round for the user's game.
     *
     * @param userId unique user's identifier.
     * @return the current round for the user's game.
     */
    @Override
    public int getCurrentRound(String userId) {
        return getUserGameIfExist(userId).getRound();
    }

    /**
     * Sets the current round for the user's game.
     *
     * @param userId      unique user's identifier.
     * @param roundNumber the number of round.
     */
    @Override
    public void setCurrentRound(String userId, int roundNumber) {
        getUserGameIfExist(userId);
        jdbcTemplate.update("UPDATE usergames SET round = ? WHERE userid = ?", roundNumber, userId);
    }

    /**
     * Returns the current user's game status.
     *
     * @param userId unique user's identifier.
     * @return the current user's game status.
     */
    @Override
    public GameState getUserGameState(String userId) {
        return getUserGameIfExist(userId).getGameState();
    }

    /**
     * Sets the current user's game status.
     *
     * @param userId    unique user's identifier.
     * @param gameState the game status.
     */
    @Override
    public void setUserGameState(String userId, GameState gameState) {
        getUserGameIfExist(userId);
        jdbcTemplate.update("UPDATE usergames SET gamestate = ? WHERE userid = ?", gameState.toString(), userId);
    }
}
