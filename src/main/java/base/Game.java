package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.DictionaryType;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

import java.util.Optional;

/**
 * Represents the game process.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    private UserInterface userInterface;
    private String hiddenWord;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";

    public Game() {
        userInterface = new UserInterface();
        hiddenWord = createHiddenWord();
    }

    public int getGameRuleCountOfRounds() {
        return GAME_RULE_COUNT_OF_ROUNDS;
    }

    private String createHiddenWord() {
        Dictionary dictionary = new DictionaryFileStorage(DictionaryType.HIDDEN_WORDS);
        int randomIndex = (int) (Math.random() * dictionary.getWordsCount());

        Optional<String> hiddenWord = dictionary.getWord(randomIndex);
        if (hiddenWord.isPresent()) {
            return hiddenWord.get();
        } else {
            throw new DictionaryIsNotFoundException(CREATING_HIDDEN_WORD_EXCEPTION);
        }
    }

    /**
     * Represents a single game round as user's one try to guess the hidden word.
     * Asks for a user's word then checks that word equals to the hidden word.
     *
     * @return true if the user's try was successful and his word equals to hidden, false otherwise.
     */
    public boolean playRound() {
        String userWord = getExistingUserWord();

        Checker checker = new Checker(hiddenWord, userWord);
        boolean wordsEqual = checker.areWordsEqual();
        userInterface.writeIsUsersWordCorrect(wordsEqual, userWord);

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
        return wordsEqual;
    }

    /**
     * Represents the Game by repeating rounds and counting the game result after it.
     */
    public void playGame() {
        boolean isWin = false;
        for (int i = 1; i <= GAME_RULE_COUNT_OF_ROUNDS; i++) {
            userInterface.writeNumberOfRounds(i);
            isWin = playRound();
            if (isWin) {
                break;
            }
        }
        userInterface.writeGameResult(isWin, hiddenWord);
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
            userInterface.writeUsersWordNotExist(userWord);
            userWord = userInterface.getUserWord();
        }
        return userWord;
    }
}