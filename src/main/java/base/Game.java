package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

import java.util.Optional;

/**
 * Represents the game process.
 */
public class Game {
    private static final String WORD_NOT_EXIST = "\"%s\" word doesn't exist!";
    private static final String WRONG_WORD = "\"%s\" word is wrong.";
    private static final String CORRECT_WORD = "\"%s\" word is correct!";
    private UserInterface userInterface;
    private String hiddenWord;
    private Dictionary dictionary;

    public Game() {
        userInterface = new UserInterface();
        dictionary = new DictionaryFileStorage();
        hiddenWord = createHiddenWord();
    }

    private String createHiddenWord() {
        int randomIndex = (int) (Math.random() * dictionary.getWordsCount());

        Optional<String> hiddenWord = dictionary.getWord(randomIndex);
        if (hiddenWord.isPresent()) {
            return hiddenWord.get();
        } else {
            throw new DictionaryIsNotFoundException("Failed the attempt to create hidden word.");
        }
    }

    /**
     * Starts the game with greeting the user.
     */
    public void startGame() {
        userInterface.greetingUser();
    }

    /**
     * Represents a single game round as user's one try to guess the hidden word.
     * Asks for a user's word then checks that word equals to the hidden word.
     */
    public void playRound() {
        String userWord = getExistingUserWord();

        Checker checker = new Checker(hiddenWord, userWord);
        if (checker.areWordsEqual()) {
            userInterface.talkWithUser(String.format(WRONG_WORD, userWord));
        } else {
            userInterface.talkWithUser(String.format(CORRECT_WORD, userWord));
        }
    }

    /**
     * Returns a word which exists in the dictionary.
     * If the user types an absent word it will ask for other word in a cycle.
     *
     * @return a word which exists in the dictionary.
     */
    private String getExistingUserWord() {
        String userWord = getUserWord();

        while (!dictionary.isExists(userWord)) {
            userInterface.talkWithUser(String.format(WORD_NOT_EXIST, userWord));
            userWord = getUserWord();
        }
        return userWord;
    }

    private String getUserWord() {
        return userInterface.getUserWord();
    }
}