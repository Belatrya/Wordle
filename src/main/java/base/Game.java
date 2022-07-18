package base;

import model.Dictionary;
import model.exceptions.DictionaryIsNotFoundException;
import model.gamestates.State;
import model.gamestates.StateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Represents a Wordle game exemplar.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
@Controller
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    private String hiddenWord;
    private int currentRound;
    private Dictionary hiddenWordDictionary;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";
    private StateFactory stateFactory;
    private State gameState;

    @Autowired
    public Game(@Value("#{hiddenWordsDictionary}") Dictionary hiddenWordDictionary, StateFactory stateFactory) {
        this.hiddenWordDictionary = hiddenWordDictionary;
        hiddenWord = createHiddenWord();
        currentRound = 1;
        this.stateFactory = stateFactory;
        gameState = stateFactory.createStateInProcess();
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public State getWon() {
        return stateFactory.createStateWon();
    }

    public State getLost() {
        return stateFactory.createStateLost();
    }

    public int getGameRuleCountOfRounds() {
        return GAME_RULE_COUNT_OF_ROUNDS;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Returns true if user's attempts have not ended.
     *
     * @return true if the user have tries, false otherwise.
     */
    public boolean doesUserHaveGameTries() {
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
        getGameState().playRound(this, hiddenWordGuessed);
    }
}