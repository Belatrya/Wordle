package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.DictionaryType;
import model.exceptions.DictionaryIsNotFoundException;

import java.util.Optional;

/**
 * Represents a Wordle game exemplar.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    private String hiddenWord;
    private boolean hiddenWordGuessed;
    private int currentRound;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";

    public Game() {
        hiddenWord = createHiddenWord();
        hiddenWordGuessed = false;
        currentRound = 1;
    }

    public int getGameRuleCountOfRounds() {
        return GAME_RULE_COUNT_OF_ROUNDS;
    }

    public boolean isHiddenWordGuessed() {
        return hiddenWordGuessed;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns true if the game is not won yet and if user's attempts have not ended.
     *
     * @return true if the user have tries, false otherwise.
     */
    public boolean doesUserHaveGameTries() {
        return !hiddenWordGuessed && (currentRound <= GAME_RULE_COUNT_OF_ROUNDS);
    }

    private String createHiddenWord() {
        Dictionary dictionary = new DictionaryFileStorage(DictionaryType.HIDDEN_WORDS);
        int randomIndex = (int) (Math.random() * dictionary.getWordsCount());

        Optional<String> hiddenWord = dictionary.getWord(randomIndex);
        if (hiddenWord.isPresent()) {
            return hiddenWord.get().toUpperCase();
        } else {
            throw new DictionaryIsNotFoundException(CREATING_HIDDEN_WORD_EXCEPTION);
        }
    }

    /**
     * Represents a users try to guess the word and counts tries.
     * Updates the game winning status according to user's word equals to hidden or not.
     *
     * @param userWord
     */
    public void playRound(String userWord) {
        Checker checker = new Checker(hiddenWord, userWord);

        hiddenWordGuessed = checker.areWordsEqual();
        currentRound++;
    }
}