package com.belatry.model;

import com.belatry.base.Checker;
import com.belatry.model.exceptions.WordDoesNotExistException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Represents a word from one user's try to guess the hidden. It consists of letters.
 */
@Component
@RequiredArgsConstructor
@Scope("prototype")
public class Word {
    @Getter
    private String value;
    @NonNull
    @Getter
    private List<Letter> letters;
    @NonNull
    private Checker checker;

    public void setValueForExistingWord(String value) {
        if (!checker.isUserWordExists(value)) {
            throw new WordDoesNotExistException(value);
        }
        this.value = value.toUpperCase();
    }

    /**
     * Sets up parameters for the user word's letters in comparing of the hidden word's letters.
     *
     * @param hiddenWord a string value of the hidden word.
     */
    public void setComparingFlagForLetters(String hiddenWord) {
        for (int i = 0; i < value.length(); i++) {
            char[] userWordChars = value.toCharArray();
            Letter letter = new Letter(userWordChars[i]);

            if (checker.isLetterExistInTheHiddenWord(letter.value, hiddenWord)) {
                if (checker.isLetterOnTheRightPlace(i, hiddenWord, value)) {
                    letter.setLetterComparingStatus(LetterComparingStatus.CORRECT_VALUE_AND_PLACE);
                } else {
                    letter.setLetterComparingStatus(LetterComparingStatus.CORRECT_VALUE);
                }
            } else {
                letter.setLetterComparingStatus(LetterComparingStatus.INCORRECT);
            }
            letters.add(letter);
        }
    }

    /**
     * The class to store letters with parameters which depend on the hidden word letters.
     */
    @Data
    static class Letter {
        @NonNull
        private char value;
        private LetterComparingStatus letterComparingStatus;
    }

    /**
     * Represents letter's value and place in comparing to the letters in the hidden word.
     */
    enum LetterComparingStatus {
        CORRECT_VALUE_AND_PLACE, CORRECT_VALUE, INCORRECT;
    }
}
