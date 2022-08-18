package com.belatry.controller;

import com.belatry.base.Checker;
import com.belatry.model.Game;
import com.belatry.model.UserGames;
import com.belatry.model.Word;
import com.belatry.model.exceptions.DictionaryIsNotFoundException;
import com.belatry.model.exceptions.GameIsNotFoundException;
import com.belatry.model.exceptions.WordDoesNotExistException;
import com.belatry.model.gamestates.GameState;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rounds")
public class RoundsController {
    private UserGames userGames;
    private Checker checker;
    private Game game;
    private Word userWord;
    private static final String YOUR_GAME_ALREADY_OVER = "Your game is already over!";

    @Lookup
    public Word getUserWord() {
        return null;
    }

    @GetMapping(value = "/play", produces = "application/json")
    public ResponseEntity<?> playRound(HttpSession session, @RequestParam String userWord) {
        String userId = session.getId();

        try {
            if (userGames.getUserGameState(userId) == GameState.IN_PROCESS) {
                Word word = getUserWord();
                String hiddenWord = userGames.getHiddenWord(userId);
                int currentRound = userGames.getCurrentRound(userId);

                userGames.checkUserGameExist(userId);
                word.setValueForExistingWord(userWord);
                word.setComparingFlagForLetters(hiddenWord);

                if (checker.isHiddenEqualsToUserWord(hiddenWord, userWord)) {
                    userGames.setUserGameState(userId, GameState.WON);
                } else if (currentRound++ > game.getGameRuleCountOfRounds()) {
                    userGames.setUserGameState(userId, GameState.LOST);
                }
                userGames.setCurrentRound(userId, currentRound);
                return ResponseEntity.ok(word);
            }
            return ResponseEntity.internalServerError().body(YOUR_GAME_ALREADY_OVER);
        } catch (DictionaryIsNotFoundException | GameIsNotFoundException | WordDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}