package base;

import model.Dictionary;
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
    private Dictionary hiddenWordDictionary;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";

    public Game(Dictionary hiddenWordDictionary) {
        this.hiddenWordDictionary = hiddenWordDictionary;
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

    public void setHiddenWordGuessed(boolean hiddenWordGuessed) {
        this.hiddenWordGuessed = hiddenWordGuessed;
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
        return !isHiddenWordGuessed() && (getCurrentRound() <= getGameRuleCountOfRounds());
    }

    private String createHiddenWord() {
        int randomIndex = (int) (Math.random() * hiddenWordDictionary.getWordsCount());

        Optional<String> hiddenWord = hiddenWordDictionary.getWord(randomIndex);
        if (hiddenWord.isPresent()) {
            return hiddenWord.get().toUpperCase();
        } else {
            throw new DictionaryIsNotFoundException(CREATING_HIDDEN_WORD_EXCEPTION);
        }
    }

    /**
     * Increased current game round on 1.
     */
    public void increaseRoundsPlayed() {
        currentRound++;
    }
}