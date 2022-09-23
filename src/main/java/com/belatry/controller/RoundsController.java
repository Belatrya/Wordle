package com.belatry.controller;

import com.belatry.base.Checker;
import com.belatry.base.dao.UserGameService;
import com.belatry.model.Game;
import com.belatry.model.Word;
import com.belatry.model.exceptions.DictionaryIsNotFoundException;
import com.belatry.model.exceptions.GameIsNotFoundException;
import com.belatry.model.exceptions.WordDoesNotExistException;
import com.belatry.model.gamestates.GameState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Represents the user's try to guess the hidden word.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rounds")
public class RoundsController {
    private UserGameService userGames;
    private Checker checker;
    private Game game;
    private Word userWord;
    private static final String YOUR_GAME_ALREADY_OVER = "Your game is already over!";

    @Lookup
    public Word getUserWord() {
        return null;
    }

    @Operation(summary = "Returns the user's word described by letters in comparison with the hidden.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "returns the user's word described by letters in comparison with the hidden.",
                    content = @Content(schema = @Schema(implementation = Word.class))),
            @ApiResponse(responseCode = "422",
                    description = "impossible to play round due to the game is already over or the word doesn't exist"),
            @ApiResponse(responseCode = "500",
                    description = "impossible to play round due to errors with the game or dictionary")})
    @GetMapping(value = "/play", produces = "application/json")
    public ResponseEntity<?> playRound(HttpSession session, @RequestParam String userWord) {
        String userId = session.getId();

        try {
            if (userGames.getUserGameState(userId) == GameState.IN_PROCESS) {
                Word word = getUserWord();
                String hiddenWord = userGames.getHiddenWord(userId);
                int currentRound = userGames.getCurrentRound(userId);

                word.setValueForExistingWord(userWord);
                word.setComparingFlagForLetters(hiddenWord);

                if (checker.isHiddenEqualsToUserWord(hiddenWord, userWord)) {
                    userGames.setUserGameState(userId, GameState.WON);
                } else if (currentRound++ >= game.getGameRuleCountOfRounds()) {
                    userGames.setUserGameState(userId, GameState.LOST);
                }
                userGames.setCurrentRound(userId, currentRound);
                return ResponseEntity.ok(word);
            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(YOUR_GAME_ALREADY_OVER);
        } catch (WordDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (DictionaryIsNotFoundException | GameIsNotFoundException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}