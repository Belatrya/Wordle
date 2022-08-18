package com.belatry.model.exceptions;

public class WordDoesNotExistException extends RuntimeException {
    public WordDoesNotExistException(String word) {
        super(String.format("\"%s\" word doesn't exist in the dictionary!", word));
    }
}
