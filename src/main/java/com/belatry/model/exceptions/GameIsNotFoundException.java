package com.belatry.model.exceptions;

/**
 * Indicates that the game is not found.
 */
public class GameIsNotFoundException extends RuntimeException {
    public GameIsNotFoundException() {
        super("The game is not found!");
    }
}
