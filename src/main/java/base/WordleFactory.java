package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.DictionaryType;
import model.gamestates.StateFactory;
import view.UserInterface;

/**
 * Initializes all dependencies for the Wordle application.
 */
public class WordleFactory {
    public UserInterface createApp() {
        Dictionary allWordsDictionary = createAllWordsDictionary();
        Dictionary hiddenWordsDictionary = createHiddenWordsDictionary();
        Checker checker = createChecker(allWordsDictionary);
        StateFactory stateFactory = createStateFactory();
        Game game = createGame(hiddenWordsDictionary, stateFactory);

        return new UserInterface(game, checker);
    }

    private Dictionary createAllWordsDictionary() {
        return new DictionaryFileStorage(DictionaryType.ALL_WORDS);
    }

    private Dictionary createHiddenWordsDictionary() {
        return new DictionaryFileStorage(DictionaryType.HIDDEN_WORDS);
    }

    private Checker createChecker(Dictionary dictionary) {
        return new Checker(dictionary);
    }

    private Game createGame(Dictionary dictionary, StateFactory stateFactory) {
        return new Game(dictionary, stateFactory);
    }

    private StateFactory createStateFactory() {
        return new StateFactory();
    }
}
