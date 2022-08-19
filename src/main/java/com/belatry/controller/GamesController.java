package com.belatry.controller;

import com.belatry.model.Game;
import com.belatry.model.UserGames;
import com.belatry.model.exceptions.GameIsNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Starts new game and save it in the storage")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "the game is created")})
    @PostMapping("/startnew")
    public ResponseEntity<?> startNewGame(HttpSession session) {
        userGames.addUserGame(session.getId(), game.createHiddenWord());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Returns the hidden word for the current user's game")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "the hidden word"),
            @ApiResponse(responseCode = "404", description = "the game was not found")})
    @GetMapping(value = "/game/hiddenWord", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> getHiddenWord(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getHiddenWord(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Returns the status for the current user's game")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "the game status"),
            @ApiResponse(responseCode = "404", description = "the game was not found")})
    @GetMapping(value = "/game/state")
    public ResponseEntity<?> getGameState(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getUserGameState(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Returns the round number for the current user's game")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "the game round number"),
            @ApiResponse(responseCode = "404", description = "the game was not found")})
    @GetMapping(value = "/game/currentRound")
    public ResponseEntity<?> getGameRoundNumber(HttpSession session) {
        try {
            return ResponseEntity.ok(userGames.getCurrentRound(session.getId()));
        } catch (GameIsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}