package base;

import model.Dictionary;
import model.exceptions.DictionaryIsNotFoundException;
import model.gamestates.InProcess;
import model.gamestates.Lost;
import model.gamestates.State;
import model.gamestates.Won;

import java.util.Optional;

/**
 * Represents a Wordle game exemplar.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    private String hiddenWord;
    private int currentRound;
    private Dictionary hiddenWordDictionary;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";
    private final State won = new Won();
    private final State inProcess = new InProcess();
    private final State lost = new Lost();
    private State gameState;

    public Game(Dictionary hiddenWordDictionary) {
        this.hiddenWordDictionary = hiddenWordDictionary;
        hiddenWord = createHiddenWord();
        currentRound = 1;
        gameState = inProcess;
    }

    public State getGameState() {
        return gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public State getWon() {
        return won;
    }

    public State getLost() {
        return lost;
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