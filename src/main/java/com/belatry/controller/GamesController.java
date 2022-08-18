package com.belatry.controller;

import com.belatry.model.Game;
import com.belatry.model.UserGames;
import com.belatry.model.exceptions.GameIsNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/games")
public class GamesController {
    private Game game;
    private UserGames userGames;

    @PostMapping("/startnew")
    public ResponseEntity<?> startNewGame(HttpSession session) {
        userGames.addUserGame(session.getId(), game.createHiddenWord());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/game/hiddenWord", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> getHiddenWord(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getHiddenWord(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/game/state")
    public ResponseEntity<?> getGameState(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getUserGameState(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/game/currentRound")
    public ResponseEntity<?> getGameRoundNumber(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getCurrentRound(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}