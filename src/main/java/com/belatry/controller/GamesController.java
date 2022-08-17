package com.belatry.controller;

import com.belatry.model.Game;
import com.belatry.model.UserGames;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/games")
public class GamesController {
    private Game game;
    UserGames userGames;

    @PostMapping(value = "/startnew", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> getGameResult(@RequestParam String userName) {
        game.updateHiddenWord();
        userGames.addUserGame(userName, game.getHiddenWord());
        return ResponseEntity.status(HttpStatus.CREATED).body(game.getHiddenWord());
    }

    @GetMapping(value = "/game/{userName}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> getGameById(@PathVariable String userName) {
        return new ResponseEntity<>(userGames.getHiddenWord(userName), HttpStatus.OK);
    }
}