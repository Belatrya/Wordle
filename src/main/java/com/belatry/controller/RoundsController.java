package com.belatry.controller;

import com.belatry.base.Checker;
import com.belatry.model.Round;
import com.belatry.model.UserGames;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rounds")
public class RoundsController {
    private Round round;
    private Checker checker;
    UserGames userGames;

    @GetMapping("/round")
    public ResponseEntity<Round> playRound(@RequestParam String userName, @RequestParam String userWord) {
        if (userGames.isUserGameExist(userName)) {
            if (checker.isUserWordExists(userWord)) {
                round.play(userGames.getHiddenWord(userName), userWord, userGames.getCurrentRound(userName));
                return ResponseEntity.ok(round);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.noContent().build();
    }
}