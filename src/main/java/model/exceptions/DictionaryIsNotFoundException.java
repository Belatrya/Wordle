package model.exceptions;

/**
 * Indicates conditions when the dictionary with existing words is not found and there is no reason to proceed the Game.
 */
public class DictionaryIsNotFoundException extends RuntimeException {
    public DictionaryIsNotFoundException() {
        this("The dictionary is not found!");
    }

    public DictionaryIsNotFoundException(String message) {
        super(message + " The Game will be stopped.");
    }
}
