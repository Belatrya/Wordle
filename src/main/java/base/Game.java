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
    private UserInterface userInterface;
    private String hiddenWord;

    public Game() {
        userInterface = new UserInterface();
        hiddenWord = createHiddenWord();
    }

    private String createHiddenWord() {
        Dictionary dictionary = new DictionaryFileStorage();
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
        boolean wordsEqual = checker.areWordsEqual();
        userInterface.sayIsUsersWordCorrect(wordsEqual, userWord);

        if (!wordsEqual) {
            char[] userWordLetters = userWord.toUpperCase().toCharArray();

            for (int i = 0; i < hiddenWord.length(); i++) {
                char letter = userWordLetters[i];

                if (checker.isLetterExistInTheHiddenWord(letter)) {
                    userInterface.writeLetterOnTheRightPlace(checker.isLetterOnTheRightPlace(i), letter);
                } else {
                    userInterface.writeLetterNotExistInHiddenWord(letter);
                }
            }
        }
    }

    /**
     * Returns a word which exists in the dictionary.
     * If the user types an absent word it will ask for other word in a cycle.
     *
     * @return a word which exists in the dictionary.
     */
    private String getExistingUserWord() {
        String userWord = userInterface.getUserWord();

        while (!new Checker(userWord).isWordExists()) {
            userInterface.sayUsersWordNotExist(userWord);
            userWord = userInterface.getUserWord();
        }
        return userWord;
    }
}