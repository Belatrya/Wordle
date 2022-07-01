package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.DictionaryType;
import view.UserInterface;

/**
 * Initializes all dependencies for the Wordle application.
 */
public class WordleFactory {
    public UserInterface createApp() {
        Dictionary allWordsDictionary = createAllWordsDictionary();
        Dictionary hiddenWordsDictionary = createHiddenWordsDictionary();
        Checker checker = createChecker(allWordsDictionary);
        Game game = createGame(hiddenWordsDictionary);

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

    private Game createGame(Dictionary dictionary) {
        return new Game(dictionary);
    }
}
