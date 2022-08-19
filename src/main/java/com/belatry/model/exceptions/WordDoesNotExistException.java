package com.belatry.model.exceptions;

/**
 * Indicates conditions when specified word was not found in the dictionary.
 */
public class WordDoesNotExistException extends RuntimeException {
    public WordDoesNotExistException(String word) {
        super(String.format("\"%s\" word doesn't exist in the dictionary!", word));
    }
}
