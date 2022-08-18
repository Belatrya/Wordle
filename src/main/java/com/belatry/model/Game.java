package com.belatry.model;

import com.belatry.model.exceptions.DictionaryIsNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Represents a Wordle game exemplar.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
@Component
public class Game {
    private static final int GAME_RULE_COUNT_OF_ROUNDS = 6;
    private Dictionary hiddenWordDictionary;
    private static final String CREATING_HIDDEN_WORD_EXCEPTION = "Failed the attempt to create hidden word.";

    public Game(@Qualifier("hiddenWordsDictionary") Dictionary hiddenWordDictionary) {
        this.hiddenWordDictionary = hiddenWordDictionary;
    }

    public int getGameRuleCountOfRounds() {
        return GAME_RULE_COUNT_OF_ROUNDS;
    }

    public String createHiddenWord() {
        int randomIndex = (int) (Math.random() * hiddenWordDictionary.getWordsCount());

        Optional<String> hiddenWord = hiddenWordDictionary.getWord(randomIndex);
        if (hiddenWord.isPresent()) {
            return hiddenWord.get().toUpperCase();
        } else {
            throw new DictionaryIsNotFoundException(CREATING_HIDDEN_WORD_EXCEPTION);
        }
    }
}