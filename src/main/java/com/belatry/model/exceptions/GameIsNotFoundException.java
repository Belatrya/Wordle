package com.belatry.model.exceptions;

public class GameIsNotFoundException extends RuntimeException {
    public GameIsNotFoundException() {
        super("The game is not found!");
    }
}
