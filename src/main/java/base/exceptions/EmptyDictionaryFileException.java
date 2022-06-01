package base.exceptions;

/**
 *  Indicates conditions when the dictionary with existing words is empty and there is no reason to proceed the Game.
 */
public class EmptyDictionaryFileException extends Exception {
    public EmptyDictionaryFileException() {
        super("The dictionary file is empty! The Game will be stopped.");
    }
}