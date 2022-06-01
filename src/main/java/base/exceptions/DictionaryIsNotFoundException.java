package base.exceptions;

/**
 * Indicates conditions when the dictionary with existing words is not found and there is no reason to proceed the Game.
 */
public class DictionaryIsNotFoundException extends Exception {
    public DictionaryIsNotFoundException() {
        super("The dictionary is not found! The Game will be stopped.");
    }
}
