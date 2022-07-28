package base;

import lombok.Getter;
import lombok.Setter;
import model.Dictionary;
import model.Word;
import model.exceptions.DictionaryIsNotFoundException;
import model.gamestates.InProcess;
import model.gamestates.Lost;
import model.gamestates.State;
import model.gamestates.Won;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Wordle game exemplar.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
@Component
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    @Getter
    private String hiddenWord;
    @Getter
    private int currentRound;
    private Dictionary hiddenWordDictionary;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";
    @Getter
    @Setter
    private State gameState;
    @Autowired
    @Getter
    private Won won;
    @Autowired
    @Getter
    private Lost lost;
    @Getter
    private List<Word> userWordsHistory;
    @Autowired
    private Word userWord;

    @Lookup
    public Word getUserWord() {
        return null;
    }

    public Game(@Qualifier("hiddenWordsDictionary") Dictionary hiddenWordDictionary, InProcess initialState) {
        this.hiddenWordDictionary = hiddenWordDictionary;
        hiddenWord = createHiddenWord();
        currentRound = 1;
        setGameState(initialState);
        userWordsHistory = new ArrayList<>();
    }

    public int getGameRuleCountOfRounds() {
        return GAME_RULE_COUNT_OF_ROUNDS;
    }

    /**
     * Returns true if user's attempts have not ended.
     *
     * @return true if the user have tries, false otherwise.
     */
    private boolean doesUserHaveGameTries() {
        return getCurrentRound() <= getGameRuleCountOfRounds();
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

    /**
     * Represents one game round.
     *
     * @param hiddenWordGuessed shows user's word equals to hidden word.
     */
    public void playRound(boolean hiddenWordGuessed) {
        getGameState().playRound(this);

        if (hiddenWordGuessed) {
            setGameState(getWon());
        } else if (!doesUserHaveGameTries()) {
            setGameState(getLost());
        }
    }

    /**
     * Adds the user's word to the game history.
     *
     * @param userWordValue user word to add in the history.
     */
    public void addUserWordToHistory(String userWordValue) {
        Word userWord = getUserWord();
        userWord.setValue(userWordValue);
        userWord.setComparingFlagForLetters(getHiddenWord());
        getUserWordsHistory().add(userWord);
    }
}